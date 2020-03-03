package com.sdacademy.taskmanagement.dao;

import com.sdacademy.taskmanagement.model.ProjectModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;


public class ProjectDao implements DaoInterface<ProjectModel> {

    private SessionFactory sessionFactory;

    public ProjectDao() {
        ConfigurationClass configurationClass = new ConfigurationClass();
        Configuration configuration = configurationClass.getConfiguration();
        sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    public void add(ProjectModel project) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(project);
        transaction.commit();
    }

    @Override
    public List<ProjectModel> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from ProjectModel", ProjectModel.class);
        transaction.commit();
        return query.getResultList();
    }

    @Override
    public void remove(ProjectModel projectModel) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(projectModel);
        transaction.commit();
    }


    public ProjectModel findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProjectModel projectModel = session.find(ProjectModel.class, id);
        transaction.commit();
        return projectModel;
    }

    public void updateProject(ProjectModel project) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(project);
        transaction.commit();
    }

}
