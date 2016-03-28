package hu.adamsan.bionica.competition;

import static hu.adamsan.bionica.competition.Messages.*;
import static hu.adamsan.bionica.competition.utils.ConsoleUtils.*;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import hu.adamsan.bionica.competition.model.Question;
import hu.adamsan.bionica.competition.model.SubmissionData;
import hu.adamsan.bionica.competition.utils.FileUtils;

// Since converting to manven project, eclipse can't properly export to jar,
// it put's the bundle properties file and the questions file in the wrong place
// to compile to jar:
// mvn clean compile assembly:single
// to run jar:
// java -jar target\BHVirusAnalyzer-0.0.1-SNAPSHOT-jar-with-dependencies.jar
public class Main {
    private Scanner sc = null;
    private String teamName = null;
    private NetworkCommunicator communicator;
    private SubmissionData submissionData;

    public static void main(String[] args) {

        Main main = new Main();
        printlnSlow(START_HEADER_MESSAGE);
        printlnSlow(VERSION_INFO + "\n");
        main.communicator = new NetworkCommunicator();
        main.start(getQuestions());

    }

    private static List<Question> getQuestions() {
        // TODO: maybe reading questions from webservice instead of file?
        String fileName = "/questions.data";
        List<Question> questions = FileUtils.readFromResource(fileName).stream()
                .map(Question::createFromLine)
                .collect(Collectors.toList());
        return questions;
    }

    private void start(List<Question> questions) {
        communicator.findServer();
        int score = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            sc = scanner;
            teamName = getTeamInformation();
            submissionData = new SubmissionData(teamName, teamName);
            printlnSlow(LINE);
            printlnSlow(QUESTIONS_START);
            printlnSlow(LINE);
            int count = 1;
            for (Question question : questions) {
                String response = null;
                while (response == null) {
                    ask(question, count);
                    response = readResponse();
                    if (!confirm(String.format(CONFIRM_ANSWER, count, response))) {
                        response = null;
                    }
                }
                printlnSlow(LINE);
                submitResponse(question, response);
                count++;
                score += question.evaluateAnswer(response);
            }
        }
        submissionData.setScore(score);
        printlnSlow(ENDING_MESSAGE);
        submitScore();
    }

    private void submitResponse(Question question, String response) {
        // TODO Auto-generated method stub
    }

    private void submitScore() {
        printlnSlow(String.format(END_SCORE_MESSAGE, submissionData.getScore()));
        printlnSlow(LINE);

        communicator.submitScore(submissionData);

        // TODO: actually submit score to server. Implement it when server is ready.
        // server will be at: https://bionika-competition-results.herokuapp.com/
        // https://bionika-competition-results.herokuapp.com/addResult?teamName=TestTeam&teamCode=TT01&score=23&startSubmitTime=2016-03-26%2018:43:22
    }

    private String readResponse() {
        String response = "";
        if (sc.hasNextLine()) {
            response = sc.nextLine();
        }
        return response;
    }

    private String getTeamInformation() {
        String teamName = null;
        while (teamName == null) {
            printlnSlow(REQUEST_TEAM_INFO);
            if (sc.hasNextLine()) {
                teamName = sc.nextLine();
                if (teamName.length() == 0) {
                    teamName = null;
                    continue;
                }
            }
            if (!confirm(String.format(CONFIRM_TEAM_NAME, teamName))) {
                teamName = null;
            }
        }
        return teamName;
    }

    private boolean confirm(String message) {
        printlnSlow(message + " (Y/N)");
        if (sc.hasNextLine()) {
            String response = sc.nextLine();
            return response.toUpperCase().equals("Y");
        }
        return false;
    }

    private void ask(Question question, int questionCount) {
        printlnSlow(String.format(QUESTION_NUMBER, questionCount));
        printlnSlow(question.getQuestion());
    }

}
