package by.itechart.elasticsearch_demo.service;

import by.itechart.elasticsearch_demo.model.Link;
import by.itechart.elasticsearch_demo.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.Assert.notNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;

    @Override
    public Link createLink(Link link) {

        notNull(link, "The link must be not null");
        log.info("Create a new link");

        Link storedLink = linkRepository.save(link);

        return storedLink;
    }

    @Override
    public Link getLinkById(String id) {

        notNull(id, "The id must be not null");
        log.info("Receive a link by ID = {}", id);

        Optional<Link> possibleLink = linkRepository.findById(id);
        Link validLink = possibleLink.orElseThrow(NullPointerException::new);

        return validLink;
    }

    @Override
    public Link getLinkByTitle(String title) {

        notNull(title, "The title must be not null");
        log.info("Receive a link by title = {}", title);

        Link validLinkByTitle = linkRepository.findLinkByTitle(title);

        return validLinkByTitle;
    }

    @Override
    public Iterable<Link> getAllLinks() {

        log.info("Receive all links");

        Iterable<Link> links = linkRepository.findAll();

        return links;
    }

    @Override
    public void deleteLinkById(String id) {

        notNull(id, "The Id must be not null");
        log.info("Delete a link by id = {}", id);

        linkRepository.deleteById(id);
    }

    @Override
    public Link findLinkByDescriptionStartWith(String start) {

        notNull(start, "The start string must be not null");
        log.info("Receive a link by description");

        Link responseLink = linkRepository.findLinkByDescriptionStartingWith(start);

        return responseLink;
    }
}
