package by.itechart.elasticsearch_demo.service;

import by.itechart.elasticsearch_demo.dto.ArticleDto;
import by.itechart.elasticsearch_demo.model.Article;

import java.io.IOException;
import java.util.List;

public interface ArticleService {

    Article saveArticle(ArticleDto dto) throws IOException;

    List<ArticleDto> search(String query) throws IOException;

}
