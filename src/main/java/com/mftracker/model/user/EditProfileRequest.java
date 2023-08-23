package com.mftracker.model.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EditProfileRequest {
    public String userUid;
    public String name;
    public String email;
    public String phoneNumber;
    public MultipartFile profilePhoto;
    public String userId;
}
