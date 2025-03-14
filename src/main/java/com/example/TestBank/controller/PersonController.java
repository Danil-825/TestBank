package com.example.TestBank.controller;


import com.example.TestBank.request.PersonRequest;
import com.example.TestBank.response.PersonResponse;
import com.example.TestBank.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public String getAllPersons(Model model) {
        List<PersonResponse> persons = personService.getAllPersons();
        model.addAttribute("persons", persons);
        return "persons";
    }

    @PostMapping
    public String createPerson(@ModelAttribute PersonRequest request) {
        personService.createPerson(request);
        return "redirect:/persons";
    }
}
