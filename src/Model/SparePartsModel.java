
package Model;


public class SparePartsModel {
    private int partId;
    private String partNumber;
    private String partName;
    private String category;
    private String brand;
    private String compatibleVehicle;
    private double costPrice;
    private double sellingPrice;
    private int openingQuantity;
    private int currentStock;
    private int minStock;
    private String rackLocation;
    private String notes;  

    public SparePartsModel() {
    }

    public SparePartsModel(int partId, String partNumber, String partName, String category, String brand, String compatibleVehicle, double costPrice, double sellingPrice, int openingQuantity, int currentStock, int minStock, String rackLocation, String notes) {
        this.partId = partId;
        this.partNumber = partNumber;
        this.partName = partName;
        this.category = category;
        this.brand = brand;
        this.compatibleVehicle = compatibleVehicle;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.openingQuantity = openingQuantity;
        this.currentStock = currentStock;
        this.minStock = minStock;
        this.rackLocation = rackLocation;
        this.notes = notes;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCompatibleVehicle() {
        return compatibleVehicle;
    }

    public void setCompatibleVehicle(String compatibleVehicle) {
        this.compatibleVehicle = compatibleVehicle;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getOpeningQuantity() {
        return openingQuantity;
    }

    public void setOpeningQuantity(int openingQuantity) {
        this.openingQuantity = openingQuantity;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public int getMinStock() {
        return minStock;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }

    public String getRackLocation() {
        return rackLocation;
    }

    public void setRackLocation(String rackLocation) {
        this.rackLocation = rackLocation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
}
