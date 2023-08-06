package com.mftracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Table(name = "user-details")
@Entity
@Getter
@Setter
public class User {
    @Id
    public String userId;
    public String email;
    public String name;
    public String imageUrl;
    public String pushNotificationToken;
    public String isFukraUSer;
    public String source;
}

