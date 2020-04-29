package com.sdacademy.taskmanagement.dao;

import com.sdacademy.taskmanagement.ConfigurationClass;
import com.sdacademy.taskmanagement.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class ModelDao<T extends Model> implements DaoInterface {

    private SessionFactory sessionFactory;

    public ModelDao() {
        ConfigurationClass configurationClass = new ConfigurationClass();
        Configuration configuration = configurationClass.getConfiguration();
        sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    public void add(Model objectToBeAdded) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(objectToBeAdded);
        transaction.commit();
    }

    @Override
    public void update(Model objectToBeUpdated) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(objectToBeUpdated);
        transaction.commit();
    }

    @Override
    public void remove(Model objectToBeRemoved) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(objectToBeRemoved);
        transaction.commit();
    }

// User methods

    public List<UserModel> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from UserModel", UserModel.class);
        transaction.commit();
        return query.getResultList();
    }

    public UserModel findUserById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserModel userModel = session.find(UserModel.class, id);
        transaction.commit();
        return userModel;
    }

    public UserModel findByUserName(String userName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from UserModel where userName = '" + userName + "'", UserModel.class);
        List<UserModel> userModelList = query.getResultList();
        return userModelList.get(0);
    }

    // Project methods

    public List<ProjectModel> getAllProjects() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from ProjectModel", ProjectModel.class);
        transaction.commit();
        return query.getResultList();
    }

    public ProjectModel findProjectById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProjectModel projectModel = session.find(ProjectModel.class, id);
        transaction.commit();
        return projectModel;
    }

    // Task methods

    public List<TaskModel> getAllTasks() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from TaskModel", TaskModel.class);
        transaction.commit();
        return query.getResultList();
    }

    public TaskModel findTaskById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        TaskModel taskModel = session.find(TaskModel.class, id);
        transaction.commit();
        return taskModel;
    }

    public List<TaskModel> getAllTasksByUser(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM task_management_generic.tasks \n" +
                "inner join task_management_generic.tasks on tasks.id_Project=projects.id\n" +
                "\tinner join task_management_generic.subtasks on subtasks.id_Task=tasks.id\n" +
                "\t\tinner join task_management_generic.users on subtasks.id_User=users.id where user.id = " + id + " ", ProjectModel.class);
        transaction.commit();
        return query.getResultList();
    }

    // Subtasks methods

    public List<SubTaskModel> getAllSubtasks() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from SubTaskModel", SubTaskModel.class);
        transaction.commit();
        return query.getResultList();
    }

    public SubTaskModel findSubtaskById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        SubTaskModel subTaskModel = session.find(SubTaskModel.class, id);
        transaction.commit();
        return subTaskModel;
    }

    public List<SubTaskModel> getSubTaskByUser(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<SubTaskModel> subTaskByUser = getAllSubtasks().stream()
                .filter(a -> a.getUserModel().getId() == id)
                .collect(Collectors.toList());
        transaction.commit();
        return subTaskByUser;
    }
}
