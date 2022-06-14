package com.ticket.railway.entity;

import java.util.Objects;

public class Order
{
    private Integer orderId;
    private Integer userId;
    private Integer ticketId;
    private String firstName;
    private String lastName;
    private String numberPassport;
    private String comments;

    public Order(Integer orderId, Integer userId, Integer ticketId, String firstName,
                 String lastName, String numberPassport, String comments) {
        this.orderId = orderId;
        this.userId = userId;
        this.ticketId = ticketId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberPassport = numberPassport;
        this.comments = comments;
    }

    public Order(Integer userId, Integer ticketId, String firstName,
                 String lastName, String numberPassport, String comments) {
        this.userId = userId;
        this.ticketId = ticketId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberPassport = numberPassport;
        this.comments = comments;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumberPassport() {
        return numberPassport;
    }

    public void setNumberPassport(String numberPassport) {
        this.numberPassport = numberPassport;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", ticketId=" + ticketId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", numberPassport='" + numberPassport + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
