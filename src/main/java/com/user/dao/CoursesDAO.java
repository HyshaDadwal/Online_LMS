package com.user.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.user.model.Courses;

public class CoursesDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/onlinelmsdb";
    private String jdbcUserName = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_COURSE_SQL = "INSERT INTO Courses (course_name, description, created_by) VALUES (?, ?, ?)";
    private static final String SELECT_COURSE_BY_ID = "SELECT * FROM Courses WHERE course_id = ?;";
    private static final String SELECT_ALL_COURSES = "SELECT * FROM Courses;";
    private static final String DELETE_COURSE_SQL = "DELETE FROM Courses WHERE course_id = ?;";
    private static final String UPDATE_COURSE_SQL = "UPDATE Courses SET course_name = ?, description = ?, created_by = ? WHERE course_id = ?;";

    public CoursesDAO() {
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

    // Insert a course into the database
    public void insertCourse(Courses course) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURSE_SQL)) {
            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setInt(3, course.getCreatedBy());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Select a course by ID
    public Courses selectCourse(int id) {
        Courses course = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COURSE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                course = new Courses(
                        resultSet.getInt("course_id"),
                        resultSet.getString("course_name"),
                        resultSet.getString("description"),
                        resultSet.getInt("created_by")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    // Select all courses from the database
    public List<Courses> selectAllCourses() {
        List<Courses> courses = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COURSES);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                courses.add(new Courses(
                        resultSet.getInt("course_id"),
                        resultSet.getString("course_name"),
                        resultSet.getString("description"),
                        resultSet.getInt("created_by")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // Delete a course by ID
    public boolean deleteCourse(int id) {
        boolean status = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COURSE_SQL)) {
            preparedStatement.setInt(1, id);
            status = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    // Update a course
    public boolean updateCourse(Courses course) {
        boolean status = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COURSE_SQL)) {
            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setInt(3, course.getCreatedBy());
            preparedStatement.setInt(4, course.getCourseId());
            status = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static void main(String[] args) {
        CoursesDAO dao = new CoursesDAO();
        if (dao.getConnection() != null) {
            System.out.println("Successfully connected to the database!!");
        } else {
            System.out.println("Problem in database connection!!");
        }
    }
}
