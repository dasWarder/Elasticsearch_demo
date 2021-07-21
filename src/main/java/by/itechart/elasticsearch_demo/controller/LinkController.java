package by.itechart.elasticsearch_demo.controller;

import by.itechart.elasticsearch_demo.dto.LinkDto;
import by.itechart.elasticsearch_demo.mapping.LinkMapper;
import by.itechart.elasticsearch_demo.model.Link;
import by.itechart.elasticsearch_demo.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/links")
public class LinkController {

    private final LinkService linkService;

    private final LinkMapper mapper;

    @PostMapping("/link")
    public ResponseEntity createLink(@RequestBody LinkDto linkDto) {

        Link link = mapper.linkDtoToLink(linkDto);
        Link storedLink = linkService.createLink(link);
        LinkDto responseDto = mapper.linkToLinkDto(storedLink);

        return ResponseEntity.created(URI.create("/links")).body(responseDto);
    }

    @GetMapping("/link/{linkId}")
    public ResponseEntity getLinkById(@PathVariable("linkId") String linkId) {

        Link linkById = linkService.getLinkById(linkId);
        LinkDto responseDto = mapper.linkToLinkDto(linkById);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/link")
    public ResponseEntity getLinkByTitle(@RequestParam("title") String title) {

        Link linkByTitle = linkService.getLinkByTitle(title);
        LinkDto responseDto = mapper.linkToLinkDto(linkByTitle);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/link/search")
    public ResponseEntity getLinkByDescriptionStartWith(@RequestParam("description") String start) {

        Link responseLink = linkService.findLinkByDescriptionStartWith(start);
        LinkDto responseDto = mapper.linkToLinkDto(responseLink);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/link/{linkId}")
    public ResponseEntity deleteLinkById(@PathVariable("linkId") String linkId) {

        linkService.deleteLinkById(linkId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity getAllLinks() {

        Iterable<Link> links = linkService.getAllLinks();
        Iterable<LinkDto> responseLinks = mapper.linkListToLinkDtoList(links);

        return ResponseEntity.ok(responseLinks);
    }

}
