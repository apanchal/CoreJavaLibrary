/**
 * 
 */
package com.inclever.library.daoblend.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.inclever.library.daoblend.core.dao.IPersistentEntityDAO;
import com.inclever.library.daoblend.core.dao.query.NamedQuery;
import com.inclever.library.daoblend.core.dao.query.NativeQuery;
import com.inclever.library.daoblend.core.dao.search.ISearch;
import com.inclever.library.daoblend.core.dao.search.JPAAnnotationMetadataUtil;
import com.inclever.library.daoblend.core.dao.search.JPASearchProcessor;
import com.inclever.library.daoblend.core.dao.search.SearchResult;
import com.inclever.library.daoblend.core.dao.sql.mapping.conversion.DaoConversionManager;
import com.inclever.library.daoblend.core.domain.IPersistable;
import com.inclever.library.daoblend.core.domain.PersistentEntity;
import com.inclever.library.daoblend.exception.DaoExceptionHandler;
import com.inclever.library.daoblend.exception.DaoRuntimeException;
import com.inclever.library.exception.annotation.ExceptionHandler;
import com.inclever.library.exception.core.HandlerUtil;
import com.inclever.library.logging.LogManagerFactory;

/**
 * This class provides a generic default implementation for many functionalities
 * used in persistence mechanisms. It offers standard CRUD functions for JPA
 * applications plus count() and findInRange() functions as they are frequently
 * used in Web applications.
 * 
 * 
 * @author Ashish Panchal
 * @since 1.0
 * 
 * @param <MODEL>
 *            the type to be persisted (i.e. Person.class)
 * @param <IDENTIFIER>
 *            the identifier type
 */

@Repository("genericJPADAOImpl")
@Scope(org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE)
@ExceptionHandler(handler = DaoExceptionHandler.class)
public class GenericJPADAOImpl<MODEL extends IPersistable<IDENTIFIER>, IDENTIFIER extends Serializable>
        implements IPersistentEntityDAO<MODEL, IDENTIFIER> {

    private static final Logger LOGGER = LogManagerFactory.getInstance().getLogger(GenericJPADAOImpl.class);

    private static final String NO_ENTITY_CLASS = "No Entity Class Found!!";

    private Class<MODEL> entityClass;

    @PersistenceContext
    private EntityManager entityManager;

    // @Autowired
    // private JpaUtil jpaUtil;

    private JPASearchProcessor jpaSearchProcessor;

    public GenericJPADAOImpl() {
        //
        // this.entityClass = (Class<MODEL>) DaoUtil.getTypeArguments(
        // CoreServiceImpl.class, CoreServiceImpl.class).get(0);

        jpaSearchProcessor = new JPASearchProcessor(new JPAAnnotationMetadataUtil());
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * @see com.nividous.library.daoblend.core.dao.IPersistentEntityDAO#setEntityClass()
     */
    public void setEntityClass(Class<MODEL> entityClazz) {
        this.entityClass = entityClazz;
    }

    /**
     * @see com.nividous.library.daoblend.core.dao.IPersistentEntityDAO#getEntityClass()
     */
    @Override
    public final Class<MODEL> getEntityClass() {
        return entityClass;
    }

    // =========================================================================
    // ======== C-R-U-D Methods Implementation ========
    // =========================================================================

    /**
     * @see com.nividous.library.daoblend.core.dao.IPersistentEntityDAO#persist(IPersistable)
     */
    @Override
    public final <S extends MODEL> void persist(S entity) {
        LOGGER.trace("Persisting entity {}", entity);
        if (entity == null) {
            throw DaoRuntimeException.cannotPerformCRUDOnNullEntity();
        }
        try {
            entityManager.persist(entity);
        } catch (Exception exception) {
            HandlerUtil.handle(exception);
            Throwable rootCause = Throwables.getRootCause(exception);
            throw DaoRuntimeException.cannotSaveEntity(getEntityClassName(), rootCause.getMessage(), exception);

        } finally {
            closeEntityManager(entityManager);
        }

    }

    /**
     * @see com.nividous.library.daoblend.core.dao.IPersistentEntityDAO#merge(IPersistable)
     */
    @SuppressWarnings("unchecked")
    @Override
    public final <S extends MODEL> S merge(S entity) {
        LOGGER.trace("Merging entity {}", entity);
        if (entity == null) {
            throw DaoRuntimeException.cannotPerformCRUDOnNullEntity();
        }
        try {
            return entityManager.merge(entity);
        } catch (Exception exception) {
            HandlerUtil.handle(exception);
            Throwable rootCause = Throwables.getRootCause(exception);
            throw DaoRuntimeException.cannotUpdateEntity(getEntityClassName(),
                    String.valueOf(((PersistentEntity<S>) entity).getId()), rootCause.getMessage(), exception);

        } finally {
            closeEntityManager(entityManager);
        }

    }

    /**
     * @
     * 
     *   @see
     *   com.nividous.library.daoblend.core.dao.IPersistentEntityDAO#refresh(IPersistable)
     */
    @SuppressWarnings("unchecked")
    @Override
    public final void refresh(MODEL entity) {
        LOGGER.trace("Refreshing entity {}", entity);
        if (entity == null) {
            throw DaoRuntimeException.cannotPerformCRUDOnNullEntity();
        }
        try {
            entityManager.refresh(entity);
        } catch (Exception exception) {
            HandlerUtil.handle(exception);
            Throwable rootCause = Throwables.getRootCause(exception);
            throw DaoRuntimeException.entityRefreshException(getEntityClassName(),
                    String.valueOf(((PersistentEntity<MODEL>) entity).getId()), rootCause.getMessage(), exception);
        } finally {
            closeEntityManager(entityManager);
        }

    }

    /**
     * @see com.nividous.library.daoblend.core.dao.IPersistentEntityDAO#remove(IPersistable)
     */
    @SuppressWarnings("unchecked")
    @Override
    public final void remove(MODEL entity) {
        if (entity == null) {
            throw DaoRuntimeException.cannotPerformCRUDOnNullEntity();
        }
        LOGGER.trace("Removing entity {}", entity.getId());
        try {
            IPersistable<MODEL> a = entityManager.getReference(entity.getClass(), entity.getId());
            LOGGER.trace("Reference is {}", a.getId());
            entityManager.remove(a);
        } catch (Exception exception) {
            HandlerUtil.handle(exception);
            Throwable rootCause = Throwables.getRootCause(exception);
            throw DaoRuntimeException.cannotDeleteEntity(getEntityClassName(),
                    String.valueOf(((PersistentEntity<MODEL>) entity).getId()), rootCause.getMessage(), exception);
        } finally {
            closeEntityManager(entityManager);
        }

    }

    /**
     * @
     * 
     *   @see
     *   com.nividous.library.daoblend.core.dao.IPersistentEntityDAO#remove(Serializable,
     *   Class)
     */
    @Override
    public final <S extends MODEL> void remove(IDENTIFIER id, Class<S> targetedEntity) {
        remove(find(id, targetedEntity));
    }

    /**
     * @
     * 
     *   @see
     *   com.nividous.library.daoblend.core.dao.IPersistentEntityDAO#remove(Serializable)
     */
    @Override
    public final void remove(IDENTIFIER id) {
        remove(id, entityClass);
    }

    /**
     * @
     * 
     *   @see
     *   com.nividous.library.daoblend.core.dao.IPersistentEntityDAO#removeAll(Class)
     */
    @Override
    public final <S extends MODEL> int removeAll(Class<S> targetedEntityClass) {
        List<MODEL> instancesToRemove = findAll(targetedEntityClass);
        int entityRemoved = -1;
        if (instancesToRemove != null) {
            for (MODEL instance : instancesToRemove) {
                remove(instance);
            }
            entityRemoved = instancesToRemove.size();
        }
        return entityRemoved;
    }

    /**
     * @
     * 
     *   @see
     *   com.nividous.library.daoblend.core.dao.IPersistentEntityDAO#removeAll()
     */
    @Override
    public final int removeAll() {
        return removeAll(entityClass);
    }

    // =========================================================================
    // ======== Finder Methods Implementation ========
    // =========================================================================

    /**
     * @see com.nividous.library.daoblend.core.dao.IPersistentEntityDAO#find(Serializable,
     *      Class)
     */
    @Override
    public final <S extends MODEL> S find(IDENTIFIER id, Class<S> targetedEntity) {
        LOGGER.trace("Finding Entity [{}] with Persistence Identifier [{}]", targetedEntity, id);
        if (targetedEntity == null) {
            throw DaoRuntimeException.cannotPerformCRUDOnNullEntity();
        }

        if (id == null) {
            throw DaoRuntimeException.cannotFindEntity(targetedEntity.toString(), null, null);
        }
        S entityToBeFound = null;

        try {
            entityToBeFound = entityManager.find(targetedEntity, id);
        } catch (Exception exception) {
            HandlerUtil.handle(exception);
            Throwables.getRootCause(exception);
            throw DaoRuntimeException.cannotFindEntity(targetedEntity.toString(), String.valueOf(id), exception);

        } finally {
            closeEntityManager(entityManager);
        }
        return entityToBeFound;

    }

    /**
     * @see com.nividous.library.daoblend.core.dao.IPersistentEntityDAO#find(Serializable)
     */
    @Override
    public final MODEL find(IDENTIFIER id) {
        return find(id, entityClass);

    }

    /**
     * @see com.nividous.library.daoblend.core.dao.IPersistentEntityDAO#getAllEntities(Class)
     */
    @SuppressWarnings("unchecked")
    @Override
    public final <S extends MODEL> List<MODEL> findAll(Class<S> targetedEntityClass) {
        List<MODEL> models = null;
        LOGGER.debug("Listing {} entities...", targetedEntityClass);
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            @SuppressWarnings("rawtypes")
            CriteriaQuery c = cb.createQuery(targetedEntityClass);
            Root<MODEL> entity = c.from(targetedEntityClass);
            c.select(entity);
            TypedQuery<MODEL> query = entityManager.createQuery(c);
            LOGGER.debug("Executing Query...");
            models = query.getResultList();
            LOGGER.debug("Data : {} ", models);
            return models;
        } catch (Exception exception) {
            HandlerUtil.handle(exception);
            Throwables.getRootCause(exception);
            throw DaoRuntimeException.cannotFindEntities(getEntityClassName(), exception);
        } finally {
            closeEntityManager(entityManager);
        }

    }

    /**
     * @see com.nividous.library.daoblend.core.dao.IPersistentEntityDAO#findAll()
     */
    @Override
    public final List<MODEL> findAll() {
        return findAll(entityClass);

    }

    // =====================================================================
    // ==================== SEARCH Methods Implementation ==================
    // =====================================================================

    /**
     * @see com.nividous.library.daoblend.core.dao.IPersistentEntityDAO#countAll(Class)
     */
    @Override
    public final <S extends MODEL> int countAll(Class<S> targetedEntity) {
        throw DaoRuntimeException.UnSupportedFeatureError();

    }

    /**
     * @see com.nividous.library.daoblend.core.dao.IPersistentEntityDAO#countAll()
     */
    @Override
    public final int countAll() {
        return countAll(entityClass);
    }

    /**
     * @see com.nividous.library.daoblend.core.dao.IPersistentEntityDAO#count(ISearch)
     */
    public final int count(ISearch search) {
        LOGGER.trace("Searching baed on serch {}", search);
        try {
            return jpaSearchProcessor.count(entityManager, search);
        } finally {
            closeEntityManager(entityManager);
        }
    }

    /**
     * @see com.nividous.library.daoblend.core.dao.IPersistentEntityDAO#search(ISearch
     *      search)
     */
    @SuppressWarnings("unchecked")
    @Override
    public final List<MODEL> search(ISearch search) {
        List<MODEL> list = new ArrayList<MODEL>();
        LOGGER.trace("Search Criteria {}", search);
        try {
            list = jpaSearchProcessor.search(entityManager, search);
        } catch (Exception exception) {
            Throwable rootCause = Throwables.getRootCause(exception);
            throw DaoRuntimeException.searchError(rootCause.getMessage(), exception);
        } finally {
            closeEntityManager(entityManager);
        }

        return list;
    }

    /**
     * @see com.nividous.library.daoblend.core.dao.IPersistentEntityDAO#searchAndCount(ISearch
     *      search)
     */
    @Override
    public final SearchResult searchAndCount(ISearch search) {
        SearchResult result = null;
        LOGGER.trace("Searching baed on serch {}", search);
        try {
            result = jpaSearchProcessor.searchAndCount(entityManager, search);
        } catch (Exception exception) {
            Throwable rootCause = Throwables.getRootCause(exception);
            throw DaoRuntimeException.searchError(rootCause.getMessage(), exception);
        } finally {
            closeEntityManager(entityManager);
        }
        return result;
    }

    /**
     * @see com.nividous.library.daoblend.core.dao.IPersistentEntityDAO#search(NamedQuery
     *      namedQuery)
     */
    @SuppressWarnings({ "rawtypes" })
    @Override
    public final List search(NamedQuery namedQuery) {
        Query query = getQuery(namedQuery);
        LOGGER.trace("Checking for query parameters...");
        if (!query.getParameters().isEmpty()) {
            Map<String, Object> queryParamMap = namedQuery.getQueryParamMap();
            if (queryParamMap == null || queryParamMap.isEmpty()) {
                throw DaoRuntimeException.searchParameterMissingException(namedQuery.getQueryName(),
                        namedQuery.getQueryParamMap());
            }
            for (String namedParam : queryParamMap.keySet()) {
                if (!namedQuery.isNamedParam()) {
                    query.setParameter(Integer.parseInt(namedParam), queryParamMap.get(namedParam));
                } else {
                    query.setParameter(namedParam, queryParamMap.get(namedParam));
                }
            }
        }
        return query.getResultList();
    }

    @SuppressWarnings("rawtypes")
    public List search(NativeQuery nativeQuery) {
        Query query = getQuery(nativeQuery);
        if (!query.getParameters().isEmpty()) {
            List<Object> queryParamList = nativeQuery.getQueryParamList();
            if (queryParamList == null || queryParamList.isEmpty()) {
                throw DaoRuntimeException.searchParameterMissingException(nativeQuery.getQueryString(),
                        nativeQuery.getQueryParamList());
            }
            int paramCount = 1;
            for (Object paramValue : queryParamList) {
                query.setParameter(paramCount, paramValue);
                paramCount++;
            }
        }
        return query.getResultList();
    }

    /**
     * @see com.nividous.library.daoblend.core.dao.IPersistentEntityDAO#
     *      searchByNativeQuery( String nativeQuery, Class
     *      <S> targetedEntityClass)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public final <S extends PersistentEntity> List<S> searchByNativeQuery(String nativeQuery,
            Class<S> targetedEntityClass) {
        if (Strings.isNullOrEmpty(nativeQuery)) {
            throw DaoRuntimeException.searchError(nativeQuery, null);
        }
        return (List<S>) entityManager.createNativeQuery(nativeQuery, targetedEntityClass).getResultList();

    }

    @Override
    public <T> List<T> searchByNativeQueryWithBean(NativeQuery nativeQuery, Class<T> beanClazz) {
        List<T> result = new ArrayList<T>();
        Query query = getQuery(nativeQuery);
        if (query != null) {
            // Get Constructor object of Passed Bean
            Constructor<?> beanClassConstructor = (Constructor<?>) beanClazz.getDeclaredConstructors()[0];

            Class<?>[] constructorParameterTypes = beanClassConstructor.getParameterTypes();

            if (!query.getParameters().isEmpty()) {
                if (nativeQuery.getQueryParamList() == null || nativeQuery.getQueryParamList().isEmpty()) {
                    throw DaoRuntimeException.searchParameterMissingException(nativeQuery.getQueryString(),
                            nativeQuery.getQueryParamList());
                }
                int paramCount = 1;
                for (Object paramValue : nativeQuery.getQueryParamList()) {
                    query.setParameter(paramCount, paramValue);
                    paramCount++;
                }
            }

            @SuppressWarnings("unchecked")
            List<Object[]> rows = query.getResultList();

            for (Object[] columns : rows) {
                Object[] constructorArgs = new Object[constructorParameterTypes.length];
                if (columns.length != constructorParameterTypes.length) {
                    throw DaoRuntimeException.ColumnParameterNumberMismatchException(beanClazz.getName());
                }
                for (int j = 0; j < columns.length; j++) {
                    Object columnValue = columns[j];
                    Class<?> parameterType = constructorParameterTypes[j];
                    LOGGER.trace("Column Value is {} corresponding Constructor Parameter Type is {}", columnValue,
                            parameterType.getName());
                    // convert the column value to the correct type--if possible
                    constructorArgs[j] = DaoConversionManager.getDefaultManager().convertObject(columnValue,
                            parameterType);

                }
                try {
                    @SuppressWarnings("unchecked")
                    T bean = (T) beanClassConstructor.newInstance(constructorArgs);
                    result.add(bean);

                } catch (Exception exception) {
                    throw DaoRuntimeException.ColumnParameterMismatchException(beanClazz.getName(), exception);
                }

            }
        }
        return result;
    }

    private Query getQuery(NamedQuery namedQuery) {
        LOGGER.trace("NamedQuery: {}", namedQuery);
        if (namedQuery == null || Strings.isNullOrEmpty(namedQuery.getQueryName())) {
            throw DaoRuntimeException.searchError(namedQuery.getQueryName(), null);
        }
        Query query = entityManager.createNamedQuery(namedQuery.getQueryName());
        if (namedQuery.getLockModeType() != null) {
            LOGGER.trace("Setting Lock Mode Type to {}", namedQuery.getLockModeType().name());
            query.setLockMode(namedQuery.getLockModeType());
        }
        Map<String, Object> hints = namedQuery.getHints();
        Set<String> hintSet = hints.keySet();
        for (String hint : hintSet) {
            LOGGER.trace("Setting hint {} with value {}", hint, hints.get(hint));
            query.setHint(hint, hints.get(hint));
        }
        query.setFirstResult(namedQuery.getStartPosition());
        query.setMaxResults(namedQuery.getMaxResults());
        return query;
    }

    private Query getQuery(NativeQuery nativeQuery) {
        LOGGER.trace("NativeQuery: {}", nativeQuery);
        if (nativeQuery == null || Strings.isNullOrEmpty(nativeQuery.getQueryString())) {
            throw DaoRuntimeException.searchError(nativeQuery.getQueryString(), null);
        }
        Query query = entityManager.createNativeQuery(nativeQuery.getQueryString());
        return query;
    }

    private void closeEntityManager(EntityManager entityManager) {
        if (entityManager != null && entityManager.isOpen()) {
            LOGGER.trace("Closing Entity Manager...");
            entityManager.close();
            LOGGER.trace("Closed entitiyManager.");
        }
    }

    private String getEntityClassName() {
        if (entityClass != null) {
            return entityClass.getClass().getName();
        } else {
            return NO_ENTITY_CLASS;
        }
    }

}
