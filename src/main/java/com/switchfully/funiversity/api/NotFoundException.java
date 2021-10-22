package com.switchfully.funiversity.api;

public class NotFoundException extends NullPointerException{
    public NotFoundException(String s) {
        super("Id not found in the database.");
    }
}
