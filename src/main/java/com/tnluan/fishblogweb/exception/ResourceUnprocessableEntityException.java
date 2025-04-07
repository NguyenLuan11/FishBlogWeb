package com.tnluan.fishblogweb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class ResourceUnprocessableEntityException extends RuntimeException {

    public ResourceUnprocessableEntityException(String message) {
        super(message);
    }
}
