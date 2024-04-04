package au.com.telstra.simcardactivator;

public class ActivationResponse {
    private boolean success;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Boolean isSuccess()
    {
        return success;
    }
}
