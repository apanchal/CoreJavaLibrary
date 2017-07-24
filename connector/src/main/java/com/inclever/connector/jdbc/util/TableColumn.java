/**
 * 
 */
package com.inclever.connector.jdbc.util;

/**
 * @author apanchal
 *
 */
public class TableColumn {
    
    private String name;
    
    private String label;
    
    private boolean isAutoIncrement;
    
    private boolean isPrimaryKey;
    
    private String type;
    
    private int size;
    /**
     * 
     * @param name
     */
    public TableColumn(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getLabel() {
        return label;
    }
    
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public int getSize() {
        return size;
    }


    public void setLabel(String label) {
        this.label = label;
    }


    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }


    public void setAutoIncrement(boolean isAutoIncrement) {
        this.isAutoIncrement = isAutoIncrement;
    }


    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }


    public void setPrimaryKey(boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }
    
    @Override
    public String toString() {
        return new StringBuilder("["+getClass().getName()).append("]")
                .append("Name= ").append(name)
                .append("\n").append("Label= ").append(label)
                .append("\n").append("Type= ").append(type)
                .append("\n").append("size=").append(size)
                .append("\n").append("isPrimaryKey? ").append(isPrimaryKey)
                .append("\n").append("isAutoIncrement?").append(isAutoIncrement)
                .append("\n")
                .toString();
    }
    
}
