package com.ticket.railway.dao.implementation;

import com.ticket.railway.connection.PSQLConnector;
import com.ticket.railway.dao.TrainDAO;
import com.ticket.railway.entity.Train;
import com.ticket.railway.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresTrainDAO implements TrainDAO
{
    private static final String COLUMN_ID = "train_id";
    private static final String COLUMN_NUMBER = "number_train";
    private static final String COLUMN_TYPE = "type_train";
    private static final String COLUMN_DEPARTURE_POINT = "departure_point";
    private static final String COLUMN_DEPARTURE_DATE = "departure_date";
    private static final String COLUMN_DEPARTURE_TIME = "departure_time";
    private static final String COLUMN_DESTINATION = "destination";
    private static final String COLUMN_DESTINATION_DATE = "destination_date";
    private static final String COLUMN_DESTINATION_TIME = "destination_time";

    private static final String SELECT = "SELECT * FROM public.\"train\"";
    private static final String UPDATE = "UPDATE public.\"train\" SET " + COLUMN_NUMBER + "=?, "
            + COLUMN_TYPE + "=?, " + COLUMN_DEPARTURE_POINT + "=?, "
            + COLUMN_DEPARTURE_DATE + "=?, " + COLUMN_DEPARTURE_TIME + "=?, " + COLUMN_DESTINATION + "=?, "
            + COLUMN_DESTINATION_DATE + "=?, " + COLUMN_DESTINATION_TIME + "=?"+ " WHERE "+ COLUMN_ID +"=?";
    private static final String INSERT = "INSERT INTO public.\"train\" (" + COLUMN_NUMBER + ", "
            + COLUMN_TYPE + ", " + COLUMN_DEPARTURE_POINT + ", "
            + COLUMN_DEPARTURE_DATE + ", " + COLUMN_DEPARTURE_TIME + ", " + COLUMN_DESTINATION + ", "
            + COLUMN_DESTINATION_DATE + ", " + COLUMN_DESTINATION_TIME + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM public.\"train\" WHERE " + COLUMN_ID + "=?";

    @Override
    public Train findById(int trainId) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT + " WHERE " + COLUMN_ID + "=" + trainId);
        ResultSet rs = statement.executeQuery();
        Train train = null;

        if (rs.next()) {
            train = new Train(rs.getInt(COLUMN_ID), rs.getInt(COLUMN_NUMBER), rs.getString(COLUMN_TYPE), rs.getString(COLUMN_DEPARTURE_POINT),
                    rs.getDate(COLUMN_DEPARTURE_DATE), rs.getTime(COLUMN_DEPARTURE_TIME), rs.getString(COLUMN_DESTINATION),
                    rs.getDate(COLUMN_DESTINATION_DATE), rs.getTime(COLUMN_DESTINATION_TIME));
        }

        rs.close();
        statement.close();
        connection.close();

        return train;
    }

    @Override
    public List<Train> findAll() throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT);
        ResultSet rs = statement.executeQuery();
        List<Train> trains = new ArrayList<>();

        while (rs.next()) {
            trains.add(new Train(rs.getInt(COLUMN_ID), rs.getInt(COLUMN_NUMBER), rs.getString(COLUMN_TYPE), rs.getString(COLUMN_DEPARTURE_POINT),
                    rs.getDate(COLUMN_DEPARTURE_DATE), rs.getTime(COLUMN_DEPARTURE_TIME), rs.getString(COLUMN_DESTINATION),
                    rs.getDate(COLUMN_DESTINATION_DATE), rs.getTime(COLUMN_DESTINATION_TIME)));
        }

        rs.close();
        statement.close();
        connection.close();

        return trains;
    }

    @Override
    public boolean addTrain (Train train) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1,train.getNumberTrain());
        statement.setString(2, train.getTypeTrain());
        statement.setString(3, train.getDeparturePoint());
        statement.setDate(4, train.getDepartureDate());
        statement.setTime(5, train.getDepartureTime());
        statement.setString(6, train.getDestination());
        statement.setDate(7, train.getDestinationDate());
        statement.setTime(8, train.getDestinatonTime());

        if (statement.executeUpdate() == 1) {
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                train.setTrainId(rs.getInt(COLUMN_ID));
            }
            rs.close();
        }

        statement.close();
        connection.close();

        return train.getTrainId() != null;
    }

    @Override
    public void updateTrain(Train train) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);

        statement.setInt(1,train.getNumberTrain());
        statement.setString(2, train.getTypeTrain());
        statement.setString(3, train.getDeparturePoint());
        statement.setDate(4, train.getDepartureDate());
        statement.setTime(5, train.getDepartureTime());
        statement.setString(6, train.getDestination());
        statement.setDate(7, train.getDestinationDate());
        statement.setTime(8, train.getDestinatonTime());
        statement.setInt(9, train.getTrainId());

        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    @Override
    public void deleteTrain(Train train) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);

        statement.setInt(1, train.getTrainId());
        statement.executeUpdate();

        statement.close();
        connection.close();
    }
}
