package com.user.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.user.model.Submissions;

public class SubmissionsDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/onlinelmsdb";
    private String jdbcUserName = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_SUBMISSION_SQL = "INSERT INTO Submissions (assignment_id, student_id, submission_date, grade) VALUES (?, ?, ?, ?)";
    private static final String SELECT_SUBMISSION_BY_ID = "SELECT * FROM Submissions WHERE submission_id = ?;";
    private static final String SELECT_ALL_SUBMISSIONS = "SELECT * FROM Submissions;";
    private static final String DELETE_SUBMISSION_SQL = "DELETE FROM Submissions WHERE submission_id = ?;";
    private static final String UPDATE_SUBMISSION_SQL = "UPDATE Submissions SET assignment_id = ?, student_id = ?, submission_date = ?, grade = ? WHERE submission_id = ?;";

    public SubmissionsDAO() {
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

    // Insert a new submission
    public void insertSubmission(Submissions submission) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUBMISSION_SQL)) {
            preparedStatement.setInt(1, submission.getAssignmentId());
            preparedStatement.setInt(2, submission.getStudentId());
            preparedStatement.setTimestamp(3, submission.getSubmissionDate());
            preparedStatement.setString(4, submission.getGrade());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Select a submission by ID
    public Submissions selectSubmission(int id) {
        Submissions submission = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUBMISSION_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                submission = new Submissions(
                        resultSet.getInt("submission_id"),
                        resultSet.getInt("assignment_id"),
                        resultSet.getInt("student_id"),
                        resultSet.getTimestamp("submission_date"),
                        resultSet.getString("grade")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return submission;
    }

    // Select all submissions
    public List<Submissions> selectAllSubmissions() {
        List<Submissions> submissions = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SUBMISSIONS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                submissions.add(new Submissions(
                        resultSet.getInt("submission_id"),
                        resultSet.getInt("assignment_id"),
                        resultSet.getInt("student_id"),
                        resultSet.getTimestamp("submission_date"),
                        resultSet.getString("grade")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return submissions;
    }

    // Delete a submission by ID
    public boolean deleteSubmission(int id) {
        boolean status = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SUBMISSION_SQL)) {
            preparedStatement.setInt(1, id);
            status = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    // Update a submission
    public boolean updateSubmission(Submissions submission) {
        boolean status = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SUBMISSION_SQL)) {
            preparedStatement.setInt(1, submission.getAssignmentId());
            preparedStatement.setInt(2, submission.getStudentId());
            preparedStatement.setTimestamp(3, submission.getSubmissionDate());
            preparedStatement.setString(4, submission.getGrade());
            preparedStatement.setInt(5, submission.getSubmissionId());
            status = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static void main(String[] args) {
        SubmissionsDAO dao = new SubmissionsDAO();
        if (dao.getConnection() != null) {
            System.out.println("Successfully connected to the database!!");
        } else {
            System.out.println("Problem in database connection!!");
        }
    }
}
