
package Model;

public class CustomerModel {
    private int customerId;
    private String customerName;
    private String phone;
    private String bikeNumber;
    private String brand;
    private String model;
    private int currentKm;
    private String address;
    private String notes;

    public CustomerModel() {
    }

    public CustomerModel(int customerId, String customerName, String phone, String bikeNumber, String brand, String model, int currentKm, String address, String notes) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.phone = phone;
        this.bikeNumber = bikeNumber;
        this.brand = brand;
        this.model = model;
        this.currentKm = currentKm;
        this.address = address;
        this.notes = notes;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBikeNumber() {
        return bikeNumber;
    }

    public void setBikeNumber(String bikeNumber) {
        this.bikeNumber = bikeNumber;
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

    public int getCurrentKm() {
        return currentKm;
    }

    public void setCurrentKm(int currentKm) {
        this.currentKm = currentKm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    
}
