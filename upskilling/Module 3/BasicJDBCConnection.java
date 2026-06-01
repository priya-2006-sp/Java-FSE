import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class BasicJDBCConnection {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:students.db";
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("JDBC Driver loaded successfully.");
            try (Connection conn = DriverManager.getConnection(url)) {
                System.out.println("Connection to SQLite database established.");
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute("CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT)");                    stmt.execute("DELETE FROM students");
                    stmt.execute("INSERT INTO students (id, name) VALUES (1, 'Alice')");
                    stmt.execute("INSERT INTO students (id, name) VALUES (2, 'Bob')");
                    stmt.execute("INSERT INTO students (id, name) VALUES (3, 'Charlie')");
                    String sql = "SELECT id, name FROM students";
                    try (ResultSet rs = stmt.executeQuery(sql)) {
                        System.out.println("\nStudent List retrieved from database:");
                        System.out.printf("%-5s | %-15s\n", "ID", "Name");
                        System.out.println("----------------------");
                        while (rs.next()) {
                            int id = rs.getInt("id");
                            String name = rs.getString("name");
                            System.out.printf("%-5d | %-15s\n", id, name);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error: SQLite JDBC Driver not found. Make sure the driver is in the classpath.");
        } catch (SQLException e) {
            System.out.println("Database error occurred: " + e.getMessage());
        }
    }
}
