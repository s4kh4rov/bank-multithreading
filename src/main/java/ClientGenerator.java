import java.util.Random;

public class ClientGenerator implements Runnable {
    /**
     * Average client service time in milliseconds
     */
    private static final long SERVICE_TIME = 4526;
    /**
     * The average amount of money that a client has when it is created
     */
    private static final double AVERAGE_CASH_AMOUNT = 10000;
    /**
     * Number of clients generated per minute
     */
    private static final int CLIENTS_PER_MINUTE = 20;

    /**
     * The time interval between the creation of clients. Calculated based on the number of clients per minute.
     */
    private int generationDelay;
    /**
     * The bank to which the created clients will be directed
     */
    private Bank bank;
    private Random random;

    /**
     * Constructor with parameters
     *
     * @param bank the bank to which the created clients will be directed
     */
    public ClientGenerator(Bank bank) {
        this.generationDelay = 60 / CLIENTS_PER_MINUTE;
        this.random = new Random();
        this.bank = bank;
    }

    /**
     * The method creates a new client at a specified time interval and redirects him to the queue to the bank cashier
     */
    @Override
    public void run() {
        OperationType[] type = OperationType.values();
        while (true) {
            try {
                Thread.sleep(Math.abs((long) ((random.nextGaussian() * (generationDelay / 2) + generationDelay) * 1000)));
                long clientServiceTime = Math.abs(Math.round(random.nextGaussian() * (SERVICE_TIME / 2) + SERVICE_TIME));
                double clientCash = Math.abs(Math.round(random.nextGaussian() * (AVERAGE_CASH_AMOUNT / 2) + AVERAGE_CASH_AMOUNT));
                Client client = new Client(type[random.nextInt(type.length)], clientCash, clientServiceTime);
                bank.getFreeCashier().addClient(client);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
