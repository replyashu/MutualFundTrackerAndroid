package com.mftracker.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.mftracker.data.NoteWithToken;
import com.mftracker.service.firebase.FirebaseMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private FirebaseMessagingService firebaseMessagingService;

    @RequestMapping("/send-notification")
    @ResponseBody
    public String sendNotification(@RequestBody NoteWithToken note) throws FirebaseMessagingException {
        return firebaseMessagingService.sendNotification(note.getNote(), note.getToken());
    }
}
