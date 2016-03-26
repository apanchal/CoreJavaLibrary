/*******************************************************************************
 * Copyright (c) 2015, 2016 Nividous Software Solution Pvt Ltd. All rights reserved.
 * 
 * Contributors:
 *     Ashish Panchal - 2.5 - initial implementation
 ******************************************************************************/
package com.inclever.library.daoblend.core.dao.sql.mapping.conversion;

public abstract class DaoCoreConversionManager {

    /**
     * Convert the object to the appropriate type by invoking the appropriate
     * DaoConversionManager method
     * 
     * @param object
     *            - the object that must be converted
     * @param javaClass
     *            - the class that the object must be converted to
     * @exception - ConversionException, all exceptions will be thrown as this
     *            type.
     * @return - the newly converted object
     */
    public abstract Object convertObject(Object sourceObject, Class<?> javaClass);

}
