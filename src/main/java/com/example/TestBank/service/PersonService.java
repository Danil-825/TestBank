package com.example.TestBank.service;

import com.example.TestBank.entity.Person;
import com.example.TestBank.repository.PersonRepository;
import com.example.TestBank.request.PersonRequest;
import com.example.TestBank.response.PersonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class PersonService {

    private final PersonRepository personRepository;

    public void createPerson(PersonRequest request) {
        Person person = new Person();
        person.setName(request.getName());
        person.setAge(request.getAge());
        person.setGender(request.getGender());
        personRepository.save(person);

    }

    public List<PersonResponse> getAllPersons() {
        return personRepository.findAll().stream()
                .map(person -> new PersonResponse(person.getId(), person.getName(), person.getAge(), person.getGender()))
                .collect(Collectors.toList());
    }
}
