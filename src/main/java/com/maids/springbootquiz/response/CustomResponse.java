package com.maids.springbootquiz.response;

public class CustomResponse<T> {

    private T data;

    private CustomResponseStatus status;

    private String message;

    public CustomResponse(T data, CustomResponseStatus status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public CustomResponseStatus getStatus() {
        return status;
    }

    public void setStatus(CustomResponseStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
