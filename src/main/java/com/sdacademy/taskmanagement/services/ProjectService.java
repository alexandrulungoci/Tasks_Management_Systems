package com.sdacademy.taskmanagement.services;

import com.sdacademy.taskmanagement.dao.ProjectDao;
import com.sdacademy.taskmanagement.model.ProjectModel;

import java.util.List;

public class ProjectService {

    ProjectDao projectDao = new ProjectDao();

    public void addProject(ProjectModel project) {
        projectDao.add(project);
    }

    public List<ProjectModel> getAllProjects(){
        List<ProjectModel> projectModelList = projectDao.getAll();
        return projectModelList;
    }

    public void removeProject(int id) {
        ProjectModel projectToBeRemoved = projectDao.findById(id);
        projectDao.remove(projectToBeRemoved);
    }

    public void changeName(int id, String newName) {
        ProjectModel projectToBeRenamed = projectDao.findById(id);
        projectToBeRenamed.setName(newName);
        projectDao.updateProject(projectToBeRenamed);
    }

    public ProjectModel findProjectById(int id) {
        ProjectModel projectModel = projectDao.findById(id);
        return projectModel;
    }

    public void updateProject(ProjectModel projectModel) {
        projectDao.updateProject(projectModel);
    }

}
