package com.ticket.railway.dao.implementation;

import com.ticket.railway.connection.PSQLConnector;
import com.ticket.railway.dao.TicketDAO;
import com.ticket.railway.entity.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresTicketDAO implements TicketDAO
{
    private static final String COLUMN_ID = "ticket_id";
    private static final String COLUMN_TYPE_CARRIAGE = "type_carriage";
    private static final String COLUMN_NUMBER_CARRIAGE = "number_carriage";
    private static final String COLUMN_NUMBER_PLACE = "number_place";
    private static final String COLUMN_TRAIN = "train_id";
    private static final String COLUMN_PRICE = "price";

    private static final String SELECT = "SELECT * FROM public.\"ticket\"";
    private static final String UPDATE = "UPDATE public.\"ticket\" SET " + COLUMN_TYPE_CARRIAGE + "=?, " + COLUMN_NUMBER_CARRIAGE + "=?, "
            + COLUMN_NUMBER_PLACE + "=?, " + COLUMN_TRAIN + "=?, "
            + COLUMN_PRICE + "=?" + " WHERE " + COLUMN_ID + "=?";
    private static final String INSERT = "INSERT INTO public.\"ticket\" (" + COLUMN_TYPE_CARRIAGE + ","
            + COLUMN_NUMBER_CARRIAGE + "," + COLUMN_NUMBER_PLACE + ", " + COLUMN_TRAIN + ", "
            + COLUMN_PRICE +") VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM public.\"ticket\" WHERE " + COLUMN_ID + "=?";

    @Override
    public Ticket findById(int ticketId) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT + " WHERE " + COLUMN_ID + "=" + ticketId);
        ResultSet rs = statement.executeQuery();
        Ticket ticket = null;

        if (rs.next()) {
            ticket = new Ticket(rs.getInt(COLUMN_ID), rs.getString(COLUMN_TYPE_CARRIAGE), rs.getInt(COLUMN_NUMBER_CARRIAGE),
                     rs.getInt(COLUMN_NUMBER_PLACE), rs.getInt(COLUMN_TRAIN), rs.getDouble(COLUMN_PRICE));
        }

        rs.close();
        statement.close();
        connection.close();

        return ticket;
    }

    @Override
    public List<Ticket> findAll() throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT);
        ResultSet rs = statement.executeQuery();
        List<Ticket> tickets = new ArrayList<>();

        while (rs.next()) {
            tickets.add(new Ticket(rs.getInt(COLUMN_ID), rs.getString(COLUMN_TYPE_CARRIAGE), rs.getInt(COLUMN_NUMBER_CARRIAGE),
                    rs.getInt(COLUMN_NUMBER_PLACE), rs.getInt(COLUMN_TRAIN), rs.getDouble(COLUMN_PRICE)));
        }

        rs.close();
        statement.close();
        connection.close();

        return tickets;
    }

    @Override
    public boolean addTicket (Ticket ticket) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);


        statement.setString(1, ticket.getTypeCarriage());
        statement.setInt(2, ticket.getNumberCarriage());
        statement.setInt(3, ticket.getNumberPlace());
        statement.setInt(4, ticket.getTrain());
        statement.setDouble(5, ticket.getPrice());

        if (statement.executeUpdate() == 1) {
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                ticket.setTicketId(rs.getInt(COLUMN_ID));
            }
            rs.close();
        }

        statement.close();
        connection.close();

        return ticket.getTicketId() != null;
    }

    @Override
    public void updateTicket(Ticket ticket) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);

        statement.setString(1, ticket.getTypeCarriage());
        statement.setInt(2, ticket.getNumberCarriage());
        statement.setInt(3, ticket.getNumberPlace());
        statement.setInt(4, ticket.getTrain());
        statement.setDouble(5, ticket.getPrice());
        statement.setInt(6, ticket.getTicketId());

        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    @Override
    public void deleteTicket(Ticket ticket) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);

        statement.setInt(1, ticket.getTicketId());
        statement.executeUpdate();

        statement.close();
        connection.close();
    }
}

