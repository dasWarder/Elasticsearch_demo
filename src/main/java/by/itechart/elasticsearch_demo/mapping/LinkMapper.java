package by.itechart.elasticsearch_demo.mapping;

import by.itechart.elasticsearch_demo.dto.LinkDto;
import by.itechart.elasticsearch_demo.model.Link;
import org.mapstruct.Mapper;

@Mapper
public interface LinkMapper {

    Link linkDtoToLink(LinkDto linkDto);

    LinkDto linkToLinkDto(Link link);

    Iterable<LinkDto> linkListToLinkDtoList(Iterable<Link> links);
}
