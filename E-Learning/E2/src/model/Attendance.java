package model;

public class Attendance {
    private String memberId;
    private String date;
    private boolean attended;

    public Attendance(String memberId, String date, boolean attended) {
        this.memberId = memberId;
        this.date = date;
        this.attended = attended;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getDate() {
        return date;
    }

    public boolean isAttended() {
        return attended;
    }
}
