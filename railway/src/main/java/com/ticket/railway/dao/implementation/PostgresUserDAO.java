package com.ticket.railway.dao.implementation;

import com.ticket.railway.connection.PSQLConnector;
import com.ticket.railway.dao.UserDAO;
import com.ticket.railway.entity.Role;
import com.ticket.railway.entity.User;

import com.ticket.railway.connection.PSQLConnector;
import com.ticket.railway.dao.UserDAO;
import com.ticket.railway.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresUserDAO implements UserDAO {
    private static final String COLUMN_ID = "user_id";
    private static final String COLUMN_LOGIN = "login";
    private static final String COLUMN_PASSWORD = "password_user";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_BIRTHDAY = "birthday";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_EMAIL = "email";

    private static final String SELECT = "SELECT * FROM public.\"user\"";
    private static final String UPDATE = "UPDATE public.\"user\" SET " + COLUMN_LOGIN + "=?, " + COLUMN_PASSWORD + "=?, "
            + COLUMN_FIRST_NAME + "=?, " + COLUMN_LAST_NAME + "=?, "
            + COLUMN_BIRTHDAY + "=?, " + COLUMN_PHONE + "=?, " + COLUMN_EMAIL +"=?" + " WHERE "+ COLUMN_ID +"=?";
    private static final String INSERT = "INSERT INTO public.\"user\" (" + COLUMN_LOGIN + ","
            + COLUMN_PASSWORD + "," + COLUMN_FIRST_NAME + ", " + COLUMN_LAST_NAME + ", "
            + COLUMN_BIRTHDAY + ", " + COLUMN_PHONE + ", " + COLUMN_EMAIL +") VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM public.\"user\" WHERE " + COLUMN_ID + "=?";

    @Override
    public User findById(int userId) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT + " WHERE " + COLUMN_ID + "=" + userId);
        ResultSet rs = statement.executeQuery();
        User user = null;

        if (rs.next()) {
            user = new User(rs.getInt(COLUMN_ID), rs.getString(COLUMN_LOGIN), rs.getString(COLUMN_PASSWORD),
                    rs.getString(COLUMN_FIRST_NAME), rs.getString(COLUMN_LAST_NAME), rs.getDate(COLUMN_BIRTHDAY),
                    rs.getString(COLUMN_PHONE), rs.getString(COLUMN_EMAIL));
        }

        rs.close();
        statement.close();
        connection.close();

        return user;
    }

    @Override
    public List<User> findAll() throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT);
        ResultSet rs = statement.executeQuery();
        List<User> users = new ArrayList<>();

        while (rs.next()) {
            users.add(new User(rs.getInt(COLUMN_ID), rs.getString(COLUMN_LOGIN), rs.getString(COLUMN_PASSWORD),
                    rs.getString(COLUMN_FIRST_NAME), rs.getString(COLUMN_LAST_NAME), rs.getDate(COLUMN_BIRTHDAY),
                    rs.getString(COLUMN_PHONE), rs.getString(COLUMN_EMAIL)));
        }

        rs.close();
        statement.close();
        connection.close();

        return users;
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getFirstName());
        statement.setString(4, user.getLastName());
        statement.setDate(5, user.getBirthday());
        statement.setString(6, user.getPhone());
        statement.setString(7, user.getEmail());

        if (statement.executeUpdate() == 1) {
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                user.setUserId(rs.getInt(COLUMN_ID));
            }
            rs.close();
        }

        statement.close();
        connection.close();

        return user.getUserId() != null;
    }

    @Override
    public void updateUser(User user) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);

        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getFirstName());
        statement.setString(4, user.getLastName());
        statement.setDate(5, user.getBirthday());
        statement.setString(6, user.getPhone());
        statement.setString(7, user.getEmail());
        statement.setInt(8, user.getUserId());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);

        statement.setInt(1, user.getUserId());
        statement.executeUpdate();

        statement.close();
        connection.close();
    }
}
