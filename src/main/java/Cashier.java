import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Cashier implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(Cashier.class);
    /**
     * Bank cashbox with which the cashier works
     */
    private Cashbox cashbox;
    /**
     * Queue to the cashier
     */
    private List<Client> clientQueue;

    public Cashier() {
    }

    public Cashier(Cashbox cashbox) {
        this.clientQueue = new ArrayList<>();
        this.cashbox = cashbox;
    }

    /**
     * Adding money to the cashbox
     *
     * @param cashbox cashbox where you need to put money
     * @param cash    the amount of money to be deposited
     */
    public void putCash(Cashbox cashbox, double cash) {
        cashbox.put(cash);
        LOGGER.info("Положил в кассу " + cash);
    }

    /**
     * Take money from the cashbox
     *
     * @param cashbox cashbox from which to withdraw money
     * @param cash    amount of money to withdraw
     */
    public void withdrawCash(Cashbox cashbox, double cash) {
        boolean operationResult = cashbox.withdraw(cash);
        if (operationResult) {
            LOGGER.info("Взял из кассы " + cash);
        } else {
            LOGGER.info("Не смог снять " + cash + " . Недостаточно средств!");
        }
    }

    /**
     * @return the number of clients in the queue at the cashier
     */
    public int getQueueSize() {
        return clientQueue.size();
    }

    /**
     * Add a client to the cashier queue
     *
     * @param c client
     */
    public void addClient(Client c) {
        synchronized (clientQueue) {
            clientQueue.add(c);
            clientQueue.notify();
        }
    }

    /**
     * The method contains the logic of the cashier operation. As long as the queue to the cashier is empty, the thread
     * is waiting.If a client is added, then the thread goes to sleep for the duration of the client service and then
     * performs the operation,which is specified in the type of operation in the client.
     */
    @Override
    public void run() {
        Client client;
        while (true) {
            try {
                synchronized (clientQueue) {
                    while (clientQueue.isEmpty()) {
                        clientQueue.wait();
                    }
                    LOGGER.info("Клиентов в очереди " + clientQueue.size());
                    client = clientQueue.remove(0);
                    LOGGER.info("Пришел клиент " + client.toString());
                }
                Thread.sleep(client.getServiceTime());
                switch (client.getOperationType()) {
                    case PUT_CASH:
                        putCash(cashbox, client.getSum());
                        break;
                    case WITHDRAW_CASH:
                        withdrawCash(cashbox, client.getSum());
                        break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
