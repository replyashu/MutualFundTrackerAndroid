package com.mftracker.model.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegistrationResponse {
    public String userUid;
    public String name;
    public String email;
    public String phoneNumber;
    public String profilePhoto;

}
