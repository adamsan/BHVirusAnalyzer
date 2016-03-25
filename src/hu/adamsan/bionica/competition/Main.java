package hu.adamsan.bionica.competition;

import static hu.adamsan.bionica.competition.Messages.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import hu.adamsan.bionica.competition.model.Question;

public class Main {
    private Scanner sc = null;
    private String teamName = null;

    public static void main(String[] args) {

        Main main = new Main();
        System.out.println(START_HEADER_MESSAGE);
        main.start(getQuestions());

    }

    private static List<Question> getQuestions() {
        // TODO: maybe reading questions from webservice instead of file?
        String fileName = "/questions.data";
        try (BufferedReader r = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream(fileName), "utf-8"))) {
            List<Question> questions = r.lines()
                    .map(Question::createFromLine)
                    .collect(Collectors.toList());
            return questions;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Error occured while reading questions.");
    }

    private void start(List<Question> questions) {
        int score = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            sc = scanner;
            teamName = getTeamInformation();
            System.out.println(LINE);
            System.out.println(QUESTIONS_START);
            System.out.println(LINE);
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
                System.out.println(LINE);
                submitResponse(question, response);
                count++;
                score += question.evaluateAnswer(response);
            }
        }
        System.out.println(ENDING_MESSAGE);
        submitScore(score);
    }

    private void submitResponse(Question question, String response) {
        // TODO Auto-generated method stub
    }

    private void submitScore(int score) {
        System.out.println(LINE);
        System.out.println("\nA '" + teamName + "' csapat pontszáma : " + score);

        // TODO: actually submit score to server. Implement it when server is ready.
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
            System.out.println(REQUEST_TEAM_INFO);
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
        System.out.println(message + " (Y/N)");
        if (sc.hasNextLine()) {
            String response = sc.nextLine();
            return response.toUpperCase().equals("Y");
        }
        return false;
    }

    private void ask(Question question, int questionCount) {
        System.out.println("Kérdés [" + questionCount + "]");
        System.out.println(question.getQuestion());
    }

}
