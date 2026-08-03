
package Model;

import java.time.LocalDate;


public class ReportsModel {
    private int jobCardId;
    private LocalDate date;
    private String customerName;
    private String mechanicName;
    private double amount;
    private String status; 
    private String customerAndBike;
    public ReportsModel() {
    }

    public int getJobCardId() {
        return jobCardId;
    }

    public void setJobCardId(int jobCardId) {
        this.jobCardId = jobCardId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMechanicName() {
        return mechanicName;
    }

    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerAndBike() {
        return customerAndBike;
    }

    public void setCustomerAndBike(String customerAndBike) {
        this.customerAndBike = customerAndBike;
    }
    
}
