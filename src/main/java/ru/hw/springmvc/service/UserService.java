package ru.hw.springmvc.service;

import ru.hw.springmvc.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(long id);

    User getUserByEmail(String email);

    void saveUser(User user);

    void deleteUser(long id);

    void updateUser(long id, User user);

}