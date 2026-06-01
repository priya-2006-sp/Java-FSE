import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
class Student {
    private int id;
    private String name;
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
class StudentDAO {
    private String dbUrl;
    public StudentDAO(String dbUrl) {
        this.dbUrl = dbUrl;
        initializeDatabase();
    }
    private void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT)");
        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }
    public void insertStudent(Student student) {
        String sql = "INSERT INTO students (id, name) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(dbUrl);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.executeUpdate();
            System.out.println("Inserted: " + student.getName() + " (ID: " + student.getId() + ")");
        } catch (SQLException e) {
            System.out.println("Error inserting student: " + e.getMessage());
        }
    }
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET name = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getId());
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Updated Student ID " + student.getId() + " to name: " + student.getName());
            } else {
                System.out.println("No student found with ID " + student.getId() + " to update.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }
    public void displayAllStudents() {
        String sql = "SELECT id, name FROM students";
        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nAll Students in database:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving students: " + e.getMessage());
        }
    }
}
public class JDBCInsertUpdate {
    public static void main(String[] args) {
        String dbUrl = "jdbc:sqlite:students.db";
        StudentDAO dao = new StudentDAO(dbUrl);
        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM students");
        } catch (SQLException e) {}
        System.out.println("--- Testing StudentDAO ---");
        dao.insertStudent(new Student(1, "John Doe"));
        dao.insertStudent(new Student(2, "Jane Smith"));
        dao.displayAllStudents();
        dao.updateStudent(new Student(1, "Johnathan Doe"));
        dao.displayAllStudents();
    }
}
