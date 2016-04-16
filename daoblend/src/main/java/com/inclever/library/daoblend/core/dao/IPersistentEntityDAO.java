package com.inclever.library.daoblend.core.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inclever.library.daoblend.core.dao.query.NamedQuery;
import com.inclever.library.daoblend.core.dao.query.NativeQuery;
import com.inclever.library.daoblend.core.dao.search.ISearch;
import com.inclever.library.daoblend.core.dao.search.SearchResult;
import com.inclever.library.daoblend.core.domain.IPersistable;
import com.inclever.library.daoblend.core.domain.PersistentEntity;
import com.inclever.library.daoblend.exception.DaoRuntimeException;

/**
 * IPersistentEntityDAO contract containing the standard set of operations
 * supported by any persistence provider-specific DAO implementation.
 * 
 * <p>
 * 
 * The user is responsible for providing proper transaction support within the
 * context of DAO method calls. The use of a specific transaction strategy based
 * on a transaction model as well as proper transaction attributes always depend
 * on specific business requirements of your project and should be therefore
 * carefully considered in terms of concurrency, performance and data integrity.
 * 
 * <p>
 * 
 * Note that it is possible to query for subclasses of the implicit persistent
 * entity class the DAO works with. This way, one can have a general persistent
 * entity DAO working with a parent entity which is able to query for individual
 * entity subclasses as well.
 * 
 * * @param <MODEL> Type of the persistent entity the DAO works with.
 * 
 * @param <IDENTIFIER>
 *            Java type of persistent entity's primary key column.
 * 
 * @see IPersistable
 * @see PersistentEntity
 * 
 * @author Ashish Panchal (apanchal@inclever.com)
 */

public interface IPersistentEntityDAO<MODEL extends IPersistable<IDENTIFIER>, IDENTIFIER extends Serializable> {

    void setEntityClass(Class<MODEL> entityClazz);

    /**
     * Returns the implicit persistent entity class the DAO works with.
     * 
     * @return Persistent entity class.
     */
    Class<MODEL> getEntityClass();

    // ==========================================================
    // ==================== C-R-U-D Methods ====================
    // ==========================================================

    /**
     * Persists a transient instance.
     * 
     * <p>
     * 
     * Cascade types triggered by this operation: {@link CascadeType#SAVE_UPDATE
     * save-update}, {@link CascadeType#MERGE merge}.
     * 
     * @param entity
     *            Transient or detached instance to save or update.
     */
    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
    <S extends MODEL> void persist(S entity);

    /**
     * updates a transient instance.
     * 
     * <p>
     * 
     * Cascade types triggered by this operation: {@link CascadeType#SAVE_UPDATE
     * save-update}, {@link CascadeType#MERGE merge}.
     * 
     * @param entity
     *            Transient or detached instance to save or update.
     * @return Resulting persistent instance.
     */
    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
    <S extends MODEL> S merge(S entity);

    /**
     * Refreshes a persistent or a detached instance by synchronizing its state
     * with the database.
     * 
     * @param entity
     *            Persistent or detached instance to refresh.
     */
    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
    void refresh(MODEL entity);

    /**
     * Deletes a persistent instance.
     * 
     * <p>
     * 
     * Cascade types triggered by this operation: {@link CascadeType#DELETE
     * delete}.
     * 
     * @param entity
     *            Persistent instance to delete.
     */
    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
    void remove(MODEL entity);

    /**
     * Deletes a persistent instance.
     * 
     * <p>
     * 
     * Cascade types triggered by this operation: {@link CascadeType#DELETE
     * delete}.
     * 
     * @param id
     *            <tt>id</tt> of the persistent instance to delete.
     * @param targetEntityClass
     *            Target persistent entity class.
     */
    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
    <S extends MODEL> void remove(IDENTIFIER id, Class<S> targetedEntity);

    /**
     * Deletes a persistent instance, using the implicit persistent entity
     * class.
     * 
     * <p>
     * 
     * Cascade types triggered by this operation: {@link CascadeType#DELETE
     * delete}.
     * 
     * @param id
     *            <tt>id</tt> of the persistent instance to delete.
     * 
     * @see #delete(Serializable, Class)
     * @see #getEntityClass()
     */
    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
    void remove(IDENTIFIER id);

    /**
     * Deletes all persistent instances.
     * 
     * <p>
     * 
     * Cascade types triggered by this operation: {@link CascadeType#DELETE
     * delete}.
     * 
     * @param targetEntityClass
     *            Target persistent entity class.
     * @return Number of persistent instances deleted.
     */
    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
    <S extends MODEL> int removeAll(Class<S> targetedEntityClass);

    /**
     * Deletes all persistent instances, using the implicit persistent entity
     * class.
     * 
     * <p>
     * 
     * Cascade types triggered by this operation: {@link CascadeType#DELETE
     * delete}.
     * 
     * @return Number of persistent instances deleted.
     * 
     * @see #deleteAll(Class)
     * @see #getEntityClass()
     */
    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
    int removeAll();

    // =========================================================================
    // ======== Finder Methods ========
    // =========================================================================

    /**
     * Retrieves a persistent instance.
     * 
     * @param id
     *            <tt>id</tt> of the persistent instance to retrieve.
     * @param targetEntityClass
     *            Target persistent entity class.
     * @return Resulting persistent instance or <tt>null</tt> in case the
     *         requested instance was not found.
     */
    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT, readOnly = true, rollbackFor = Exception.class)
    <S extends MODEL> S find(IDENTIFIER id, Class<S> targetedEntity);

    /**
     * Retrieves a persistent instance, using the implicit persistent entity
     * class.
     * 
     * @param id
     *            <tt>id</tt> of the persistent instance to retrieve.
     * @return Resulting persistent instance or <tt>null</tt> in case the
     *         requested instance was not found.
     * 
     * @see #get(Serializable, Class)
     * @see #getEntityClass()
     */
    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT, readOnly = true, rollbackFor = Exception.class)
    MODEL find(IDENTIFIER id);

    /**
     * Retrieves all persistent instances.
     * 
     * @param targetEntityClass
     *            Target persistent entity class.
     * @return Resulting list of persistent instances.
     */
    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT, readOnly = true, rollbackFor = Exception.class)
    <S extends MODEL> List<MODEL> findAll(Class<S> targetedEntityClass);

    /**
     * Retrieves all persistent instances, using the implicit persistent entity
     * class.
     * 
     * @return Resulting list of persistent instances.
     * 
     * @see #getAll(Class)
     * @see #getEntityClass()
     */
    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT, readOnly = true, rollbackFor = Exception.class)
    List<MODEL> findAll();


    // ==========================================================
    // ==================== SEARCH Methods ======================
    // ==========================================================

    /**
     * 
     * @param targetedEntity
     * @return
     * @throws DaoRuntimeException
     * 
     *             Note: DO NOT USE THIS METHOD, ITS NOT Supported. Will result
     *             in DaoRuntimeException with message 'Unimplemented Feature,
     *             try in higher version then the current DAOBleand version.'
     */
    <S extends MODEL> int countAll(Class<S> targetedEntity);

    /**
     * 
     * @return
     * @throws DaoRuntimeException
     * 
     *             Note: DO NOT USE THIS METHOD, ITS NOT Supported. Will result
     *             in DaoRuntimeException with message 'Unimplemented Feature,
     *             try in higher version then the current DAOBleand version.'
     */
    int countAll();

    /**
     * Returns the total number of results that would be returned using the
     * given <code>ISearch</code> if there were no paging or maxResult limits.
     * 
     * @see ISearch
     */
    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT, readOnly = true, rollbackFor = Exception.class)
    int count(ISearch search);

    /**
     * Search for objects based on the search parameters in the specified
     * <code>ISearch</code> object.
     * 
     * @see ISearch
     */
    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT, readOnly = true, rollbackFor = Exception.class)
    List<MODEL> search(ISearch search);

    /**
     * Returns a <code>SearchResult</code> object that includes the list of
     * results like <code>search()</code> and the total length like
     * <code>searchLength</code>.
     * 
     * @see ISearch
     */
    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT, readOnly = true, rollbackFor = Exception.class)
    SearchResult searchAndCount(ISearch search);

    /**
     * method returns List of IPersistable, based on passed named query
     * 
     * @param namedQuery
     *            - @see NameQuery
     * @return
     * @throws DaoRuntimeException
     */
    @SuppressWarnings("rawtypes")
    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT, readOnly = true, rollbackFor = Exception.class)
    List search(NamedQuery namedQuery);

    /**
     * Method returns the raw list of records being fetched by the passed native
     * query. Returned List will have array of Object, and each object in the
     * array represents the row in result set.
     * 
     * In order, to get actual value need to iterate each array Object
     * separately. The value inside array object is in the same order as defined
     * in native query.
     * 
     * <code>
     * List list = ...
     * 	for(Object o:list) {
     *  Object[] row = (Object[]) o;
     * 	 for(Object o : row) {
     * 	   System.out.println(o);
     * 	 }
     *  }
     * </code>
     * 
     * @param nativeQuery
     * @return List of Object[]
     * @throws DaoRuntimeException
     */
    @SuppressWarnings("rawtypes")
    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT, readOnly = true, rollbackFor = Exception.class)
    List search(NativeQuery nativeQuery);

    /**
     * 
     * @param nativeQuery
     * @param targetedEntityClass
     * @return
     * @throws DaoRuntimeException
     */
    @SuppressWarnings("rawtypes")
    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT, readOnly = true, rollbackFor = Exception.class)
    <S extends PersistentEntity> List<S> searchByNativeQuery(String nativeQuery, Class<S> targetedEntityClass);

    /**
     * 
     * @param nativeQuery
     * @param beanClazz
     * @return
     */
    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT, readOnly = true, rollbackFor = Exception.class)
    public <T> List<T> searchByNativeQueryWithBean(NativeQuery nativeQuery, Class<T> beanClazz);

}
