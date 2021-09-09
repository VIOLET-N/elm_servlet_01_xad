package com.neuedu.elm.service;

import com.neuedu.elm.entity.User;

public interface UserService {
    public int saveUser(User u);
    public User getUserByIdByPass(String id, String pass);
    public User getUserById(String id);
}
