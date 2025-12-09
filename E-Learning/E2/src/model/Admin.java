package model;

public class Admin extends User {
    public Admin(String id, String name) {
        super(id, name, "Admin");
    }

    @Override
    public void showMenu() {
        System.out.println("1. Quan ly thanh vien");
        System.out.println("2. Quan ly huan luyen vien");
        System.out.println("3. Xem bao cao doanh thu");
        System.out.println("0. Thoat");
    }
}
