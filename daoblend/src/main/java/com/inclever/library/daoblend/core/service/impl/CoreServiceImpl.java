package com.inclever.library.daoblend.core.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import com.inclever.library.daoblend.core.dao.IPersistentEntityDAO;
import com.inclever.library.daoblend.core.dao.query.NamedQuery;
import com.inclever.library.daoblend.core.dao.query.NativeQuery;
import com.inclever.library.daoblend.core.dao.search.ISearch;
import com.inclever.library.daoblend.core.dao.search.SearchResult;
import com.inclever.library.daoblend.core.domain.IPersistable;
import com.inclever.library.daoblend.core.domain.PersistentEntity;
import com.inclever.library.daoblend.core.service.CoreService;
import com.inclever.library.logging.LogManagerFactory;

@Scope(org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE)
public class CoreServiceImpl<MODEL extends IPersistable<IDENTIFIER>, IDENTIFIER extends Serializable>
        implements CoreService<MODEL, IDENTIFIER> {

    private IPersistentEntityDAO<MODEL, IDENTIFIER> genericJPADAOImpl;

    private Class<MODEL> entityClazz;

    private static final Logger LOGGER = LogManagerFactory.getInstance().getLogger(CoreServiceImpl.class);

    @Autowired()
    public void setGenericJPADAOImpl(
            @Qualifier("genericJPADAOImpl") IPersistentEntityDAO<MODEL, IDENTIFIER> genericJPADAOImpl) {
        this.genericJPADAOImpl = genericJPADAOImpl;
    }

    @SuppressWarnings("unchecked")
    public CoreServiceImpl() {
        super();
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();

        this.entityClazz = (Class<MODEL>) genericSuperclass.getActualTypeArguments()[0];

    }

    @PostConstruct
    public void setEntity() {
        LOGGER.debug("Post Construct Called..");
        genericJPADAOImpl.setEntityClass(entityClazz);
    }

    @Override
    public <S extends MODEL> void saveEntity(S entity) {
        genericJPADAOImpl.persist(entity);

    }

    @Override
    public <S extends MODEL> S mergeEntity(S entity) {
        return genericJPADAOImpl.merge(entity);
    }

    @Override
    public void refreshEntity(MODEL entity) {
        genericJPADAOImpl.refresh(entity);

    }

    @Override
    public <S extends MODEL> void deleteEntity(IDENTIFIER id, Class<S> targetedEntity) {
        genericJPADAOImpl.remove(id, targetedEntity);
    }

    @Override
    public void deleteEntity(MODEL entity) {
        genericJPADAOImpl.remove(entity);

    }

    @Override
    public void deleteEntityById(IDENTIFIER id) {
        genericJPADAOImpl.remove(id);
    }

    @Override
    public <S extends MODEL> int deleteAllEntities(Class<S> targetedEntityClass) {
        return genericJPADAOImpl.removeAll(targetedEntityClass);
    }

    @Override
    public int deleteAllEntities() {
        // TODO Auto-generated method stub
        return genericJPADAOImpl.removeAll();
    }

    @Override
    public <S extends MODEL> S findEntity(IDENTIFIER id, Class<S> targetedEntity) {
        return genericJPADAOImpl.find(id, targetedEntity);
    }

    @Override
    public MODEL findEntityById(IDENTIFIER id) {
        return genericJPADAOImpl.find(id);
    }

    @Override
    public List<MODEL> listAllEntities() {
        return genericJPADAOImpl.findAll();
    }

    @Override
    public <S extends MODEL> List<MODEL> listAllEntities(Class<S> targetedEntityClass) {
        return genericJPADAOImpl.findAll(targetedEntityClass);
    }

    @Override
    public SearchResult searchAndCount(ISearch search) {
        return genericJPADAOImpl.searchAndCount(search);
    }

    @Override
    public List<MODEL> searchEntity(ISearch search) {
        return genericJPADAOImpl.search(search);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List searchEntity(NamedQuery namedQuery) {
        return genericJPADAOImpl.search(namedQuery);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List searchEntity(NativeQuery nativeQuery) {
        return genericJPADAOImpl.search(nativeQuery);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public <S extends PersistentEntity> List<S> searchByNativeQuery(String nativeQuery, Class<S> targetedEntityClass) {
        return genericJPADAOImpl.searchByNativeQuery(nativeQuery, targetedEntityClass);
    }

    @Override
    public <P> List<P> searchByNativeQueryWithBean(NativeQuery nativeQuery, Class<P> beanClazz) {
        return genericJPADAOImpl.searchByNativeQueryWithBean(nativeQuery, beanClazz);
    }

}
