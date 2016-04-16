package com.inclever.library.daoblend.core.dao.search;

import java.io.Serializable;

/**
 * Used to specify field ordering in <code>Search</code>.
 * 
 * @see Search
 */
public class Sort implements Serializable {

    private static final long serialVersionUID = 1L;

    private String property;

    private boolean desc = false;

    private boolean ignoreCase = false;

    private boolean customExpression = false;

    /**
     * 
     * @param property
     * @param desc
     * @param ignoreCase
     */
    public Sort(String property, boolean desc, boolean ignoreCase) {
        this.property = property;
        this.desc = desc;
        this.ignoreCase = ignoreCase;
    }

    /**
     * 
     * @param property
     * @param desc
     */
    public Sort(String property, boolean desc) {
        this.property = property;
        this.desc = desc;
    }

    /**
     * 
     * @param property
     */
    public Sort(String property) {
        this.property = property;
    }

    /**
     * If isCustomExpression is true, the "property" of this Sort is reckoned as
     * a free-form JPQL/HQL order-by expression. Reference properties by
     * wrapping them with curly braces ({}).
     * 
     * <p>
     * Here are some examples:
     * 
     * <pre>
     * new Sort(true, &quot;cast({employeeno} as integer)&quot;);
     * new Sort(true, &quot;abs({prop1} - {prop2})&quot;);
     * </pre>
     */
    public Sort(boolean isCustomExpression, String property, boolean desc) {
        this.customExpression = isCustomExpression;
        this.property = property;
        this.desc = desc;
    }

    /**
     * If isCustomExpression is true, the "property" of this Sort is reckoned as
     * a free-form JPQL/HQL order-by expression. Reference properties by
     * wrapping them with curly braces ({}).
     * 
     * <p>
     * Here are some examples:
     * 
     * <pre>
     * new Sort(true, &quot;cast({employeeno} as integer)&quot;, true);
     * new Sort(true, &quot;abs({prop1} - {prop2})&quot;, true);
     * </pre>
     */
    public Sort(boolean isCustomExpression, String property) {
        this.customExpression = isCustomExpression;
        this.property = property;
    }

    public static Sort asc(String property) {
        return new Sort(property);
    }

    public static Sort asc(String property, boolean ignoreCase) {
        return new Sort(property, ignoreCase);
    }

    public static Sort desc(String property) {
        return new Sort(property, true);
    }

    public static Sort desc(String property, boolean ignoreCase) {
        return new Sort(property, true, ignoreCase);
    }

    /**
     * Instead of a property for this Sort, use a free-form JPQL/HQL order-by
     * expression. Reference properties by wrapping them with curly braces ({}).
     * 
     * <p>
     * Here are some examples:
     * 
     * <pre>
     * Sort.customExpressionAsc(&quot;cast({employeeno} as integer)&quot;);
     * Sort.customExpressionAsc(&quot;abs({prop1} - {prop2})&quot;);
     * </pre>
     */
    public static Sort customExpressionAsc(String expression) {
        return new Sort(true, expression);
    }

    /**
     * Instead of a property for this Sort, use a free-form JPQL/HQL order-by
     * expression. Reference properties by wrapping them with curly braces ({}).
     * 
     * <p>
     * Here are some examples:
     * 
     * <pre>
     * Sort.customExpressionDesc(&quot;cast({employeeno} as integer)&quot;);
     * Sort.customExpressionDesc(&quot;abs({prop1} - {prop2})&quot;);
     * </pre>
     */
    public static Sort customExpressionDesc(String expression) {
        return new Sort(true, expression, true);
    }

    /**
     * Property on which to sort
     */
    public String getProperty() {
        return property;
    }

    /**
     * Property on which to sort
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * If true, sort descending by the given property; otherwise, sort
     * ascending.
     */
    public boolean isDesc() {
        return desc;
    }

    /**
     * If true, sort descending by the given property; otherwise, sort
     * ascending.
     */
    public void setDesc(boolean desc) {
        this.desc = desc;
    }

    /**
     * If true the ordering will be case insensitive for this property. Ignore
     * case has no effect when customExpression is specified.
     */
    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    /**
     * If true the ordering will be case insensitive for this property. Ignore
     * case has no effect when customExpression is specified.
     */
    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    /**
     * <p>
     * If true, the "property" of this Sort is reckoned as a free-form JPQL/HQL
     * order-by expression. Reference properties by wrapping them with curly
     * braces ({}).
     * 
     * <p>
     * When set to <code>true</code>, the <code>ignoreCase</code> property is
     * ignored.
     * 
     * <p>
     * Here are some examples:
     * 
     * <pre>
     * new Sort(true, &quot;cast({employeeno} as integer)&quot;);
     * new Sort(true, &quot;abs({prop1} - {prop2})&quot;, true);
     * Sort.ascCustom(&quot;cast({employeeno} as integer)&quot;);
     * Sort.descCustom(&quot;abs({prop1} - {prop2})&quot;);
     * </pre>
     */
    public boolean isCustomExpression() {
        return customExpression;
    }

    /**
     * <p>
     * If true, the "property" of this Sort is reckoned as a free-form JPQL/HQL
     * order-by expression. Reference properties by wrapping them with curly
     * braces ({}).
     * 
     * <p>
     * When set to <code>true</code>, the <code>ignoreCase</code> property is
     * ignored.
     * 
     * <p>
     * Here are some examples:
     * 
     * <pre>
     * new Sort(true, &quot;cast({employeeno} as integer)&quot;);
     * new Sort(true, &quot;abs({prop1} - {prop2})&quot;, true);
     * Sort.ascCustom(&quot;cast({employeeno} as integer)&quot;);
     * Sort.descCustom(&quot;abs({prop1} - {prop2})&quot;);
     * </pre>
     */
    public void setCustomExpression(boolean customExpression) {
        this.customExpression = customExpression;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (customExpression ? 1231 : 1237);
        result = prime * result + (desc ? 1231 : 1237);
        result = prime * result + (ignoreCase ? 1231 : 1237);
        result = prime * result + ((property == null) ? 0 : property.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Sort other = (Sort) obj;
        if (customExpression != other.customExpression) {
            return false;
        }
        if (desc != other.desc) {
            return false;
        }
        if (ignoreCase != other.ignoreCase) {
            return false;
        }
        if (property == null) {
            if (other.property != null) {
                return false;
            }
        } else if (!property.equals(other.property)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (customExpression) {
            sb.append("CUSTOM: ");
        }

        if (property == null) {
            sb.append("null");
        } else {
            sb.append('`');
            sb.append(property);
            sb.append('`');
        }
        sb.append(desc ? " desc" : " asc");
        if (ignoreCase && !customExpression) {
            sb.append(" (ignore case)");
        }
        return sb.toString();
    }
}
