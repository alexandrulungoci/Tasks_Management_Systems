package com.sdacademy.taskmanagement.services;

import com.sdacademy.taskmanagement.dao.TaskDao;
import com.sdacademy.taskmanagement.model.TaskModel;

import java.util.List;

public class TaskService {

    TaskDao taskDao = new TaskDao();

    public void addTask(TaskModel task) {
        taskDao.add(task);
    }

    public List<TaskModel> getTasks() {
        List<TaskModel> taskModelList = taskDao.getAllTasks();
        return taskModelList;
    }

    public void deleteTask(int id) {
        TaskModel taskToBeRemoved = taskDao.findTaskById(id);
        taskDao.remove(taskToBeRemoved);
    }

    public void changeTaskName(int id, String newName) {
        TaskModel taskToBeRenamed = taskDao.findTaskById(id);
        taskToBeRenamed.setName(newName);
        updateTask(taskToBeRenamed);
    }

    public void updateTask(TaskModel taskModel){
        taskDao.update(taskModel);
    }

}
