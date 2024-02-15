package org.example.repository;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RepositoryConfigurer {
    private static final org.apache.maven.model.Repository repository = new org.apache.maven.model.Repository();

    private final List<String> repositoryUrls = new ArrayList<>(List.of(repository.getUrl()));

    private Log logger = new SystemStreamLog();


    public RepositoryConfigurer() {
    }

    public void setLogger(Log logger) {
        this.logger = logger;
    }


    public void addRepository(String url) {
        repositoryUrls.add(Objects.requireNonNull(url));
    }

    public void addRepository(List<String> url) {
        repositoryUrls.addAll(Objects.requireNonNull(url));
    }

    public List<String> getRepositories() {
        return repositoryUrls;
    }

    public List<URL> gUrls() {
        return toURLs();
    }

    private List<URL> toURLs() {
        List<URL> urls = new ArrayList<>();
        for (String repositoryUrl : repositoryUrls) {
            handleUrlExceptions(urls, repositoryUrl);
        }
        return urls;
    }

    private void handleUrlExceptions(List<URL> urls, String url) {
        try {
            urls.add(new URL(url));
        } catch (MalformedURLException e) {
            logger.info("Url is not a repository [" + url + "]. ", e);
        }
    }


}
