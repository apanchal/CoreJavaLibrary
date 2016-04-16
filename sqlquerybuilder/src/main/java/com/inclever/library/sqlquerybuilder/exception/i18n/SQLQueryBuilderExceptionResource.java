package com.inclever.library.sqlquerybuilder.exception.i18n;

import java.util.ListResourceBundle;

import com.inclever.library.sqlquerybuilder.exception.SQLQueryBuilderException;

/**
 * English ResourceBundle for SQLQueryBuilderException messages.
 * 
 * @author apanchal
 * @since 2.0.0
 */
public class SQLQueryBuilderExceptionResource extends ListResourceBundle {

    /**
     * Lookup table.
     */
    private static final Object[][] contents = { { String.valueOf(SQLQueryBuilderException.EMPTY_SELECT_ERROR),
            "There is nothing to select, make sure that there is atleast single columm to select." } };

    /**
     * Return the lookup table.
     */
    @Override
    protected Object[][] getContents() {
        return contents;
    }

}
