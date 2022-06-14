package com.ticket.railway.dao.implementation;

import com.ticket.railway.connection.PSQLConnector;
import com.ticket.railway.dao.OrderDAO;
import com.ticket.railway.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgersOrderDAO implements OrderDAO
{
    private static final String COLUMN_ID = "order_id";
    private static final String COLUMN_USER = "user_id";
    private static final String COLUMN_TICKET = "ticket_id";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_PASSPORT = "number_passport";
    private static final String COLUMN_COMMENTS = "comments";

    private static final String SELECT = "SELECT * FROM public.\"order\"";
    private static final String UPDATE = "UPDATE public.\"order\" SET " + COLUMN_USER + "=?, " + COLUMN_TICKET + "=?, "
            + COLUMN_FIRST_NAME + "=?, " + COLUMN_LAST_NAME + "=?, "
            + COLUMN_PASSPORT + "=?, " + COLUMN_COMMENTS + "=?" + " WHERE "+ COLUMN_ID +"=?";
    private static final String INSERT = "INSERT INTO public.\"order\" (" + COLUMN_USER + ","
            + COLUMN_TICKET + "," + COLUMN_FIRST_NAME + ", " + COLUMN_LAST_NAME + ", "
            + COLUMN_PASSPORT + ", " + COLUMN_COMMENTS + ") VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM public.\"order\" WHERE " + COLUMN_ID + "=?";

    @Override
    public Order findById(int orderId) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT + " WHERE " + COLUMN_ID + "=" + orderId);
        ResultSet rs = statement.executeQuery();
        Order order = null;

        if (rs.next()) {
            order = new Order(rs.getInt(COLUMN_ID), rs.getInt(COLUMN_USER), rs.getInt(COLUMN_TICKET),
                    rs.getString(COLUMN_FIRST_NAME), rs.getString(COLUMN_LAST_NAME), rs.getString(COLUMN_PASSPORT),
                    rs.getString(COLUMN_COMMENTS));
        }

        rs.close();
        statement.close();
        connection.close();

        return order;
    }

    @Override
    public List<Order> findAll() throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT);
        ResultSet rs = statement.executeQuery();
        List<Order> orders = new ArrayList<>();

        while (rs.next()) {
            orders.add(new Order(rs.getInt(COLUMN_ID), rs.getInt(COLUMN_USER), rs.getInt(COLUMN_TICKET),
                    rs.getString(COLUMN_FIRST_NAME), rs.getString(COLUMN_LAST_NAME), rs.getString(COLUMN_PASSPORT), rs.getString(COLUMN_COMMENTS)));
        }

        rs.close();
        statement.close();
        connection.close();

        return orders;
    }

    @Override
    public boolean addOrder(Order order) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1, order.getUserId());
        statement.setInt(2, order.getTicketId());
        statement.setString(3, order.getFirstName());
        statement.setString(4, order.getLastName());
        statement.setString(5, order.getNumberPassport());
        statement.setString(6, order.getComments());

        if (statement.executeUpdate() == 1) {
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                order.setOrderId(rs.getInt(COLUMN_ID));
            }
            rs.close();
        }

        statement.close();
        connection.close();

        return order.getOrderId() != null;
    }

    @Override
    public void updateOrder(Order order) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);

        statement.setInt(1, order.getUserId());
        statement.setInt(2, order.getTicketId());
        statement.setString(3, order.getFirstName());
        statement.setString(4, order.getLastName());
        statement.setString(5, order.getNumberPassport());
        statement.setString(6, order.getComments());
        statement.setInt(7, order.getOrderId());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    @Override
    public void deleteOrder(Order order) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);

        statement.setInt(1, order.getOrderId());
        statement.executeUpdate();

        statement.close();
        connection.close();
    }
}
