package com.sdacademy.taskmanagement.dao;

import com.sdacademy.taskmanagement.model.UserModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao implements DaoInterface<UserModel> {

    private SessionFactory sessionFactory;

    public UserDao() {
        ConfigurationClass configurationClass = new ConfigurationClass();
        Configuration configuration = configurationClass.getConfiguration();
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

    public UserModel findByUserName(String userName){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from UserModel where userName = '"+userName+"'", UserModel.class);
        List<UserModel> userModelList = query.getResultList();
        return userModelList.get(0);
    }
}
