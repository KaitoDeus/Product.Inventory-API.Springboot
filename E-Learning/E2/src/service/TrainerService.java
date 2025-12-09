package service;

import java.util.*;

import model.Attendance;
import model.WorkoutSchedule;

public class TrainerService {
    private List<WorkoutSchedule> schedules = new ArrayList<>();
    private List<Attendance> attendanceList = new ArrayList<>();
    private MemberService memberService;
    private Scanner sc = new Scanner(System.in);

    public TrainerService(MemberService ms) {
        this.memberService = ms;
    }

    public void menu() {
        while (true) {
            System.out.println("\n--- MENU TRAINER ---");
            System.out.println("1. Them lich tap");
            System.out.println("2. Diem danh");
            System.out.println("3. Xem danh sach thanh vien");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    assignSchedule();
                    break;
                case 2:
                    markAttendance();
                    break;
                case 3:
                    memberService.showMembers();
                    break;
                case 0:
                    return;
            }
        }
    }

    private void assignSchedule() {
        System.out.print("Nhap ID thanh vien: ");
        String id = sc.nextLine();
        System.out.print("Bai tap: ");
        String ex = sc.nextLine();
        System.out.print("Ngay: ");
        String date = sc.nextLine();
        schedules.add(new WorkoutSchedule(id, ex, date));
        System.out.println("Da tao lich tap!");
    }

    private void markAttendance() {
        System.out.print("Nhap ID thanh vien: ");
        String id = sc.nextLine();
        System.out.print("Ngay: ");
        String date = sc.nextLine();
        attendanceList.add(new Attendance(id, date, true));
        System.out.println("Da diem danh!");
    }
}
