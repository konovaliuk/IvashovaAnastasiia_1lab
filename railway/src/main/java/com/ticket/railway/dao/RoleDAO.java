package com.ticket.railway.dao;


import com.ticket.railway.entity.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleDAO
{
    public Role findById(int id) throws SQLException;
    public List<Role> findAll() throws SQLException;
    public boolean addRole (Role role) throws SQLException;
    public void updateRole(Role role) throws SQLException;
    public void deleteRole(Role role) throws SQLException;
}
