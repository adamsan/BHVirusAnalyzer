package hu.adamsan.bionica.competition;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import hu.adamsan.bionica.competition.model.SubmissionData;

public class NetworkCommunicator {
    private String serverURL = "http://localhost:8080/BHVirusAnalyzerServer/addResult";

    // goal: http://localhost:8080/BHVirusAnalyzerServer/addResult?teamName=TestTeam&teamCode=TT01&score=23&startSubmitTime=2016-03-26 18:43:22
    public void submitScore(SubmissionData submissionData) {
        URI uri = null;
        try {
            uri = buildURI(serverURL, submissionData);
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }

        HttpClient client = HttpClientBuilder.create().build();
        HttpUriRequest request = new HttpGet(uri);
        try {
            client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private URI buildURI(String serverUrl, SubmissionData submissionData) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(serverURL);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        uriBuilder.addParameter("teamName", submissionData.getTeamName());
        uriBuilder.addParameter("teamCode", submissionData.getTeamCode());
        uriBuilder.addParameter("score", "" + submissionData.getScore());
        uriBuilder.addParameter("startSubmitTime", "" + df.format(submissionData.getStartSubmitTime()));
        return uriBuilder.build();
    }

}
