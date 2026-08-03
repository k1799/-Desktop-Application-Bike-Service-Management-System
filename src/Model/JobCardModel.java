
package Model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class JobCardModel {
private int jobCardId;
private int customerId;
private int mechanicId;
private String serviceType;
private String fuelLevel;
private int currentKm;
private String complaint;
private double approximateCost;
private LocalDate deliveryDate;
private String status;
private Timestamp createdDate;
private String brand;
private String model;

// TableView Display Fields
    private String customerName;
    private String bikeNumber;
    private String mechanicName;

    public JobCardModel() {
    }

    public JobCardModel(int jobCardId, int customerId, int mechanicId, String serviceType, String fuelLevel, int currentKm, String complaint, double approximateCost, LocalDate deliveryDate, String status, Timestamp createdDate, String brand, String model) {
        this.jobCardId = jobCardId;
        this.customerId = customerId;
        this.mechanicId = mechanicId;
        this.serviceType = serviceType;
        this.fuelLevel = fuelLevel;
        this.currentKm = currentKm;
        this.complaint = complaint;
        this.approximateCost = approximateCost;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.createdDate = createdDate;
        this.brand = brand;
        this.model = model;
    }


    

    public int getJobCardId() {
        return jobCardId;
    }

    public void setJobCardId(int jobCardId) {
        this.jobCardId = jobCardId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getMechanicId() {
        return mechanicId;
    }

    public void setMechanicId(int mechanicId) {
        this.mechanicId = mechanicId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(String fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public int getCurrentKm() {
        return currentKm;
    }

    public void setCurrentKm(int currentKm) {
        this.currentKm = currentKm;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public double getApproximateCost() {
        return approximateCost;
    }

    public void setApproximateCost(double approximateCost) {
        this.approximateCost = approximateCost;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

   

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBikeNumber() {
        return bikeNumber;
    }

    public void setBikeNumber(String bikeNumber) {
        this.bikeNumber = bikeNumber;
    }

    public String getMechanicName() {
        return mechanicName;
    }

    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


}
