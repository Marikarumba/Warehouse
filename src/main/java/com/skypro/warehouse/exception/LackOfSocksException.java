package com.skypro.warehouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LackOfSocksException extends Throwable {
    public String message;

    public LackOfSocksException(String message) {
        this.message = message;
    }
}
