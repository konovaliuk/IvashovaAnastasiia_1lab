package com.ticket.railway.dao;

import com.ticket.railway.entity.Ticket;
import com.ticket.railway.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface TicketDAO
{
    public Ticket findById(int id) throws SQLException;
    public List<Ticket> findAll() throws SQLException;
    public boolean addTicket (Ticket ticket) throws SQLException;
    public void updateTicket(Ticket ticket) throws SQLException;
    public void deleteTicket(Ticket ticket) throws SQLException;
}
