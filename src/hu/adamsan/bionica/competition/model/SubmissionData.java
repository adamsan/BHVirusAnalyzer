package hu.adamsan.bionica.competition.model;

import java.util.Date;

public class SubmissionData {
    private String teamName;
    private String teamCode;
    private int score;
    private Date startSubmitTime;

    public SubmissionData(String teamName, String teamCode) {
        this.teamName = teamName;
        this.teamCode = teamCode;
        this.startSubmitTime = new Date();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getStartSubmitTime() {
        return startSubmitTime;
    }

    public void setStartSubmitTime(Date startSubmitTime) {
        this.startSubmitTime = startSubmitTime;
    }

}
