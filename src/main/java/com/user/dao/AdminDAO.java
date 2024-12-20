package com.user.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.user.model.Admin;

public class AdminDAO {

    private String jdbcURL = "jdbc:mysql://localhost:30006/onlinelmsdb";
    private String jdbcUserName = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_ADMIN_SQL = "INSERT INTO Admins (admin_id, full_name, phone_number) VALUES (?, ?, ?)";
    private static final String SELECT_ADMIN_BY_ID = "SELECT * FROM Admins WHERE admin_id = ?;";
    private static final String SELECT_ALL_ADMINS = "SELECT * FROM Admins;";
    private static final String DELETE_ADMIN_SQL = "DELETE FROM Admins WHERE admin_id = ?;";
    private static final String UPDATE_ADMIN_SQL = "UPDATE Admins SET full_name = ?, phone_number = ? WHERE admin_id = ?;";

    public AdminDAO() {
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

    public void insertAdmin(Admin admin) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADMIN_SQL)) {
            preparedStatement.setInt(1, admin.getAdminId());
            preparedStatement.setString(2, admin.getFullName());
            preparedStatement.setString(3, admin.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Admin selectAdmin(int id) {
        Admin admin = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADMIN_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                admin = new Admin(
                    resultSet.getInt("admin_id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("phone_number")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    public List<Admin> selectAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ADMINS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                admins.add(new Admin(
                    resultSet.getInt("admin_id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("phone_number")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    public boolean deleteAdmin(int id) {
        boolean status = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADMIN_SQL)) {
            preparedStatement.setInt(1, id);
            status = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public boolean updateAdmin(Admin admin) {
        boolean status = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADMIN_SQL)) {
            preparedStatement.setString(1, admin.getFullName());
            preparedStatement.setString(2, admin.getPhoneNumber());
            preparedStatement.setInt(3, admin.getAdminId());
            status = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static void main(String[] args) {
        AdminDAO dao = new AdminDAO();
        if (dao.getConnection() != null) {
            System.out.println("Successfully connected to the database!!");
        } else {
            System.out.println("Problem in database connection!!");
        }
    }
}
