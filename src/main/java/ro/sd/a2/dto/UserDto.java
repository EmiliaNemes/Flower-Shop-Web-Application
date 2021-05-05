package ro.sd.a2.dto;

import ro.sd.a2.entity.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class UserDto {

    private String id;

    @NotNull(message = "Field must be completed")
    @Pattern(regexp = "^([A-Z][a-z]+[ ]?)+$", message = "Name must contain one or more names that start with uppercase letter!")
    private String name;

    @NotNull(message = "Field must be completed")
    @Pattern(regexp = "^\\S+@\\S+\\.\\S+$", message = "Email Address must be a valid email!")
    private String emailAddress;

    @NotNull
    @Pattern(regexp = "[0-9]{10}$", message = "Phone Number must contain 10 digits!")
    private String phoneNumber;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+$", message = "Username must be a valid name!")
    private String username;

    @NotNull
    @Pattern(regexp = "(\\S){4,30}", message = "Password must have 4-30 characters!")
    private String password;

    @NotNull
    private Role role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
