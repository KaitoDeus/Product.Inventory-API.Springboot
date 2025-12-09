package service;

import java.util.*;

import model.Member;

public class ReportService {
    public void showReport(List<Member> members) {
        int total = members.size();
        int active = 0;
        double avgProgress = 0;
        for (Member m : members) {
            avgProgress += m.getProgress();
            if (m.getSubscription() != null && m.getSubscription().isActive())
                active++;
        }
        if (total > 0)
            avgProgress /= total;
        System.out.println("Tong thanh vien: " + total);
        System.out.println("Dang hoat dong: " + active);
        System.out.println("Tien do trung binh: " + avgProgress + "%");
    }
}
