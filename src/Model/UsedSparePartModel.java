
package Model;


public class UsedSparePartModel {
    private int jobCardSpareId;
    private int jobCardId;
    private int partId;
    private String partName;
    private int quantity;
    private double unitPrice;
    private double totalPrice;

    public UsedSparePartModel() {
    }

    public UsedSparePartModel(int jobCardSpareId, int jobCardId, int partId, String partName, int quantity, double unitPrice, double totalPrice) {
        this.jobCardSpareId = jobCardSpareId;
        this.jobCardId = jobCardId;
        this.partId = partId;
        this.partName = partName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    public int getJobCardSpareId() {
        return jobCardSpareId;
    }

    public void setJobCardSpareId(int jobCardSpareId) {
        this.jobCardSpareId = jobCardSpareId;
    }

    public int getJobCardId() {
        return jobCardId;
    }

    public void setJobCardId(int jobCardId) {
        this.jobCardId = jobCardId;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    
}
