package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.user.User;
import ru.kata.spring.boot_security.demo.dao.UserDao;

import java.util.List;

public interface UserService extends UserDao {
    List<User>getAllUsers ();
    void saveUser(User user);
    void removeUser(long id);
    void updateUser(User user);
    User getUserById(long id);
}
