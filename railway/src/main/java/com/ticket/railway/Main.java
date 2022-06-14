package com.ticket.railway;

import com.ticket.railway.dao.*;
import com.ticket.railway.dao.implementation.*;
import com.ticket.railway.entity.*;

import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException
    {
        //--------------------USER TABLE-----------------//
        UserDAO userDAO = new PostgresUserDAO();
        User user = new User("Frog09",
                "ffhudfuuh",
                "Oleg",
                "Targhonskiy",
                Date.valueOf("2003-02-07"),
                "+380980458679",
                "frog@gmail.com");
        System.out.println("------------------------User Table--------------------------");
        System.out.println(userDAO.findAll());
        System.out.println("-------------Insert data of user in User Table--------------");
        System.out.println("Inserted date:");
        System.out.println(userDAO.addUser(user));
        System.out.println("Check (whether the data was written in the table):");
        System.out.println(userDAO.findAll());
        System.out.println("-------------Update data of user in User Table---------------");
        user.setPassword("chsudf8r3485");
        userDAO.updateUser(user);
        System.out.println(userDAO.findById(user.getUserId()));
        System.out.println("--------------------Delete user in User Table----------------");
        userDAO.deleteUser(user);
        System.out.println(userDAO.findAll());
        System.out.println("==============================================================");

        //-----------------------ROLE TABLE---------------------//
        RoleDAO roleDAO = new PostgersRoleDAO();
        Role role = new Role("Manager",
                "Can manage users");
        System.out.println("------------------------Role Table--------------------------");
        System.out.println(roleDAO.findAll());
        System.out.println("-------------Insert data of role in Role Table--------------");
        System.out.println("Inserted date:");
        System.out.println(roleDAO.addRole(role));
        System.out.println("Check (whether the data was written in the table):");
        System.out.println(roleDAO.findAll());
        System.out.println("-------------Update data of user in User Table---------------");
        role.setNameRole("User Manager");
        roleDAO.updateRole(role);
        System.out.println(roleDAO.findById(role.getRoleId()));
        System.out.println("--------------------Delete user in User Table----------------");
        roleDAO.deleteRole(role);
        System.out.println(roleDAO.findAll());
        System.out.println("==============================================================");

        //---------------------USER-ROLE TABLE------------------//
        UserRoleDAO userRoleDAO = new PostgresUserRoleDAO();
        UserRole userRole = new UserRole(16, 1);
        System.out.println("------------------------User-Role Table--------------------------");
        System.out.println(userRoleDAO.findAll());
        System.out.println("-------------Insert data of user-role in User-Role Table--------------");
        System.out.println("Inserted date:");
        System.out.println(userRoleDAO.addUserRole(userRole));
        System.out.println("Check (whether the data was written in the table):");
        System.out.println(userRoleDAO.findAll());
        System.out.println("-------------Update data of user-role in User-Role Table---------------");
        userRole.setRoleId(2);
        userRoleDAO.updateUserRole(userRole);
        System.out.println(userRoleDAO.findById(userRole.getUserRoleId()));
        System.out.println("--------------------Delete user-role in User-Role Table----------------");
        userRoleDAO.deleteUserRole(userRole);
        System.out.println(userRoleDAO.findAll());
        System.out.println("==============================================================");

        //----------------------TRAIN TABLE------------------//
        TrainDAO trainDAO = new PostgresTrainDAO();
        Train train = new Train(320,
                "passenger",
                "Lviv",
                Date.valueOf("2022-07-23"),
                Time.valueOf("07:00:00"),
                "Varshava",
                Date.valueOf("2022-07-24"),
                Time.valueOf("10:30:00"));
        System.out.println("------------------------Train Table--------------------------");
        System.out.println(trainDAO.findAll());
        System.out.println("-------------Insert data of train in Train Table--------------");
        System.out.println("Inserted date:");
        System.out.println(trainDAO.addTrain(train));
        System.out.println("Check (whether the data was written in the table):");
        System.out.println(trainDAO.findAll());
        System.out.println("-------------Update data of train in Train Table---------------");
        train.setDestinatonTime(Time.valueOf("11:30:00"));
        trainDAO.updateTrain(train);
        System.out.println(trainDAO.findById(train.getTrainId()));
        System.out.println("--------------------Delete train in Train Table----------------");
        trainDAO.deleteTrain(train);
        System.out.println(trainDAO.findAll());
        System.out.println("==============================================================");

        //----------------------TICKET TABLE--------------------//
        TicketDAO ticketDAO = new PostgresTicketDAO();
        Ticket ticket = new Ticket("reserved seat",
                12,
                23,
                18,
                200.00);
        System.out.println("------------------------Ticket Table--------------------------");
        System.out.println(ticketDAO.findAll());
        System.out.println("-------------Insert data of ticket in Ticket Table--------------");
        System.out.println("Inserted date:");
        System.out.println(ticketDAO.addTicket(ticket));
        System.out.println("Check (whether the data was written in the table):");
        System.out.println(ticketDAO.findAll());
        System.out.println("-------------Update data of ticket in Ticket Table---------------");
        ticket.setNumberPlace(15);
        ticketDAO.updateTicket(ticket);
        System.out.println(ticketDAO.findById(ticket.getTicketId()));
        System.out.println("--------------------Delete ticket in Ticket Table----------------");
        ticketDAO.deleteTicket(ticket);
        System.out.println(ticketDAO.findAll());
        System.out.println("==============================================================");

        //-----------------------ORDER TABLE---------------------//
        OrderDAO orderDAO = new PostgersOrderDAO();
        Order order = new Order(16,
                1,
                "Nastia",
                "Lebediva",
                "045056060",
                null);
        System.out.println("------------------------Order Table--------------------------");
        System.out.println(orderDAO.findAll());
        System.out.println("-------------Insert data of order in Order Table--------------");
        System.out.println("Inserted date:");
        System.out.println(orderDAO.addOrder(order));
        System.out.println("Check (whether the data was written in the table):");
        System.out.println(orderDAO.findAll());
        System.out.println("-------------Update data of order in Order Table---------------");
        order.setComments("Do you have a hotel?");
        orderDAO.updateOrder(order);
        System.out.println(orderDAO.findById(order.getOrderId()));
        System.out.println("--------------------Delete order in Order Table----------------");
        orderDAO.deleteOrder(order);
        System.out.println(orderDAO.findAll());
        System.out.println("==============================================================");
    }
}
