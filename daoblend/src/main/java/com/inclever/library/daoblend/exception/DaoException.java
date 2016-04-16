/**
 * 
 */
package com.inclever.library.daoblend.exception;

import java.sql.SQLException;

import com.inclever.library.common.StringPool;
import com.inclever.library.configuration.LibraryVersion;
import com.inclever.library.exception.LibraryException;

/**
 * <P>
 * <B>Purpose</B>: Wrapper for any database exception that occurred through
 * DAOBlend.
 * 
 * @author Ashish Panchal(aashish.panchal@gmail.com)
 * 
 */
public class DaoException extends LibraryException {

    /**
     * 
     */
    private static final long serialVersionUID = 5590645956367173279L;

    private static final String MODULE_NAME = StringPool.OPEN_PARENTHESIS.concat("DAOBlend Persistence Services - "
            .concat(LibraryVersion.getVersion()).concat(".v").concat(LibraryVersion.getBuildDate())
            .concat(StringPool.DASH).concat(LibraryVersion.getBuildNumber()).concat(StringPool.CLOSE_PARENTHESIS));

    public DaoException() {
        super();
    }

    /**
     * 
     */
    protected DaoException(String message) {
        super(message);
    }

    /**
     * 
     */
    protected DaoException(SQLException exception) {
        super(exception.toString(), exception);
    }

    public String getModuleName() {

        return MODULE_NAME;
    }

}
