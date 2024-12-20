package com.user.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.user.model.Enrollments;

public class EnrollmentsDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/onlinelmsdb";
    private String jdbcUserName = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_ENROLLMENT_SQL = "INSERT INTO Enrollments (student_id, course_id) VALUES (?, ?)";
    private static final String SELECT_ENROLLMENT_BY_ID = "SELECT * FROM Enrollments WHERE enrollment_id = ?;";
    private static final String SELECT_ALL_ENROLLMENTS = "SELECT * FROM Enrollments;";
    private static final String DELETE_ENROLLMENT_SQL = "DELETE FROM Enrollments WHERE enrollment_id = ?;";
    private static final String UPDATE_ENROLLMENT_SQL = "UPDATE Enrollments SET student_id = ?, course_id = ? WHERE enrollment_id = ?;";

    public EnrollmentsDAO() {
    }

    // Get database connection
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Insert an enrollment into the database
    public void insertEnrollment(Enrollments enrollment) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ENROLLMENT_SQL)) {
            preparedStatement.setInt(1, enrollment.getStudentId());
            preparedStatement.setInt(2, enrollment.getCourseId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Select an enrollment by ID
    public Enrollments selectEnrollment(int id) {
        Enrollments enrollment = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ENROLLMENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                enrollment = new Enrollments(
                        resultSet.getInt("enrollment_id"),
                        resultSet.getInt("student_id"),
                        resultSet.getInt("course_id"),
                        resultSet.getTimestamp("enrolled_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollment;
    }

    // Select all enrollments from the database
    public List<Enrollments> selectAllEnrollments() {
        List<Enrollments> enrollments = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ENROLLMENTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                enrollments.add(new Enrollments(
                        resultSet.getInt("enrollment_id"),
                        resultSet.getInt("student_id"),
                        resultSet.getInt("course_id"),
                        resultSet.getTimestamp("enrolled_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }

    // Delete an enrollment by ID
    public boolean deleteEnrollment(int id) {
        boolean status = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ENROLLMENT_SQL)) {
            preparedStatement.setInt(1, id);
            status = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    // Update an enrollment
    public boolean updateEnrollment(Enrollments enrollment) {
        boolean status = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ENROLLMENT_SQL)) {
            preparedStatement.setInt(1, enrollment.getStudentId());
            preparedStatement.setInt(2, enrollment.getCourseId());
            preparedStatement.setInt(3, enrollment.getEnrollmentId());
            status = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static void main(String[] args) {
        EnrollmentsDAO dao = new EnrollmentsDAO();
        if (dao.getConnection() != null) {
            System.out.println("Successfully connected to the database!!");
        } else {
            System.out.println("Problem in database connection!!");
        }
    }
}
