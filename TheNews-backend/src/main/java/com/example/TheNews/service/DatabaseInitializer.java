package com.example.TheNews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class DatabaseInitializer {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @PostConstruct
    public void init() {
        transactionTemplate.execute(status -> {
            entityManager.createNativeQuery("ALTER TABLE comments ALTER COLUMN comment_text TYPE VARCHAR(1000)").executeUpdate();
            return null;
        });
    }
}
