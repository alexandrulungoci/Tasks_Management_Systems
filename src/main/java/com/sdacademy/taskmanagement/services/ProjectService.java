package com.sdacademy.taskmanagement.services;

import com.sdacademy.taskmanagement.dao.ProjectDao;
import com.sdacademy.taskmanagement.model.ProjectModel;
import com.sdacademy.taskmanagement.model.SubTaskModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectService {

    ProjectDao projectDao = new ProjectDao();

    public void addProject(ProjectModel project){
        projectDao.add(project);
    }

    public void removeProject(int id){
        ProjectModel projectToBeRemoved = projectDao.findById(id);
        projectDao.remove(projectToBeRemoved);
    }

    public void changeName(int id, String newName){
        ProjectModel projectToBeRenamed = projectDao.findById(id);
        projectToBeRenamed.setName(newName);
        projectDao.updateProject(projectToBeRenamed);
    }

    public void changeDeadline(int id, Date newDeadline){
        ProjectModel projectToBeChanged = projectDao.findById(id);
        projectToBeChanged.setDeadline(newDeadline);
        projectDao.updateProject(projectToBeChanged);
    }

    public ProjectModel findProjectById(int id){
        ProjectModel projectModel = projectDao.findById(id);
        return projectModel;
    }

  public void updateProject(ProjectModel projectModel){
        projectDao.updateProject(projectModel);
  }

//    public List<ProjectModel> getAllByUser(int id) {
//        List<ProjectModel> projectModelList = projectDao.getAllByUser() ;
//        return  projectModelList;
//    }
}
