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

public class UserDao implements DaoInterface<UserModel> {

    private SessionFactory sessionFactory;

    public UserDao(){

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
    public void add(UserModel userModel) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(userModel);
        transaction.commit();
    }

    @Override
    public List<UserModel> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from UserModel", UserModel.class);
        transaction.commit();
        return query.getResultList();
    }

    @Override
    public void remove(UserModel userModel) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(userModel);
        transaction.commit();
    }

    public UserModel findUserById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserModel userModel = session.find(UserModel.class, id);
        transaction.commit();
        return userModel;
    }
}
