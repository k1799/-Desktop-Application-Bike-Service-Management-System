
package Model;


public class MechanicModel {
    private int mechanicId;
    private String mechanicName;
    private String phone;
    private String email;
    private String idProof;
    private String specialization;
    private int experience;
    private String address;
    private String status;

    public MechanicModel() {
    }

    public MechanicModel(int mechanicId, String mechanicName, String phone, String email, String idProof, String specialization, int experience, String address, String status) {
        this.mechanicId = mechanicId;
        this.mechanicName = mechanicName;
        this.phone = phone;
        this.email = email;
        this.idProof = idProof;
        this.specialization = specialization;
        this.experience = experience;
        this.address = address;
        this.status = status;
    }

    public int getMechanicId() {
        return mechanicId;
    }

    public void setMechanicId(int mechanicId) {
        this.mechanicId = mechanicId;
    }

    public String getMechanicName() {
        return mechanicName;
    }

    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdProof() {
        return idProof;
    }

    public void setIdProof(String idProof) {
        this.idProof = idProof;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
