import java.util.ArrayList;
import java.util.List;

public class Bank {
    /**
     * List of cashiers working in this bank
     */
    private List<Cashier> cashierList;
    /**
     * Bank general cashbox
     */
    private Cashbox cashbox;
    /**
     * Number of cashiers working in the bank
     */
    private int cashiersCount;

    /**
     * Constructor with parameters
     *
     * @param startCash     the amount of money in the cashbox of the bank when it was created
     * @param cashiersCount initial number of cashiers
     */
    public Bank(int startCash, int cashiersCount) {
        this.cashiersCount = cashiersCount;
        this.cashbox = new Cashbox(startCash);
        this.cashierList = new ArrayList<>();

    }

    /**
     * Creating objects and launching threads required for the bank to work
     */
    public void startWork() {
        for (int i = 0; i < cashiersCount; i++) {
            Cashier cashier = new Cashier(cashbox);
            cashierList.add(cashier);
            new Thread(cashier, "Кассир " + i).start();
        }
        ClientGenerator generator = new ClientGenerator(this);
        Thread t = new Thread(generator, "Генератор");
        t.start();
    }

    /**
     * Search for a cashier to whom the smallest queue
     *
     * @return cashier with the smallest queue
     */
    public Cashier getFreeCashier() {
        Cashier cashier = cashierList.get(0);
        int minQueueSize = cashierList.get(0).getQueueSize();
        for (Cashier c : cashierList) {
            if (c.getQueueSize() < minQueueSize) {
                minQueueSize = c.getQueueSize();
                cashier = c;
            }
        }
        return cashier;
    }
}
