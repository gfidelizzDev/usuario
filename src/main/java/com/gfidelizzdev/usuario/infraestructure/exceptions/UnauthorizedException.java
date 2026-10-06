package com.gfidelizzdev.usuario.infraestructure.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message, Throwable throwable) {
        super(message);
    }

}

