package com.sdacademy.taskmanagement.services;

import com.sdacademy.taskmanagement.dao.SubTaskDao;
import com.sdacademy.taskmanagement.model.SubTaskModel;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SubTaskService {

    SubTaskDao subTaskDao = new SubTaskDao();

    public List<SubTaskModel> getAllSubTasks() {
        List<SubTaskModel> subTaskModelList = subTaskDao.getAllSubtasks();
        return subTaskModelList;
    }

    public void addSubTask(SubTaskModel subTaskModel) {
        subTaskDao.add(subTaskModel);
    }

    public void updateSubTask(SubTaskModel subTaskModel) {
        subTaskDao.updateSubTask(subTaskModel);
    }

    public SubTaskModel findSubTaskById(int id) {
        SubTaskModel subTaskModel = subTaskDao.findSubtaskById(id);
        return subTaskModel;
    }

    public void changeDeadline(int id, Date newDeadline) {
        SubTaskModel subTaskToBeChanged = subTaskDao.findSubtaskById(id);
        subTaskToBeChanged.setDeadline(newDeadline);
        subTaskDao.updateSubTask(subTaskToBeChanged);
    }

    public List<SubTaskModel> getSubTaskWithoutUser(){
        List<SubTaskModel> all = subTaskDao.getAllSubtasks();
        List<SubTaskModel> subTaskModelList = all.stream().filter(s-> s.getUserModel()==null).collect(Collectors.toList());
        return subTaskModelList;
    }

    public List<SubTaskModel> getSubTaskByUser(int id) {
        List<SubTaskModel> subTaskModelList = subTaskDao.getSubTaskByUser(id);
        return subTaskModelList;
    }
}