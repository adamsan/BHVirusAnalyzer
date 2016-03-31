package hu.adamsan.bionica.competition;

import static hu.adamsan.bionica.competition.Messages.*;
import static hu.adamsan.bionica.competition.utils.ConsoleUtils.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hu.adamsan.bionica.competition.model.SubmissionData;
import hu.adamsan.bionica.competition.utils.FileUtils;

public class NetworkCommunicator {
    private String baseURL = "http://localhost:8080/BHVirusAnalyzerServer";

    // goal: http://localhost:8080/BHVirusAnalyzerServer/addResult?teamName=TestTeam&teamCode=TT01&score=23&startSubmitTime=2016-03-26 18:43:22
    public void submitScore(SubmissionData submissionData) {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpPost post = new HttpPost(baseURL + "/addResult");
            List<NameValuePair> data = prepareNamedValuePairList(submissionData);
            post.setEntity(new UrlEncodedFormEntity(data));
            client.execute(post);

        } catch (HttpHostConnectException ex) {
            // If server is running, no need to alert the students.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<NameValuePair> prepareNamedValuePairList(SubmissionData submissionData) {

        List<NameValuePair> data = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            mapper.setDateFormat(df);
            String asString = mapper.writeValueAsString(submissionData);
            data.add(new BasicNameValuePair("json", asString));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void findServer(Options options) {
        List<String> servers = getServers();
        if (options != null && options.getServer() != null) {
            servers.add(0, options.getServer());
        }
        printlnSlow(SERVER_SEARCH);
        for (String server : servers) {
            if (isAlive(server)) {
                baseURL = server;
                printlnSlow(SERVER_FOUND + "\n");
                return;
            }
        }
        printlnSlow(SERVER_NOT_FOUND);
    }

    private boolean isAlive(String server) {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            URI uri = new URIBuilder(server + "/AppInfo").addParameter("isAlive", "isAlive").build();
            HttpGet request = new HttpGet(uri);
            try (CloseableHttpResponse response = client.execute(request);) {
                if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                    return false;
                }
                if (checkIfResponseFirstLineMatch(response, "ALIVE")) {
                    return true;
                }
            }
        } catch (URISyntaxException | IOException e) {
        }
        return false;
    }

    private boolean checkIfResponseFirstLineMatch(HttpResponse response, String target) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));) {
            String line = br.readLine();
            if (target.equals(line)) {
                return true;
            }
        } catch (IOException e) {
        }
        return false;
    }

    private static List<String> getServers() {
        return FileUtils.readFromResource("/servers.data");
    }
}
