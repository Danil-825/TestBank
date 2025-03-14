package com.example.TestBank.service;

import com.example.TestBank.entity.Address;
import com.example.TestBank.entity.Person;
import com.example.TestBank.repository.PersonRepository;
import com.example.TestBank.request.PersonRequest;
import com.example.TestBank.response.PersonWithAddressResponse;
import com.example.TestBank.response.AddressResponse;
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

        // Обработка адреса
        if (request.getAddress() != null && !request.getAddress().isEmpty()) {
            String[] addressParts = request.getAddress().split(",");
            if (addressParts.length == 3) {
                Address address = new Address();
                address.setCity(addressParts[0].trim());
                address.setStreet(addressParts[1].trim());
                address.setHouseNumber(addressParts[2].trim());
                address.setPerson(person);
                person.getAddresses().add(address);
            }
        }

        personRepository.save(person);
    }

    public void addAddressToPerson(int personId, String address) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        String[] addressParts = address.split(",");
        if (addressParts.length == 3) {
            Address newAddress = new Address();
            newAddress.setCity(addressParts[0].trim());
            newAddress.setStreet(addressParts[1].trim());
            newAddress.setHouseNumber(addressParts[2].trim());
            newAddress.setPerson(person);
            person.getAddresses().add(newAddress);
            personRepository.save(person);
        } else {
            throw new IllegalArgumentException("Invalid address format");
        }
    }

    public List<PersonWithAddressResponse> getAllPersonsWithAddresses() {
        return personRepository.findAll().stream()
                .map(person -> new PersonWithAddressResponse(
                        person.getId(),
                        person.getName(),
                        person.getAge(),
                        person.getGender(),
                        person.getAddresses().stream()
                                .map(address -> new AddressResponse(
                                        address.getCity(),
                                        address.getStreet(),
                                        address.getHouseNumber()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}