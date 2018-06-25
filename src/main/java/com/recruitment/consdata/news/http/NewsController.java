package com.recruitment.consdata.news.http;

import com.recruitment.consdata.news.model.News;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<News>> findAll() {
        List<News> newsList = new ArrayList<>();
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }
}
