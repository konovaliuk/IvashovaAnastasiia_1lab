package com.ticket.railway.dao;

import com.ticket.railway.entity.Train;
import java.sql.SQLException;
import java.util.List;

public interface TrainDAO
{
    public Train findById(int id) throws SQLException;
    public List<Train> findAll() throws SQLException;
    public boolean addTrain(Train train) throws SQLException;
    public void updateTrain(Train train) throws SQLException;
    public void deleteTrain(Train train) throws SQLException;
}
