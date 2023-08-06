package com.mftracker.data;

import lombok.Data;

@Data
public class NoteWithToken {
    private Note note;
    private String token;
}
