package com.recruitment.consdata.news.downloader;

import com.recruitment.consdata.news.model.News;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@Service
@ConfigurationProperties(prefix = "downloader")
public class NewsDownloader {
    private RestTemplate restTemplate = new RestTemplate();
    private String scheme;
    private String baseUrl;
    private String path;
    private String apiKey;

    private ModelMapper newsModelMapper;

    public NewsDownloader(ModelMapper newsModelMapper) {
        this.newsModelMapper = newsModelMapper;
    }

    public List<News> getList(String country) {
        URI uri = prepareURI(country, null);
        return download(uri);
    }

    public List<News> getList(String country, String category) {
        URI uri = prepareURI(country, category);
        return download(uri);
    }

    private List<News> download(URI uri) {
        ResponseDto response = restTemplate
                .exchange(
                        uri,
                        HttpMethod.GET,
                        getHeaders(),
                        new ParameterizedTypeReference<ResponseDto>() {}
                )
                .getBody();

        return newsModelMapper.map(response.getArticles(), new TypeToken<List<News>>() {}.getType());
    }

    private HttpEntity<String> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        return new HttpEntity<>("parameters", headers);
    }

    private URI prepareURI(String country, String category) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        uriComponentsBuilder.scheme(scheme).host(baseUrl).path(path).queryParam("apiKey", apiKey);

        if (country != null) {
            uriComponentsBuilder.queryParam("country", country);
        }

        if (category != null) {
            uriComponentsBuilder.queryParam("category", category);
        }

        return uriComponentsBuilder.build().toUri();
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
