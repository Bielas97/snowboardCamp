package com.app.controllers;

import com.app.dao.NewsletterDao;
import com.app.dao.PersonDao;
import com.app.model.Country;
import com.app.model.Gender;
import com.app.model.entities.Newsletter;
import com.app.model.entities.Person;
import com.app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MyController {
    private PersonDao personDao;
    private PersonService personService;
    private NewsletterDao newsletterDao;

    @Autowired
    public MyController(PersonDao personDao, PersonService personService, NewsletterDao newsletterDao) {
        this.personDao = personDao;
        this.personService = personService;
        this.newsletterDao = newsletterDao;
    }

    @GetMapping("/")
    public String welcome(Model model)
    {
        model.addAttribute("newsletter", new Newsletter());
        return "welcome";
    }

    @PostMapping("/welcome")
    public String insertNewsletter(@ModelAttribute Newsletter newsletter){
        System.out.println(newsletter);
        newsletterDao.add(newsletter);
        return "redirect:/";
    }

    @GetMapping("/registration")
    public String registrate(Model model){
        model.addAttribute("person", new Person());
        model.addAttribute("countries", Country.values());
        model.addAttribute("genders", Gender.values());

        return "registration";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("person", new Person());

        return "login";
    }

    @PostMapping("/login")
    public String insertLogin(@ModelAttribute Person person){
        List<Person> people = personService.findByEmail(person.getEmail());
        if(people.size() == 1){
            Person p = people.get(0);
            if(p.getPassword().equals(person.getPassword())){
                return ""; // przekierwuje na bezpieczna strone
            }
        }

        return "/errorLoginPage";
    }

    @PostMapping("/registration")
    public String insertUser(@ModelAttribute Person person){
        personDao.add(person);
        System.out.println(person);
        return "redirect:/";
    }
}
