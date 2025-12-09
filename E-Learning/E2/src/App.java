import java.util.Scanner;

import data.DataManager;
import service.AdminService;
import service.MemberService;
import service.TrainerService;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Tạo các service chính
        MemberService memberService = new MemberService();
        TrainerService trainerService = new TrainerService(memberService);
        AdminService adminService = new AdminService(memberService, trainerService);

        // Tải dữ liệu (nếu có)
        memberService.setMembers(DataManager.loadMembers());

        System.out.println("=======================================");
        System.out.println("     WELCOME TO GYM MANAGEMENT SYSTEM  ");
        System.out.println("=======================================");

        while (true) {
            System.out.println("\nChon vai tro:");
            System.out.println("1. Admin");
            System.out.println("2. Trainer");
            System.out.println("3. Member");
            System.out.println("0. Thoat");
            System.out.print("Lua chon cua ban: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("\n=== Dang nhap voi vai tro ADMIN ===");
                    adminService.menu();
                    break;
                case "2":
                    System.out.println("\n=== Dang nhap voi vai tro TRAINER ===");
                    trainerService.menu();
                    break;
                case "3":
                    System.out.println("\n=== Dang nhap voi vai tro MEMBER ===");
                    memberService.menu();
                    break;
                case "0":
                    System.out.println("Luu du lieu thanh cong!");
                    DataManager.saveMembers(memberService.getMembers());
                    System.out.println("Thoat chuong trinh!");
                    sc.close();
                    return;
                default:
                    System.out.println("Lua chon khong hop le, vui long thu lai!");
            }
        }
    }
}
