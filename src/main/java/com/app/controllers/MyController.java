package com.app.controllers;

import com.app.dao.PersonDao;
import com.app.model.Country;
import com.app.model.Gender;
import com.app.model.entities.Person;
import com.app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyController {
    private PersonDao personDao;
    private PersonService personService;

    @Autowired
    public MyController(PersonDao personDao, PersonService personService) {
        this.personDao = personDao;
        this.personService = personService;
    }

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/registration")
    public String registrate(Model model){
        model.addAttribute("person", new Person());
        model.addAttribute("countries", Country.values());
        model.addAttribute("genders", Gender.values());

        return "registration";
    }

    @PostMapping("/registration")
    public String insertUser(@ModelAttribute Person person){
        personDao.add(person);
        System.out.println(person);
        return "redirect:/";
    }
}
