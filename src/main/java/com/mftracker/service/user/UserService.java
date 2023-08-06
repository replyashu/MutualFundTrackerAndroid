package com.mftracker.service.user;

import com.mftracker.entity.User;

import java.util.List;

public interface UserService {

    public User saveUser(User user);

    public User findUserByEmail(String email);

    public User findByUserId(String uuid);

    public List<User> findAllUsers();

}
