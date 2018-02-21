package com.app.dao;

import com.app.dao.generic_dao.AbstractGenericDao;
import com.app.model.entities.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoImpl extends AbstractGenericDao<Person> implements PersonDao{
}
