package com.maids.springbootquiz.response;

public class CustomResponseHelper<T> {

    public static <T> CustomResponse<T> successResponse(T t){
        return new CustomResponse<>(t, CustomResponseStatus.SUCCESS, "");
    }

    public static <T> CustomResponse<T> failureResponse(String msg){
        return new CustomResponse<>(null, CustomResponseStatus.FAILURE, msg);
    }
}
