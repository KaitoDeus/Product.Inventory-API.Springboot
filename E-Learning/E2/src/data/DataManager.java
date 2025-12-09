package data;

import java.io.*;
import java.util.*;

import model.Member;

public class DataManager {
    public static void saveMembers(List<Member> members) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("members.csv"))) {
            for (Member m : members) {
                pw.println(m.getId() + "," + m.getName() + "," + m.getProgress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Member> loadMembers() {
        List<Member> members = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("members.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                Member m = new Member(arr[0], arr[1]);
                m.updateProgress(Double.parseDouble(arr[2]));
                members.add(m);
            }
        } catch (IOException e) {
            // File chưa tồn tại
        }
        return members;
    }
}
