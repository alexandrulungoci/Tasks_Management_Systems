package com.sdacademy.taskmanagement.services;

import com.sdacademy.taskmanagement.dao.SubTaskDao;
import com.sdacademy.taskmanagement.model.SubTaskModel;
import com.sdacademy.taskmanagement.model.UserModel;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SubTaskService {

    SubTaskDao subTaskDao = new SubTaskDao();
    UsersService usersService = new UsersService();

    public List<SubTaskModel> getAllSubTasks() {
        List<SubTaskModel> subTaskModelList = subTaskDao.getAllSubtasks();
        return subTaskModelList;
    }

    public void addSubTask(SubTaskModel subTaskModel) {
        subTaskDao.add(subTaskModel);
    }


    public SubTaskModel findSubTaskById(int id) {
        SubTaskModel subTaskModel = subTaskDao.findSubtaskById(id);
        return subTaskModel;
    }

    public void changeDeadline(int id, Date newDeadline) {
        SubTaskModel subTaskToBeChanged = subTaskDao.findSubtaskById(id);
        subTaskToBeChanged.setDeadline(newDeadline);
        updateSubtask(subTaskToBeChanged);
    }

    public List<SubTaskModel> getSubTaskWithoutUser() {
        List<SubTaskModel> all = subTaskDao.getAllSubtasks();
        List<SubTaskModel> subTaskModelList = all.stream().filter(s -> s.getUserModel() == null).collect(Collectors.toList());
        return subTaskModelList;
    }

    public List<SubTaskModel> getSubTaskByUser(int id) {
        List<SubTaskModel> subTaskModelList = subTaskDao.getSubTaskByUser(id);
        return subTaskModelList;
    }

    public void changeSubtaskName(int id, String newName) {
        SubTaskModel subtaskToBeRenamed = subTaskDao.findSubtaskById(id);
        subtaskToBeRenamed.setName(newName);
        updateSubtask(subtaskToBeRenamed);
    }

    public void deleteSubtask(int id) {
        SubTaskModel subtaskToBeRemoved = subTaskDao.findSubtaskById(id);
        subTaskDao.remove(subtaskToBeRemoved);
    }

    public List<SubTaskModel> chooseStatus(String status) {
        List<SubTaskModel> subTaskModelList = getAllSubTasks().stream()
                .filter(s -> s.getStatus().equals(status))
                .collect(Collectors.toList());
        return subTaskModelList;
    }

    public void assignUserToSubTask(int subtaskId, int userId) {
        SubTaskModel subTaskModel = findSubTaskById(subtaskId);
        UserModel userModel = usersService.findUserById(userId);
        subTaskModel.setUserModel(userModel);
        updateSubtask(subTaskModel);
    }

    public void updateSubtask(SubTaskModel subTaskModel) {
        subTaskDao.update(subTaskModel);
    }
}
