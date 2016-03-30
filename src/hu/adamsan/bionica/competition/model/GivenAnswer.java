package hu.adamsan.bionica.competition.model;

public class GivenAnswer {
    private int answerOrder;
    private Question question;
    private String givenAnswer;

    public GivenAnswer(int answerOrder, Question question, String givenAnswer) {
        super();
        this.answerOrder = answerOrder;
        this.question = question;
        this.givenAnswer = givenAnswer;
    }

    public int getAnswerOrder() {
        return answerOrder;
    }

    public void setAnswerOrder(int answerOrder) {
        this.answerOrder = answerOrder;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(String givenAnswer) {
        this.givenAnswer = givenAnswer;
    }

    @Override
    public String toString() {
        return "GivenAnswer [answerOrder=" + answerOrder + ", question=" + question + ", givenAnswer=" + givenAnswer + "]";
    }

}
