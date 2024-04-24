package com.thenavigator.crud_backend.exception;

public class UserNotFoundExeption extends RuntimeException {
    public UserNotFoundExeption(Long id) {
        super("Could not found user with id " + id);
    }
}
