package com.recruitment.consdata.news.config;

import com.recruitment.consdata.news.downloader.ArticleReponseDto;
import com.recruitment.consdata.news.model.News;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NewsBeans {
    @Bean
    public ModelMapper newsModelMapper() {
        PropertyMap<ArticleReponseDto, News> newsPropertyMap = new PropertyMap<ArticleReponseDto, News>() {
            @Override
            protected void configure() {
                map(source.getPublishedAt(), destination.getDate());
                map(source.getSource().getName(), destination.getSourceName());
                map(source.getUrl(), destination.getArticleUrl());
                map(source.getUrlToImage(), destination.getImageUrl());
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addMappings(newsPropertyMap);

        return modelMapper;
    }
}
