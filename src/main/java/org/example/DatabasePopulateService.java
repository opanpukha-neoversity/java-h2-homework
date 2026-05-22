package org.example;

import org.example.db.Database;
import org.example.db.SqlFileExecutor;

import java.sql.Connection;

public class DatabasePopulateService {
    private static final String POPULATE_SQL_FILE = "sql/populate_db.sql";

    public static void main(String[] args) {
        Connection connection = Database.getInstance().getConnection();
        SqlFileExecutor.executeSqlFile(connection, POPULATE_SQL_FILE);
        System.out.println("Database populated successfully.");
    }
}
