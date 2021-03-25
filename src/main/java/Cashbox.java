public class Cashbox {
    /**
     * Cash held at the cashbox
     */
    private volatile double cash;

    public Cashbox() {
    }

    public Cashbox(double cash) {
        this.cash = cash;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }
}
