package com.SilentEight.algorithm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Server side error")
public class ServerSideError extends RuntimeException {
    public ServerSideError(){
        super();
    }
}
