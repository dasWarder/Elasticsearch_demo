package by.itechart.elasticsearch_demo.controller;

import by.itechart.elasticsearch_demo.dto.ArticleDto;
import by.itechart.elasticsearch_demo.model.Article;
import by.itechart.elasticsearch_demo.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity addArticle(@RequestBody ArticleDto dto) throws IOException {

        Article article = articleService.saveArticle(dto);

        return ResponseEntity.created(URI.create("/"))
                                        .body(article);
    }

    @GetMapping
    public ResponseEntity getFilteredInfo(@RequestParam("query") String query) throws IOException {

        List<ArticleDto> search = articleService.search(query);

        return ResponseEntity.ok()
                             .body(search);
    }
}
