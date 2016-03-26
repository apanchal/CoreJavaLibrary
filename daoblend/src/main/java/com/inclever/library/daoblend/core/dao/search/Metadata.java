package com.inclever.library.daoblend.core.dao.search;

import java.io.Serializable;

/**
 * This interface provides meta data for a single persistable type. Use
 * {@link MetadataUtil#get(Class)} or {@link MetadataUtil#get(Class, String)} to
 * get meta data instances.
 * 
 * This interface provides a layer of abstraction between the framework and the
 * underlying JPA provider (ex. Hibernate). By switching out the implementation
 * of this interface, the framework should be able to be used with different JPA
 * providers.
 * 
 */
public interface Metadata {
    /**
     * Return true if the type is an entity.
     */
    boolean isEntity();

    /**
     * Return true if the type is an embeddable class (a component class in
     * Hibernate).
     */
    boolean isEmeddable();

    /**
     * Return true if the type is a collection.
     */
    boolean isCollection();

    /**
     * Return true if the type is persisted as a string (char or varchar) type
     * in the database.
     */
    boolean isString();

    /**
     * Return true if the type is a number. For example: int, Float, Number,
     * double, etc.
     */
    boolean isNumeric();

    /**
     * Return the Java class of this type. If the type is a collection, return
     * the type of the collection elements.
     */
    Class<?> getJavaClass();

    /**
     * If the type is an entity return the entity name. Otherwise throw an
     * UnsupportedOperationException.
     */
    String getEntityName();

    /**
     * Return an array of the names of all the properties that this type has, if
     * any. Return null if this a simple value type with no properties.
     */
    String[] getProperties();

    /**
     * Return the value of the given property of the given object of this type.
     * Return null if this a simple value type with no properties.
     */
    Object getPropertyValue(Object object, String property);

    /**
     * Return the metadata for the given property of this type. Return null if
     * this a simple value type with no properties.
     */
    Metadata getPropertyType(String property);

    /**
     * Return the name of the id property of this type. Return null if this is
     * not an entity type.
     */
    String getIdProperty();

    /**
     * Return the metadata for the id property of this type. Return null if this
     * is not an entity type.
     */
    Metadata getIdType();

    /**
     * Return the value of the id property of the given object of this type.
     * Return null if this is not an entity type.
     */
    Serializable getIdValue(Object object);

    /**
     * If the type is a collection, return the Java class of the collection
     * itself, not the Java class of it's elements as with
     * {@link #getJavaClass()}. For example: ArrayList&lt;Project&gt;,
     * Set&lt;Person&gt;, String[].
     */
    Class<?> getCollectionClass();
}
