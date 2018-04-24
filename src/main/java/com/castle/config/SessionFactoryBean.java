package com.castle.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;

public class SessionFactoryBean {
	
    @PersistenceContext
    private EntityManager em;

	@Bean
	public SessionFactory sessionFactory() {
		return em.unwrap(SessionFactory.class);
	}
}
