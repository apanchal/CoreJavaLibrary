/**
 * 
 */
package com.inclever.library.daoblend.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.common.base.MoreObjects;

/**
 * @author Ashish Panchal(apanchal@inclever.com)
 * @param <ID>
 *
 */
@SuppressWarnings("serial")
public abstract class PersistentEntity<ID extends Serializable> implements IPersistable<ID>, Cloneable {

	public abstract ID getId();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected abstract void setId(ID id);

	@Override
	protected Object clone() throws CloneNotSupportedException {
		@SuppressWarnings("unchecked")
		PersistentEntity<ID> clone = PersistentEntity.class.cast(super.clone());
		clone.setId(null);
		return clone;
	}

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals(Object obj);

	@Override
	public String toString() {
		 return MoreObjects.toStringHelper(this).add("id", getId()).toString();
	}

}
