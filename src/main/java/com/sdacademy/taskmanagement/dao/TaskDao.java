package com.sdacademy.taskmanagement.dao;

import com.sdacademy.taskmanagement.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Properties;

public class TaskDao implements DaoInterface<TaskModel>{

    private SessionFactory sessionFactory;

    public TaskDao(){

        Configuration configuration = new Configuration();
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/task_management_generic");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "MySQL2020#$");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(UserModel.class);
        configuration.addAnnotatedClass(ProjectModel.class);
        configuration.addAnnotatedClass(TaskModel.class);
        configuration.addAnnotatedClass(SubTaskModel.class);
        configuration.addAnnotatedClass(Model.class);

        sessionFactory = configuration.buildSessionFactory();


    }

    @Override
    public void add (TaskModel TaskModel) {
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

    public TaskModel findTaskById(int id) {
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
                "\t\tinner join task_management_generic.users on subtasks.id_User=users.id where user.id = "+id+" ", ProjectModel.class);
        transaction.commit();
        return query.getResultList();
    }


}
