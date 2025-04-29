package edu.icet.service;

import edu.icet.dto.User;

public interface UserService {
    void register(User user);
    User login(String email,String password);
}
