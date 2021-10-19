package com.switchfully.funiversity.api.dto;

public class UpdateProfessorDto {
    private String firstname;
    private String lastname;

    public UpdateProfessorDto setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UpdateProfessorDto setLastname(String lastname) {
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
