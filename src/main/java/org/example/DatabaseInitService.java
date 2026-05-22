package org.example;

import org.example.db.Database;
import org.example.db.SqlFileExecutor;

import java.sql.Connection;

public class DatabaseInitService {
    private static final String INIT_SQL_FILE = "sql/init_db.sql";

    public static void main(String[] args) {
        Connection connection = Database.getInstance().getConnection();
        SqlFileExecutor.executeSqlFile(connection, INIT_SQL_FILE);
        System.out.println("Database structure initialized successfully.");
    }
}
