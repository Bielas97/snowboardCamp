package com.app.dao;

import com.app.dao.generic_dao.AbstractGenericDao;
import com.app.model.entities.Newsletter;
import org.springframework.stereotype.Repository;

@Repository
public class NewsletterDaoImpl extends AbstractGenericDao<Newsletter> implements NewsletterDao {
}
