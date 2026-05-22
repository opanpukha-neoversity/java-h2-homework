package org.example;

import org.example.db.Database;
import org.example.db.ResultSetMapper;
import org.example.db.SqlFileExecutor;
import org.example.model.LongestProject;
import org.example.model.MaxProjectCountClient;
import org.example.model.MaxSalaryWorker;
import org.example.model.ProjectPrice;
import org.example.model.YoungestEldestWorker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    private static final String FIND_MAX_PROJECTS_CLIENT_SQL = "sql/find_max_projects_client.sql";
    private static final String FIND_LONGEST_PROJECT_SQL = "sql/find_longest_project.sql";
    private static final String FIND_MAX_SALARY_WORKER_SQL = "sql/find_max_salary_worker.sql";
    private static final String FIND_YOUNGEST_ELDEST_WORKERS_SQL = "sql/find_youngest_eldest_workers.sql";
    private static final String PRINT_PROJECT_PRICES_SQL = "sql/print_project_prices.sql";

    private final Connection connection;

    public DatabaseQueryService() {
        connection = Database.getInstance().getConnection();
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        return selectList(
                FIND_MAX_PROJECTS_CLIENT_SQL,
                resultSet -> new MaxProjectCountClient(
                        resultSet.getString("name"),
                        resultSet.getInt("project_count")
                )
        );
    }

    public List<LongestProject> findLongestProject() {
        return selectList(
                FIND_LONGEST_PROJECT_SQL,
                resultSet -> new LongestProject(
                        resultSet.getString("name"),
                        resultSet.getInt("month_count")
                )
        );
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        return selectList(
                FIND_MAX_SALARY_WORKER_SQL,
                resultSet -> new MaxSalaryWorker(
                        resultSet.getString("name"),
                        resultSet.getInt("salary")
                )
        );
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers() {
        return selectList(
                FIND_YOUNGEST_ELDEST_WORKERS_SQL,
                resultSet -> new YoungestEldestWorker(
                        resultSet.getString("type"),
                        resultSet.getString("name"),
                        resultSet.getObject("birthday", LocalDate.class)
                )
        );
    }

    public List<ProjectPrice> printProjectPrices() {
        return selectList(
                PRINT_PROJECT_PRICES_SQL,
                resultSet -> new ProjectPrice(
                        resultSet.getString("name"),
                        resultSet.getInt("price")
                )
        );
    }

    private <T> List<T> selectList(String sqlFilePath, ResultSetMapper<T> mapper) {
        String sql = SqlFileExecutor.readSqlFile(sqlFilePath);
        List<T> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                result.add(mapper.map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot execute query from file: " + sqlFilePath, e);
        }

        return result;
    }

    public static void main(String[] args) {
        DatabaseQueryService queryService = new DatabaseQueryService();

        System.out.println("findMaxProjectsClient:");
        queryService.findMaxProjectsClient().forEach(System.out::println);

        System.out.println("\nfindLongestProject:");
        queryService.findLongestProject().forEach(System.out::println);

        System.out.println("\nfindMaxSalaryWorker:");
        queryService.findMaxSalaryWorker().forEach(System.out::println);

        System.out.println("\nfindYoungestEldestWorkers:");
        queryService.findYoungestEldestWorkers().forEach(System.out::println);

        System.out.println("\nprintProjectPrices:");
        queryService.printProjectPrices().forEach(System.out::println);
    }
}
