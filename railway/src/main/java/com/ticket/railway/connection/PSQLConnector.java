package com.ticket.railway.connection;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PSQLConnector {

    private static BasicDataSource dataSource;

    public PSQLConnector() {}

    public static void initDataSource() {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("JavaAngel_3425");
        dataSource.setMaxTotal(5);
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            initDataSource();
        }
        return dataSource.getConnection();
    }
}
