package model;

public class Trainer extends User {
    public Trainer(String id, String name) {
        super(id, name, "Trainer");
    }

    @Override
    public void showMenu() {
        System.out.println("1. Gan lich tap");
        System.out.println("2. Diem danh thanh vien");
        System.out.println("3. Xem danh sach thanh vien");
        System.out.println("0. Thoat");
    }
}
