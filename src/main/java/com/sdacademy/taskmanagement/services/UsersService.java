package com.sdacademy.taskmanagement.services;

import com.sdacademy.taskmanagement.dao.UserDao;
import com.sdacademy.taskmanagement.model.UserModel;

import java.util.List;

public class UsersService {

    UserDao userDao = new UserDao();

    public void addUser(UserModel userModel) {
        userDao.add(userModel);
    }

    public List<UserModel> getUsers() {
        List<UserModel> userModelList = userDao.getAllUsers();
        return userModelList;
    }

    public UserModel findUserById(int id) {
        UserModel userModel = userDao.findUserById(id);
        return userModel;
    }

    public UserModel findUserByUserName(String userName){
        UserModel userModel = userDao.findByUserName(userName);
        return userModel;
    }
}
