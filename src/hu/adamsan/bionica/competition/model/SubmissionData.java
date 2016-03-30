package hu.adamsan.bionica.competition.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubmissionData {
    private String teamName;
    private String teamCode;
    private int score;
    private Date startSubmitTime;
    private List<GivenAnswer> givenAnswers;

    public List<GivenAnswer> getGivenAnswers() {
        return givenAnswers;
    }

    public void setGivenAnswers(List<GivenAnswer> givenAnswers) {
        this.givenAnswers = givenAnswers;
    }

    public SubmissionData(String teamName, String teamCode) {
        this.teamName = teamName;
        this.teamCode = teamCode;
        this.startSubmitTime = new Date();
        this.givenAnswers = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "SubmissionData [teamName=" + teamName + ", teamCode=" + teamCode + ", score=" + score + ", startSubmitTime=" + startSubmitTime
                + ", givenAnswers=" + givenAnswers + "]";
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
