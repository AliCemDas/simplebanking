package com.eteration.simplebanking.model.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceException extends RuntimeException {
    private String message;

    public ServiceException(String message) {
        this.message = message;
    }
}


