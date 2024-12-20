package com.user.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.user.model.Student;

public class StudentDAO {

    private String jdbcURL = "jdbc:mysql://localhost:30006/onlinelmsdb";
    private String jdbcUserName = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_STUDENT_SQL = "INSERT INTO Students (full_name, enrollment_number, date_of_birth, phone_number, user_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_STUDENT_BY_ID = "SELECT * FROM Students WHERE student_id = ?;";
    private static final String SELECT_ALL_STUDENTS = "SELECT * FROM Students;";
    private static final String DELETE_STUDENT_SQL = "DELETE FROM Students WHERE student_id = ?;";
    private static final String UPDATE_STUDENT_SQL = "UPDATE Students SET full_name = ?, enrollment_number = ?, date_of_birth = ?, phone_number = ?, user_id = ? WHERE student_id = ?;";

    public StudentDAO() {
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

    // Insert a student into the database
    public void insertStudent(Student student) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {
            preparedStatement.setString(1, student.getFullName());
            preparedStatement.setString(2, student.getEnrollmentNumber());
            preparedStatement.setDate(3, student.getDateOfBirth());
            preparedStatement.setString(4, student.getPhoneNumber());
            preparedStatement.setInt(5, student.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Select a student by ID
    public Student selectStudent(int id) {
        Student student = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = new Student(
                        resultSet.getInt("student_id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("enrollment_number"),
                        resultSet.getDate("date_of_birth"),
                        resultSet.getString("phone_number"),
                        resultSet.getInt("user_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    // Select all students from the database
    public List<Student> selectAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                students.add(new Student(
                        resultSet.getInt("student_id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("enrollment_number"),
                        resultSet.getDate("date_of_birth"),
                        resultSet.getString("phone_number"),
                        resultSet.getInt("user_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Delete a student by ID
    public boolean deleteStudent(int id) {
        boolean status = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT_SQL)) {
            preparedStatement.setInt(1, id);
            status = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    // Update a student's information
    public boolean updateStudent(Student student) {
        boolean status = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT_SQL)) {
            preparedStatement.setString(1, student.getFullName());
            preparedStatement.setString(2, student.getEnrollmentNumber());
            preparedStatement.setDate(3, student.getDateOfBirth());
            preparedStatement.setString(4, student.getPhoneNumber());
            preparedStatement.setInt(5, student.getUserId());
            preparedStatement.setInt(6, student.getStudentId());
            status = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        if (dao.getConnection() != null) {
            System.out.println("Successfully connected to the database!!");
        } else {
            System.out.println("Problem in database connection!!");
        }
    }
}
