package com.mftracker.model.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TokenRequest {
    public String userId;
    public String token;
}
