package com.user.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.user.model.Instructor;

public class InstructorDAO {

    private String jdbcURL = "jdbc:mysql://localhost:30006/onlinelmsdb";
    private String jdbcUserName = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_INSTRUCTOR_SQL = "INSERT INTO Instructors (instructor_id, full_name, qualification, phone_number) VALUES (?, ?, ?, ?)";
    private static final String SELECT_INSTRUCTOR_BY_ID = "SELECT * FROM Instructors WHERE instructor_id = ?;";
    private static final String SELECT_ALL_INSTRUCTORS = "SELECT * FROM Instructors;";
    private static final String DELETE_INSTRUCTOR_SQL = "DELETE FROM Instructors WHERE instructor_id = ?;";
    private static final String UPDATE_INSTRUCTOR_SQL = "UPDATE Instructors SET full_name = ?, qualification = ?, phone_number = ? WHERE instructor_id = ?;";

    public InstructorDAO() {
    }

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

    public void insertInstructor(Instructor instructor) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INSTRUCTOR_SQL)) {
            preparedStatement.setInt(1, instructor.getInstructorId());
            preparedStatement.setString(2, instructor.getFullName());
            preparedStatement.setString(3, instructor.getQualification());
            preparedStatement.setString(4, instructor.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Instructor selectInstructor(int id) {
        Instructor instructor = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INSTRUCTOR_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                instructor = new Instructor(
                    resultSet.getInt("instructor_id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("qualification"),
                    resultSet.getString("phone_number")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instructor;
    }

    public List<Instructor> selectAllInstructors() {
        List<Instructor> instructors = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_INSTRUCTORS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                instructors.add(new Instructor(
                    resultSet.getInt("instructor_id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("qualification"),
                    resultSet.getString("phone_number")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instructors;
    }

    public boolean deleteInstructor(int id) {
        boolean status = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_INSTRUCTOR_SQL)) {
            preparedStatement.setInt(1, id);
            status = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public boolean updateInstructor(Instructor instructor) {
        boolean status = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INSTRUCTOR_SQL)) {
            preparedStatement.setString(1, instructor.getFullName());
            preparedStatement.setString(2, instructor.getQualification());
            preparedStatement.setString(3, instructor.getPhoneNumber());
            preparedStatement.setInt(4, instructor.getInstructorId());
            status = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static void main(String[] args) {
        InstructorDAO dao = new InstructorDAO();
        if (dao.getConnection() != null) {
            System.out.println("Successfully connected to the database!!");
        } else {
            System.out.println("Problem in database connection!!");
        }
    }
}
