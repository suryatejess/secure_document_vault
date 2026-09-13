package com.suryatejess.secure_document_vault.request;

import com.suryatejess.secure_document_vault.enums.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class RegisterAppUserRequest {

    @NotNull
    String username;
    @NotNull
    String password;
    @Email
    String email;
    String firstName;
    String lastName;
    @NotNull
    RoleType roleType;


    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
