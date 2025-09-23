package model;

public class ResultDTO {
    private String message;
    private boolean isSuccessful;

    // default constructor
    public ResultDTO() {}
    public ResultDTO(String message, boolean isSuccessful) {
        this.message = message;
        this.isSuccessful = isSuccessful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }
}