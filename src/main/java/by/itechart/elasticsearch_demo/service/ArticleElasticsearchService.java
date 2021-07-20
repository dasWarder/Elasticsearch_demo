package by.itechart.elasticsearch_demo.service;

import by.itechart.elasticsearch_demo.dto.ArticleDto;
import by.itechart.elasticsearch_demo.model.Article;
import by.itechart.elasticsearch_demo.repository.ArticleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ArticleElasticsearchService implements ArticleService {

    private final ArticleRepository articleRepository;

    private final RestHighLevelClient elasticClient;

    private final ObjectMapper mapper;

    @Override
    public Article saveArticle(ArticleDto dto) throws JsonProcessingException {

        Article article = new Article();
        article.setTitle(dto.getTitle());
        article.setText(dto.getText());


        Article stored = articleRepository.save(article);

        IndexRequest indexRequest = new IndexRequest();
        indexRequest.id(stored.getId());
        indexRequest.source(mapper.writeValueAsString(indexRequest),  XContentType.JSON);

        return stored;
    }

    @Override
    public List<Article> search(String query) throws IOException {

        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("text", query));

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = elasticClient.search(searchRequest, RequestOptions.DEFAULT);
        List<Article> articles = new ArrayList<>();

        for(SearchHit hit: searchResponse.getHits()) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();

            Article article = new Article();
            article.setTitle((String) sourceAsMap.get("title"));
            article.setText((String) sourceAsMap.get("text"));
            articles.add(article);
        }

        return articles;
    }
}
