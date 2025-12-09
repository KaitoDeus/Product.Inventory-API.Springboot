package model;

public class Subscription {
    private String planName;
    private double price;
    private boolean active;

    public Subscription(String planName, double price) {
        this.planName = planName;
        this.price = price;
        this.active = true;
    }

    public String getPlanName() {
        return planName;
    }

    public double getPrice() {
        return price;
    }

    public boolean isActive() {
        return active;
    }

    public void renew() {
        active = true;
    }

    public void expire() {
        active = false;
    }
}
