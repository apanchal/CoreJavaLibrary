package com.inclever.library.daoblend.core.dao.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.slf4j.Logger;

import com.inclever.library.logging.LogManagerFactory;

//@Service
//@Scope(BeanDefinition.SCOPE_SINGLETON)
public class JpaUtil {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    private static final Logger logger = LogManagerFactory.getInstance()
	    .getLogger(JpaUtil.class);

    /*
     * static { try { emf = Persistence.createEntityManagerFactory("MyPu");
     * 
     * } catch (Throwable ex) {
     * logger.error("Initial SessionFactory creation failed", ex); throw new
     * ExceptionInInitializerError(ex); } }
     */

    private JpaUtil() {
	super();
	logger.debug("----- JpaUtil Invoked--------------");
    }

    public void setEntityManagerFactory(EntityManagerFactory factory) {
	logger.debug("Entity Manager Factory set....");
	entityManagerFactory = factory;

    }

    public EntityManager getEntityManager() {
	logger.debug("entity manager factory " + entityManagerFactory);
	if (entityManagerFactory != null) {
	    return entityManagerFactory.createEntityManager();
	} else {
	    return null;
	}
    }
}
