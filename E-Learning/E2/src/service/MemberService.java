package service;
import java.util.*;

import model.Member;

public class MemberService {
    private List<Member> members = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void addMember(Member m) {
        members.add(m);
    }

    public void showMembers() {
        for (Member m : members) {
            System.out.println(m.getId() + " | " + m.getName() + " | Progress: " + m.getProgress() + "%");
        }
    }

    public void menu() {
        while (true) {
            System.out.println("\n--- MENU MEMBER ---");
            System.out.println("1. Them thanh vien");
            System.out.println("2. Xem danh sach thanh vien");
            System.out.println("3. Cap nhat tien do");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Nhap ID: ");
                    String id = sc.nextLine();
                    System.out.print("Nhap ten: ");
                    String name = sc.nextLine();
                    addMember(new Member(id, name));
                    System.out.println("Da them thanh vien!");
                    break;
                case 2:
                    showMembers();
                    break;
                case 3:
                    System.out.print("Nhap ID thanh vien: ");
                    id = sc.nextLine();
                    for (Member m : members) {
                        if (m.getId().equals(id)) {
                            System.out.print("Nhap tien do hoan thanh (%): ");
                            double p = sc.nextDouble();
                            m.updateProgress(p);
                            System.out.println("Cap nhat thanh cong!");
                        }
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
