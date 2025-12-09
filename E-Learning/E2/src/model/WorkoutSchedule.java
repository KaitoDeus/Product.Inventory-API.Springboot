package model;

public class WorkoutSchedule {
    private String memberId;
    private String exercise;
    private String date;

    public WorkoutSchedule(String memberId, String exercise, String date) {
        this.memberId = memberId;
        this.exercise = exercise;
        this.date = date;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getExercise() {
        return exercise;
    }

    public String getDate() {
        return date;
    }
}
