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
import java.util.stream.Collectors;

public class SubTaskDao implements DaoInterface<SubTaskModel> {

    private SessionFactory sessionFactory;

    public SubTaskDao(){

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
    public void add (SubTaskModel subTaskModel) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(subTaskModel);
        transaction.commit();
    }


    @Override
    public List<SubTaskModel> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from SubTaskModel", SubTaskModel.class);
        transaction.commit();
        return query.getResultList();
    }

    @Override
    public void remove(SubTaskModel subTaskModel) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(subTaskModel);
        transaction.commit();
    }

    public void updateSubTask(SubTaskModel subTaskModel) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(subTaskModel);
        transaction.commit();
    }

    public List<SubTaskModel> getSubTaskByUser(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<SubTaskModel> subTaskByUser = getAll().stream()
                .filter(a -> a.getUserModel().getId() == id)
                .collect(Collectors.toList());
        transaction.commit();
        return subTaskByUser;
    }

}
