package service;

import java.util.*;

public class AdminService {
    private MemberService memberService;
    private TrainerService trainerService;
    private ReportService reportService = new ReportService();
    private Scanner sc = new Scanner(System.in);

    public AdminService(MemberService m, TrainerService t) {
        this.memberService = m;
        this.trainerService = t;
    }

    public void menu() {
        while (true) {
            System.out.println("\n--- MENU ADMIN ---");
            System.out.println("1. Quan ly thanh vien");
            System.out.println("2. Xem bao cao");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    memberService.menu();
                    break;
                case 2:
                    reportService.showReport(memberService.getMembers());
                    break;
                case 0:
                    return;
            }
        }
    }
}
