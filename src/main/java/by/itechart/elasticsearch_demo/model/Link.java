package by.itechart.elasticsearch_demo.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.index.VersionType;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "links", createIndex = false, versionType = VersionType.INTERNAL)
public class Link implements Serializable {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String title;

    private String description;

}
