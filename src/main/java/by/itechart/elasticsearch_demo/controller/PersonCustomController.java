package by.itechart.elasticsearch_demo.controller;


import by.itechart.elasticsearch_demo.dto.LinkDto;
import by.itechart.elasticsearch_demo.dto.PersonDto;
import by.itechart.elasticsearch_demo.mapping.LinkMapper;
import by.itechart.elasticsearch_demo.mapping.PersonMapper;
import by.itechart.elasticsearch_demo.model.Link;
import by.itechart.elasticsearch_demo.model.Person;
import by.itechart.elasticsearch_demo.service.PersonCustomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/custom")
public class PersonCustomController {


    private final PersonCustomService customService;

    private final PersonMapper mapper;

    @PutMapping
    public ResponseEntity createIndex(@RequestBody PersonDto dto) throws IOException {

        Person person = mapper.personDtoToPerson(dto);
        String response = customService.createNewIndexLink(person);

        return ResponseEntity.created(URI.create("/custom")).body(response);
    }

    @GetMapping
    public ResponseEntity search(@RequestParam("query") String query) throws IOException {

        List<Person> persons = customService.findPersonsByQuery(query);
        List<PersonDto> response = mapper.personListToPersonDtoList(persons);

        return ResponseEntity.ok(response);
    }



}
