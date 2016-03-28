package hu.adamsan.bionica.competition.model;

public class Question {
    private String question;
    private String correctAnswer;
    private int pointValue;

    public Question() {}

    public Question(String question, String correctAnswer, int pointValue) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.pointValue = pointValue;
    }

    public static Question createFromLine(String line) {
        String[] split = line.split("##");
        return new Question(split[0], split[1], Integer.parseInt(split[2]));
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getPointValue() {
        return pointValue;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    public int evaluateAnswer(String response) {
        return response != null && correctAnswer != null && response.trim().toUpperCase().equals(correctAnswer.trim().toUpperCase()) ? pointValue : 0;
    }

}
