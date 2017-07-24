package com.inclever.connector.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.junit.Test;

import com.inclever.connector.jdbc.util.JdbcUtil;
import com.inclever.connector.jdbc.util.TableColumn;

import junit.framework.TestCase;

public class JdbcUtilTest extends TestCase {
    
    
    String url = "jdbc:mysql://localhost:3306/EventStore_Demo?user=root&password=mysql007";
    String table = "test";
    private Connection connection;
    
    @Test
    public void testGetTables() {
        try {
            connection = DriverManager.getConnection(url);
            List<String> tables = JdbcUtil.getTables(connection);
            System.out.println("No.Of.Table Found:"+tables.size());
            if(!tables.isEmpty()) {
                for (String tableName:tables) {
                    System.out.println("Table Name:"+tableName);
                    List<TableColumn> columns = JdbcUtil.getColumes(connection, tableName);
                    System.out.println("No.Of.Columns Found:"+columns.size());
                    if(!columns.isEmpty()) {
                        for(TableColumn tableColumn : columns) {
                            System.out.println(tableColumn);
                        }
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
