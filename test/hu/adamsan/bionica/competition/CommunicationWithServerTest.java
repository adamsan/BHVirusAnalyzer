package hu.adamsan.bionica.competition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import hu.adamsan.bionica.competition.model.GivenAnswer;
import hu.adamsan.bionica.competition.model.Question;
import hu.adamsan.bionica.competition.model.SubmissionData;

public class CommunicationWithServerTest {

    private NetworkCommunicator networkCommunicator;

    private SubmissionData[] submissionData;

    @Before
    public void setUpBefore() throws Exception {
        networkCommunicator = new NetworkCommunicator();
        networkCommunicator.findServer();
        submissionData = new SubmissionData[3];
        submissionData[0] = new SubmissionData("Best Test team", "BTT001");
        submissionData[0].setScore(4);
        submissionData[1] = new SubmissionData("Lame Test team", "LTT002");
        submissionData[1].setScore(1);
        submissionData[2] = new SubmissionData("Middle Test team", "MTT003");
        submissionData[2].setScore(3);
        
        List<GivenAnswer> answers0 = new ArrayList<>();
        List<GivenAnswer> answers1 = new ArrayList<>();
        List<GivenAnswer> answers2 = new ArrayList<>();
        
        Question q1 = new Question("Mennyi 1+1?", "2", 1);
        Question q2 = new Question("Alma gyümölcs", "igen", 1);
        Question q3 = new Question("Ki fedezte fel a gravitációt?", "Newton", 2);

        answers0.add(new GivenAnswer(1, q1, "2"));
        answers0.add(new GivenAnswer(2, q2, "igen"));
        answers0.add(new GivenAnswer(3, q3, "newton"));

        answers1.add(new GivenAnswer(1, q1, "2"));
        answers1.add(new GivenAnswer(2, q2, "nem"));
        answers1.add(new GivenAnswer(3, q3, "einstein"));

        answers2.add(new GivenAnswer(1, q1, "5"));
        answers2.add(new GivenAnswer(2, q2, "igen"));
        answers2.add(new GivenAnswer(3, q3, "newton"));


        submissionData[0].setGivenAnswers(answers0);
        submissionData[1].setGivenAnswers(answers1);
        submissionData[2].setGivenAnswers(answers2);

    }

    @Test
    public void testSubmitScore() {

        networkCommunicator.submitScore(submissionData[0]);
        networkCommunicator.submitScore(submissionData[1]);
        networkCommunicator.submitScore(submissionData[2]);

    }

}
