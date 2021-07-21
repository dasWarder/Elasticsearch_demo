package by.itechart.elasticsearch_demo.service;

import by.itechart.elasticsearch_demo.model.Link;
import by.itechart.elasticsearch_demo.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonCustomServiceImpl implements PersonCustomService {

    private final ObjectMapper mapper;

    private final RestHighLevelClient restClient;

    private static final String INDEX = "persons";

    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);

    @Override
    public String createNewIndexLink(Person person) throws IOException {

        IndexRequest request = new IndexRequest(INDEX);

        if(person.getId() == null) {
            Long id = ID_GENERATOR.getAndIncrement();

            person.setId(String.valueOf(id));
            request.id(String.valueOf(id));
        } else {

            request.id(person.getId());
        }
        request.source(mapper.writeValueAsString(person), XContentType.JSON);

        IndexResponse index = restClient.index(request, RequestOptions.DEFAULT);

        return index.toString();
    }

    @Override
    public List<Person> findPersonsByQuery(String query) throws IOException {

        SearchRequest request = new SearchRequest(INDEX);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("surname", query));
        request.source(builder);

        SearchResponse response = restClient.search(request, RequestOptions.DEFAULT);
        List<Person> persons = new ArrayList<>();

        for (SearchHit hit: response.getHits()) {

            Map<String, Object> sourceAsMap = hit.getSourceAsMap();

            Person person = new Person();
            person.setId((String) sourceAsMap.get("id"));
            person.setName((String) sourceAsMap.get("name"));
            person.setSurname((String) sourceAsMap.get("surname"));
            persons.add(person);
        }

        return persons;
    }
}
