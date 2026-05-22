package org.example.db;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public final class SqlFileExecutor {
    private SqlFileExecutor() {
    }

    public static String readSqlFile(String filePath) {
        try {
            return Files.readString(Path.of(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read SQL file: " + filePath, e);
        }
    }

    public static void executeSqlFile(Connection connection, String filePath) {
        String sql = readSqlFile(filePath);

        Arrays.stream(splitSql(sql))
                .map(String::trim)
                .filter(statement -> !statement.isBlank())
                .forEach(statement -> executeStatement(connection, statement));
    }

    private static String[] splitSql(String sql) {
        String sqlWithoutComments = sql.replaceAll("(?m)--.*$", "");
        return sqlWithoutComments.split(";");
    }

    private static void executeStatement(Connection connection, String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot execute SQL statement:\n" + sql, e);
        }
    }
}
