# Java H2 JDBC Homework

Gradle project for homework №2: Java + JDBC + H2.

## How to run

```bash
./gradlew initDb
./gradlew populateDb
./gradlew runQueries
```

If you do not have a Gradle wrapper, run the same tasks from IntelliJ IDEA Gradle panel or with installed Gradle:

```bash
gradle initDb
gradle populateDb
gradle runQueries
```

## Project structure

```text
sql/                              SQL scripts
src/main/java/org/example/db/      database helpers
src/main/java/org/example/model/   DTO classes for SELECT results
src/main/java/org/example/         main services
```

## Notes

- `Database` is a singleton and stores a single JDBC `Connection`.
- `DatabaseInitService` executes `sql/init_db.sql`.
- `DatabasePopulateService` executes `sql/populate_db.sql`.
- `DatabaseQueryService` reads SELECT files and maps results to Java objects.
- SELECT queries are executed through `PreparedStatement`.
