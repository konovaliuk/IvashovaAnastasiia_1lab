package com.ticket.railway.dao;

import com.ticket.railway.entity.UserRole;

import java.sql.SQLException;
import java.util.List;

public interface UserRoleDAO
{
    public UserRole findById(int id) throws SQLException;
    public List<UserRole> findAll() throws SQLException;
    public boolean addUserRole (UserRole userRole) throws SQLException;
    public void updateUserRole(UserRole userRole) throws SQLException;
    public void deleteUserRole(UserRole userRole) throws SQLException;
}
