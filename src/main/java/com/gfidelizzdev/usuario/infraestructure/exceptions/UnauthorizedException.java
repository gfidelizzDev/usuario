package com.gfidelizzdev.usuario.infraestructure.exceptions;

import javax.naming.AuthenticationException;

public class UnauthorizedException extends AuthenticationException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
