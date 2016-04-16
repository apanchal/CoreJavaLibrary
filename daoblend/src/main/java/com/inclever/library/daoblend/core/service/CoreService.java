package com.inclever.library.daoblend.core.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inclever.library.daoblend.core.dao.query.NamedQuery;
import com.inclever.library.daoblend.core.dao.query.NativeQuery;
import com.inclever.library.daoblend.core.dao.search.ISearch;
import com.inclever.library.daoblend.core.dao.search.SearchResult;
import com.inclever.library.daoblend.core.domain.IPersistable;
import com.inclever.library.daoblend.core.domain.PersistentEntity;

@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public interface CoreService<T extends IPersistable<ID>, ID extends Serializable> {

    // ============================================================
    // ======================Save-Update-Reload Methods ===========
    // ============================================================

    /**
     * @see
     */
    <S extends T> void saveEntity(S entity);

    /**
     * @see
     */
    <S extends T> S mergeEntity(S entity);

    /**
     * @see
     */
    void refreshEntity(T entity);

    // ============================================================
    // ======================DELETE Methods =======================
    // ============================================================

    /**
     * @see
     */
    <S extends T> void deleteEntity(ID id, Class<S> targetedEntity);

    /**
     * @see
     */
    void deleteEntity(T entity);;

    /**
     * @see
     */
    void deleteEntityById(ID id);

    /**
     * @see
     */
    <S extends T> int deleteAllEntities(Class<S> targetedEntityClass);

    /**
     * @see
     */
    int deleteAllEntities();

    // ============================================================
    // ======================FINDER Methods ========================
    // ============================================================

    /**
     * @see
     */
    <S extends T> S findEntity(ID id, Class<S> targetedEntity);

    /**
     * @see
     */
    T findEntityById(ID id);

    /**
     * @see
     */
    List<T> listAllEntities();

    /**
     * @see
     */
    <S extends T> List<T> listAllEntities(Class<S> targetedEntityClass);

    /**
     * @see
     */
    SearchResult searchAndCount(ISearch search);

    /**
     * @see
     */
    @SuppressWarnings("rawtypes")
    List searchEntity(ISearch search);

    /**
     * @see
     */
    @SuppressWarnings("rawtypes")
    List searchEntity(NamedQuery namedQuery);

    /**
     * @see
     */
    @SuppressWarnings("rawtypes")
    List searchEntity(NativeQuery nativeQuery);

    /**
     * @see
     */
    @SuppressWarnings("rawtypes")
    <S extends PersistentEntity> List<S> searchByNativeQuery(String nativeQuery, Class<S> targetedEntityClass);

    /**
     * @see
     */
    public <P> List<P> searchByNativeQueryWithBean(NativeQuery nativeQuery, Class<P> beanClazz);

}
