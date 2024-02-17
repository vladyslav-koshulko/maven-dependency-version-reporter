package org.example;

import org.apache.maven.model.Dependency;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RemoteDependencyExtractor implements DependencyExtractor {

    private final String mavenRepoPath;
    private static final String FORMAT = "json";
    private static final int rows = 100;

    public RemoteDependencyExtractor(String repoUrl) {
        this.mavenRepoPath = repoUrl;
    }

    @Override
    public List<Dependency> extract(Dependency dependency) {
        String configuredUrl = configureUrl(dependency);
        URL url = getUrlAndHandleExceptions(configuredUrl);
        String response = sendRequests(url);
        return handleResponse(response);
    }

    private String configureUrl(Dependency dependency) {
        return String.format(mavenRepoPath, dependency.getGroupId(), dependency.getArtifactId(), rows, FORMAT);
    }

    private URL getUrlAndHandleExceptions(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            System.out.println("Url exception: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static String sendRequests(URL url) {
        String resonance = "";
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder s = new StringBuilder();
            String input;
            while ((input = in.readLine()) != null) {
                s.append(input);
            }
            resonance = s.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resonance;
    }


    private List<Dependency> handleResponse(String jsonData) {
        return parseJson(jsonData).stream()
                .map(RemoteDependencyExtractor::mapToDependency)
                .collect(Collectors.toList());
    }

    private static Dependency mapToDependency(String version) {
        Dependency dependency = new Dependency();
        dependency.setVersion(version);
        return dependency;
    }
    
    private static List<String> parseJson(String json) {
        JSONObject extResponse = new JSONObject(json);
        JSONArray jsonArray = extResponse
                .getJSONObject("response")
                .getJSONArray("docs");
        return parseVersions(jsonArray);
    }

    private static List<String> parseVersions(JSONArray jsonArray) {
        List<String> versions = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            versions.add(jsonArray.getJSONObject(i).getString("v"));
        }
        return versions;
    }
}