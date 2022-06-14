package com.ticket.railway.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Train
{
    private Integer trainId;
    private Integer numberTrain;
    private String typeTrain;
    private String departurePoint;
    private Date departureDate;
    private Time departureTime;
    private String destination;
    private Date destinationDate;
    private Time destinatonTime;

    public Train(Integer trainId, Integer numberTrain, String typeTrain, String departurePoint, Date departureDate,
                 Time departureTime, String destination, Date destinationDate, Time destinatonTime)
    {
        this.trainId = trainId;
        this.numberTrain = numberTrain;
        this.typeTrain = typeTrain;
        this.departurePoint = departurePoint;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.destination = destination;
        this.destinationDate = destinationDate;
        this.destinatonTime = destinatonTime;
    }

    public Train(Integer numberTrain, String typeTrain, String departurePoint, Date departureDate,
                 Time departureTime, String destination, Date destinationDate, Time destinatonTime)
    {
        this.numberTrain = numberTrain;
        this.typeTrain = typeTrain;
        this.departurePoint = departurePoint;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.destination = destination;
        this.destinationDate = destinationDate;
        this.destinatonTime = destinatonTime;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getNumberTrain() {
        return numberTrain;
    }

    public void setNumberTrain(Integer numberTrain) {
        this.numberTrain = numberTrain;
    }

    public String getTypeTrain() {
        return typeTrain;
    }

    public void setTypeTrain(String typeTrain) {
        this.typeTrain = typeTrain;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDestinationDate() {
        return destinationDate;
    }

    public void setDestinationDate(Date destinationDate) {
        this.destinationDate = destinationDate;
    }

    public Time getDestinatonTime() {
        return destinatonTime;
    }

    public void setDestinatonTime(Time destinatonTime) {
        this.destinatonTime = destinatonTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return Objects.equals(trainId, train.trainId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainId);
    }

    @Override
    public String toString() {
        return "Train{" +
                "trainId=" + trainId +
                ", numberTrain=" + numberTrain +
                ", typeTrain='" + typeTrain + '\'' +
                ", departurePoint='" + departurePoint + '\'' +
                ", departureDate=" + departureDate +
                ", departureTime=" + departureTime +
                ", destination='" + destination + '\'' +
                ", destinationDate=" + destinationDate +
                ", destinatonTime=" + destinatonTime +
                '}';
    }
}
