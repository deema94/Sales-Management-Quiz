package com.maids.springbootquiz.exception;

import java.util.Date;

public class ErrorResponse {

    private Date timestamp;
    private String details;

    public ErrorResponse(Date timestamp, String details) {
        this.timestamp = timestamp;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
