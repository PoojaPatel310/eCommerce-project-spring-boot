package com.aurosoft.ecommerce.service;

import com.aurosoft.ecommerce.entity.User;

import java.util.List;

public interface UserService {

    List<User> listAllUsers();
    User getUserById(int id);
    User insertUser(User user);
    User updateUser(User user);
    int deleteUser(int id);

    User findByEmailAndPassword(String email,String password);
    boolean existsByEmail(String email);
    User findByEmail(String email);


}
