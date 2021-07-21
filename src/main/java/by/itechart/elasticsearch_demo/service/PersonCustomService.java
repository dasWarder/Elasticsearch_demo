package by.itechart.elasticsearch_demo.service;

import by.itechart.elasticsearch_demo.model.Person;

import java.io.IOException;
import java.util.List;

public interface PersonCustomService {

    String createNewIndexLink(Person person) throws IOException;

    List<Person> findPersonsByQuery(String query) throws IOException;
}
