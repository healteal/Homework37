package Task2;

public class Client {
    private final int clientNumber;
    private final Meal meal;
    private boolean isBlocked;

    public Client(int clientNumber,Meal meal, boolean isBlocked) {
        this.meal = meal;
        this.clientNumber = clientNumber;
        this.isBlocked = isBlocked;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public Meal getMeal() {
        return meal;
    }
}
