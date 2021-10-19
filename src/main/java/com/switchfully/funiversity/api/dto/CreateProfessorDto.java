package com.switchfully.funiversity.api.dto;

public class CreateProfessorDto {
    private String firstname;
    private String lastname;

    public CreateProfessorDto setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public CreateProfessorDto setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
