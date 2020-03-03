package com.sdacademy.taskmanagement.dao;

import com.sdacademy.taskmanagement.model.SubTaskModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class SubTaskDao implements DaoInterface<SubTaskModel> {


    private SessionFactory sessionFactory;

    public SubTaskDao() {
        ConfigurationClass configurationClass = new ConfigurationClass();
        Configuration configuration = configurationClass.getConfiguration();
        sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    public void add(SubTaskModel subTaskModel) {
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

    public SubTaskModel findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        SubTaskModel subTaskModel = session.find(SubTaskModel.class, id);
        transaction.commit();
        return subTaskModel;
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
