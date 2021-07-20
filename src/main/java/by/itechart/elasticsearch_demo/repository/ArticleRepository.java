package by.itechart.elasticsearch_demo.repository;

import by.itechart.elasticsearch_demo.model.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {

    Optional<Article> findByTitle(String title);
}
