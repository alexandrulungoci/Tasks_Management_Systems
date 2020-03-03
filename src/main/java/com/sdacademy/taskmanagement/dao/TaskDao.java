package com.sdacademy.taskmanagement.dao;

import com.sdacademy.taskmanagement.model.ProjectModel;
import com.sdacademy.taskmanagement.model.TaskModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class TaskDao implements DaoInterface<TaskModel> {

    private SessionFactory sessionFactory;

    public TaskDao() {
        ConfigurationClass configurationClass = new ConfigurationClass();
        Configuration configuration = configurationClass.getConfiguration();
        sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    public void add(TaskModel TaskModel) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(TaskModel);
        transaction.commit();
    }

    @Override
    public List<TaskModel> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from TaskModel", TaskModel.class);
        transaction.commit();
        return query.getResultList();
    }

    @Override
    public void remove(TaskModel taskModel) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(taskModel);
        transaction.commit();
    }

    public TaskModel findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        TaskModel taskModel = session.find(TaskModel.class, id);
        transaction.commit();
        return taskModel;
    }

    public List<TaskModel> getAllByUser(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM task_management_generic.tasks \n" +
                "inner join task_management_generic.tasks on tasks.id_Project=projects.id\n" +
                "\tinner join task_management_generic.subtasks on subtasks.id_Task=tasks.id\n" +
                "\t\tinner join task_management_generic.users on subtasks.id_User=users.id where user.id = " + id + " ", ProjectModel.class);
        transaction.commit();
        return query.getResultList();
    }

    public void updateTask(TaskModel task) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(task);
        transaction.commit();
    }

}
