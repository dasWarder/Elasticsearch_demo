package by.itechart.elasticsearch_demo.mapping;

import by.itechart.elasticsearch_demo.dto.PersonDto;
import by.itechart.elasticsearch_demo.model.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PersonMapper {

    Person personDtoToPerson(PersonDto dto);

    PersonDto personToPersonDto(Person person);

    List<PersonDto> personListToPersonDtoList(List<Person> persons);
}
