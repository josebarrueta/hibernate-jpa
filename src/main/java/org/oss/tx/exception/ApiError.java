package org.oss.tx.exception;

public class ApiError {

    private final int status;

    private final String description;

    public ApiError(int status, String description) {
        this.status = status;
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

}
