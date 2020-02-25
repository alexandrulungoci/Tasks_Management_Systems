package com.sdacademy.taskmanagement.services;

import com.sdacademy.taskmanagement.dao.SubTaskDao;
import com.sdacademy.taskmanagement.model.SubTaskModel;

public class SubTaskService {

    SubTaskDao subTaskDao = new SubTaskDao();

    public void addSubTask(SubTaskModel subTaskModel){
        subTaskDao.add(subTaskModel);
    }

    public void updateSubTask(SubTaskModel subTaskModel){
        subTaskDao.updateSubTask(subTaskModel);
    }
}
