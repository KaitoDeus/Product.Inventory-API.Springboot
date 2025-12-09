package model;

public class Member extends User {
    private Subscription subscription;
    private double progress;

    public Member(String id, String name) {
        super(id, name, "Member");
        this.progress = 0.0;
    }

    public void setSubscription(Subscription s) {
        this.subscription = s;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public double getProgress() {
        return progress;
    }

    public void updateProgress(double value) {
        this.progress = Math.min(100, this.progress + value);
    }

    @Override
    public void showMenu() {
        System.out.println("1. Xem thong tin");
        System.out.println("2. Cap nhat tien do");
        System.out.println("3. Gia han goi tap");
        System.out.println("0. Thoat");
    }

}
