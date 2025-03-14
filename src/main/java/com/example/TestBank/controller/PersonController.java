package com.example.TestBank.controller;


import com.example.TestBank.request.PersonRequest;
import com.example.TestBank.response.PersonWithAddressResponse;
import com.example.TestBank.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public String getAllPersons(Model model) {
        List<PersonWithAddressResponse> persons = personService.getAllPersonsWithAddresses();
        model.addAttribute("persons", persons);
        return "persons"; // Имя шаблона Thymeleaf
    }

    @PostMapping
    public String createPerson(@ModelAttribute PersonRequest request) {
        personService.createPerson(request);
        return "redirect:/persons"; // Перенаправление на страницу со списком
    }

    @PostMapping("/add-address")
    public String addAddress(@RequestParam int personId, @RequestParam String address) {
        personService.addAddressToPerson(personId, address);
        return "redirect:/persons";
    }
}

