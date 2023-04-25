package com.skypro.warehouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SocksNotFoundException extends Throwable {
    public String message;

    public SocksNotFoundException(String message) {
        this.message = message;
    }

}
