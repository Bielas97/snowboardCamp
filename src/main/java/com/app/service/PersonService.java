package com.app.service;

import com.app.dao.PersonDao;
import com.app.model.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonService {
    PersonDao personDao;

    @Autowired
    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public List<Person> findByEmail(String email){
        return personDao.findAll().stream().filter(person -> person.getEmail().equalsIgnoreCase(email)).collect(Collectors.toList());
    }
}
