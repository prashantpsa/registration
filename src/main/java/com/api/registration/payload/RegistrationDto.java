package com.api.registration.payload;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegistrationDto {
    @NotNull
    @Size(min=2, message = "minimum should be 2")
    private String name;
    @Email(message = "enter correct email format")
    private String email;
    @Size(min=10, max=10, message="please enter minimum 10 digit number")
    private String mobile;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}