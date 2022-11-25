package com.example.taskmanager.Utils;

import com.example.taskmanager.Models.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public class ResponseManager<T> {
    public ApiResponse<T> success(String successMessage){
        return new ApiResponse<T>(successMessage,true, null);
    }

    public ApiResponse<T> error(String errMessage){
        return new ApiResponse<T>(errMessage,false, null);
    }


}
