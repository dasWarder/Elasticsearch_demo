package by.itechart.elasticsearch_demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@NoArgsConstructor
@Document(indexName = "blog")
public class Article {

    @Id
    private String id;

    private String title;

    private String text;
}
