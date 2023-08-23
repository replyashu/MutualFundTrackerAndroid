package com.mftracker.model.user;

import lombok.Data;

@Data
public class RegisterUserRequest {
    public String token;
    public String phoneNumber;
    public String email;

}
