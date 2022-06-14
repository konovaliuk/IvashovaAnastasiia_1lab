package com.ticket.railway.dao.implementation;

import com.ticket.railway.connection.PSQLConnector;
import com.ticket.railway.dao.UserRoleDAO;
import com.ticket.railway.entity.User;
import com.ticket.railway.entity.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresUserRoleDAO implements UserRoleDAO
{
    private static final String COLUMN_ID = "user_role_id";
    private static final String COLUMN_USER = "user_id";
    private static final String COLUMN_ROLE = "role_id";

    private static final String SELECT = "SELECT * FROM public.\"user_role\"";
    private static final String UPDATE = "UPDATE public.\"user_role\" SET " + COLUMN_USER + "=?, "
            + COLUMN_ROLE + "=?" + " WHERE " + COLUMN_ID + "=?";
    private static final String INSERT = "INSERT INTO public.\"user_role\" (" + COLUMN_USER + ","
            + COLUMN_ROLE + ") VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM public.\"user_role\" WHERE " + COLUMN_ID + "=?";

    @Override
    public UserRole findById(int userRoleId) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT + " WHERE " + COLUMN_ID + "=" + userRoleId);
        ResultSet rs = statement.executeQuery();
        UserRole userRole = null;

        if (rs.next()) {
            userRole = new UserRole(rs.getInt(COLUMN_ID), rs.getInt(COLUMN_USER), rs.getInt(COLUMN_ROLE));
        }

        rs.close();
        statement.close();
        connection.close();

        return userRole;
    }

    @Override
    public List<UserRole> findAll() throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT);
        ResultSet rs = statement.executeQuery();
        List<UserRole> usersRole = new ArrayList<>();

        while (rs.next()) {
            usersRole.add(new UserRole(rs.getInt(COLUMN_ID), rs.getInt(COLUMN_USER), rs.getInt(COLUMN_ROLE)));
        }

        rs.close();
        statement.close();
        connection.close();

        return usersRole;
    }

    @Override
    public boolean addUserRole (UserRole userRole) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1, userRole.getUserId());
        statement.setInt(2, userRole.getRoleId());

        if (statement.executeUpdate() == 1) {
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                userRole.setUserRoleId(rs.getInt(COLUMN_ID));
            }
            rs.close();
        }

        statement.close();
        connection.close();

        return userRole.getUserId() != null;
    }

    @Override
    public void updateUserRole(UserRole userRole) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);

        statement.setInt(1, userRole.getUserId());
        statement.setInt(2, userRole.getRoleId());
        statement.setInt(3, userRole.getUserRoleId());

        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    @Override
    public void deleteUserRole(UserRole userRole) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);

        statement.setInt(1, userRole.getUserRoleId());
        statement.executeUpdate();

        statement.close();
        connection.close();
    }

}
