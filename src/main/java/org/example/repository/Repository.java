package org.example.repository;

import org.json.JSONObject;

public class Repository {

    private final String repoAPI;
    private Integer rows = 10;
    private String format = "json";

    public Repository(String repoAPI) {
        this.repoAPI = repoAPI;
    }

    public String sendRequest(String groupId, String artifactId) {
        return String.format(repoAPI, groupId, artifactId, rows, format);
    }


}
