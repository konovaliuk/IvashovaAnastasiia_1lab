package com.ticket.railway.entity;

import java.util.Objects;

public class Ticket
{
    private Integer ticketId;
    private String typeCarriage;
    private Integer numberCarriage;
    private Integer numberPlace;
    private Integer train;
    private Double price;

    public Ticket(Integer ticketId, String typeCarriage, Integer numberCarriage,
                  Integer numberPlace, Integer train, Double price) {
        this.ticketId = ticketId;
        this.typeCarriage = typeCarriage;
        this.numberCarriage = numberCarriage;
        this.numberPlace = numberPlace;
        this.train = train;
        this.price = price;
    }

    public Ticket(String typeCarriage, Integer numberCarriage,
                  Integer numberPlace, Integer train, Double price) {
        this.typeCarriage = typeCarriage;
        this.numberCarriage = numberCarriage;
        this.numberPlace = numberPlace;
        this.train = train;
        this.price = price;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getTypeCarriage() {
        return typeCarriage;
    }

    public void setTypeCarriage(String typeCarriage) {
        this.typeCarriage = typeCarriage;
    }

    public Integer getNumberCarriage() {
        return numberCarriage;
    }

    public void setNumberCarriage(Integer numberCarriage) {
        this.numberCarriage = numberCarriage;
    }

    public Integer getNumberPlace() {
        return numberPlace;
    }

    public void setNumberPlace(Integer numberPlace) {
        this.numberPlace = numberPlace;
    }

    public Integer getTrain() {
        return train;
    }

    public void setTrain(Integer train) {
        this.train = train;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketId, ticket.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", typeCarriage='" + typeCarriage + '\'' +
                ", numberCarriage=" + numberCarriage +
                ", numberPlace=" + numberPlace +
                ", train=" + train +
                ", price=" + price +
                '}';
    }
}
