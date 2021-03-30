
public class Cashbox {
    /**
     * Cash held at the cashbox
     */
    private volatile double cash;

    /**
     * Default constructor
     */
    public Cashbox() {
    }

    /**
     * Constructor with parameters
     *
     * @param cash the amount of money kept in the cashbox
     */
    public Cashbox(double cash) {
        this.cash = cash;
    }

    /**
     * Аdding money to the cashbox
     *
     * @param sum the amount of money you need to put in the cashbox
     */
    public synchronized void put(double sum) {
        this.cash += sum;
    }

    /**
     * Taking money from the cashbox
     *
     * @param sum the amount of money to be withdrawn
     * @return true if you managed to withdraw the required amount of money
     */
    public synchronized boolean withdraw(double sum) {
        boolean operationResult;
        if (cash - sum < 0) {
            operationResult = false;
        } else {
            cash -= sum;
            operationResult = true;
        }
        return operationResult;
    }
}
