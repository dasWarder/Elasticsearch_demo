package by.itechart.elasticsearch_demo.service;

import by.itechart.elasticsearch_demo.model.Link;

import java.util.List;

public interface LinkService {

    Link createLink(Link link);

    Link getLinkById(String id);

    Link getLinkByTitle(String title);

    Iterable<Link> getAllLinks();

    void deleteLinkById(String id);

    Link findLinkByDescriptionStartWith(String start);

}
