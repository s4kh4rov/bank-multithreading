public class Client {
    private OperationType operationType;
    private double sum;
    private long serviceTime;

    public Client(OperationType operationType, double sum, long serviceTime) {
        this.operationType = operationType;
        this.sum = sum;
        this.serviceTime = serviceTime;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public long getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(long serviceTime) {
        this.serviceTime = serviceTime;
    }
}
