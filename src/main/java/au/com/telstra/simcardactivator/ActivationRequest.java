package au.com.telstra.simcardactivator;

public class ActivationRequest {
    private String iccid;
    private String customerEmail;

    // Getters and setters

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }
}