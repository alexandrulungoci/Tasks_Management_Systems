package com.sdacademy.taskmanagement.services;

import com.sdacademy.taskmanagement.dao.ProjectDao;
import com.sdacademy.taskmanagement.model.ProjectModel;

import java.util.List;

public class ProjectService {

    ProjectDao projectDao = new ProjectDao();

    public void addProject(ProjectModel project) {
        projectDao.add(project);
    }

    public List<ProjectModel> getAllProjects() {
        List<ProjectModel> projectModelList = projectDao.getAllProjects();
        return projectModelList;
    }

    public void removeProject(int id) {
        ProjectModel projectToBeRemoved = projectDao.findProjectById(id);
        projectDao.remove(projectToBeRemoved);
    }

    public void changeProjectName(int id, String newName) {
        ProjectModel projectToBeRenamed = projectDao.findProjectById(id);
        projectToBeRenamed.setName(newName);
        projectDao.updateProject(projectToBeRenamed);
    }

    public ProjectModel findProjectById(int id) {
        ProjectModel projectModel = projectDao.findProjectById(id);
        return projectModel;
    }

    public void updateProject(ProjectModel projectModel) {
        projectDao.updateProject(projectModel);
    }

}
