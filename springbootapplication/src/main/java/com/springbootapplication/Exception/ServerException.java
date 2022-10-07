package com.springbootapplication.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ServerException extends Exception{

    public ServerException(){
        super("Server error");
    }

}
