package ru.hw.springmvc.dao;

import ru.hw.springmvc.model.User;

import java.util.List;

public interface UserDao {

   List<User> getUserList();

   User getUserById(long id);

   User getUserByEmail(String name);

   void saveUser(User user);

   void deleteUserById(long id);

   void updateUserById(long id, User user);

}
