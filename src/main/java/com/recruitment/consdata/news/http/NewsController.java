package com.recruitment.consdata.news.http;

import com.recruitment.consdata.news.downloader.NewsDownloader;
import com.recruitment.consdata.news.model.News;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    private NewsDownloader newsDownloader;

    public NewsController(NewsDownloader newsDownloader) {
        this.newsDownloader = newsDownloader;
    }

    @GetMapping({"/{country}/{category}", "/{country}"})
    public ResponseEntity<ResponseDto> find(
            @PathVariable String country,
            @PathVariable(required = false) String category
    ) {
        ResponseDto response = new ResponseDto();
        response.setCountry(country);
        response.setCategory(category);
        List<News> newsList = newsDownloader.getList(country, category);
        response.setArticles(newsList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
