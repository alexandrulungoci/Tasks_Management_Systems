package com.sdacademy.taskmanagement.services;

import com.sdacademy.taskmanagement.dao.Dao;
import com.sdacademy.taskmanagement.model.ProjectModel;

import java.util.Date;

public class ProjectService {

    Dao dao = new Dao();

    public void addProject(ProjectModel project){
        dao.addProject(project);
    }

    public void deleteProject(int id){
        ProjectModel projectToBeDeleted = dao.findProjectById(id);
        dao.deleteProject(projectToBeDeleted);
    }

    public void changeName(int id, String newName){
        ProjectModel projectToBeRenamed = dao.findProjectById(id);
        projectToBeRenamed.setName(newName);
        dao.updateProject(projectToBeRenamed);
    }

    public void changeDeadline(int id, Date newDeadline){
        ProjectModel projectToBeChanged = dao.findProjectById(id);
        projectToBeChanged.setDeadline(newDeadline);
        dao.updateProject(projectToBeChanged);
    }
}
