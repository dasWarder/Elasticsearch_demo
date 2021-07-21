package by.itechart.elasticsearch_demo.repository;

import by.itechart.elasticsearch_demo.model.Link;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LinkRepository extends ElasticsearchRepository<Link, String> {

    Link findLinkByTitle(String title);

    Link findLinkByDescriptionStartingWith(String start);
}
