package com.ticket.railway.dao.implementation;

import com.ticket.railway.connection.PSQLConnector;
import com.ticket.railway.dao.RoleDAO;
import com.ticket.railway.entity.Role;
import com.ticket.railway.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgersRoleDAO implements RoleDAO
{
    private static final String COLUMN_ID = "role_id";
    private static final String COLUMN_NAME = "name_role";
    private static final String COLUMN_DESCRIPTION = "description";

    private static final String SELECT = "SELECT * FROM public.\"role\"";
    private static final String UPDATE = "UPDATE public.\"role\" SET " + COLUMN_NAME + "=?, "
            + COLUMN_DESCRIPTION +"=?" + " WHERE " + COLUMN_ID + "=?";
    private static final String INSERT = "INSERT INTO public.\"role\" (" + COLUMN_NAME + "," + COLUMN_DESCRIPTION +") VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM public.\"role\" WHERE " + COLUMN_ID + "=?";

    @Override
    public Role findById(int roleId) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT + " WHERE " + COLUMN_ID + "=" + roleId);
        ResultSet rs = statement.executeQuery();
        Role role = null;

        if (rs.next()) {
            role = new Role(rs.getInt(COLUMN_ID), rs.getString(COLUMN_NAME), rs.getString(COLUMN_DESCRIPTION));
        }

        rs.close();
        statement.close();
        connection.close();

        return role;
    }

    @Override
    public List<Role> findAll() throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT);
        ResultSet rs = statement.executeQuery();
        List<Role> roles = new ArrayList<>();

        while (rs.next()) {
            roles.add(new Role(rs.getInt(COLUMN_ID), rs.getString(COLUMN_NAME), rs.getString(COLUMN_DESCRIPTION)));
        }

        rs.close();
        statement.close();
        connection.close();

        return roles;
    }

    @Override
    public boolean addRole (Role role) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, role.getNameRole());
        statement.setString(2, role.getDescription());

        if (statement.executeUpdate() == 1) {
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                role.setRoleId(rs.getInt(COLUMN_ID));
            }
            rs.close();
        }

        statement.close();
        connection.close();

        return role.getRoleId() != null;
    }

    @Override
    public void updateRole(Role role) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);

        statement.setString(1, role.getNameRole());
        statement.setString(2, role.getDescription());
        statement.setInt(3, role.getRoleId());

        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    @Override
    public void deleteRole(Role role) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);

        statement.setInt(1, role.getRoleId());
        statement.executeUpdate();

        statement.close();
        connection.close();
    }
}
