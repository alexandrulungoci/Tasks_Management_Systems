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


public class ProjectDao implements DaoInterface<ProjectModel> {

    private SessionFactory sessionFactory;


    public ProjectDao(){

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



    public List<ProjectModel> getProjectsInProgress() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from ProjectModel where deadline > current_date", ProjectModel.class);
        transaction.commit();
        return query.getResultList();
    }

    public List<ProjectModel> getProjectsExceeded() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from ProjectModel where deadline <= current_date", ProjectModel.class);
        transaction.commit();
        return query.getResultList();
    }

    public List<ProjectModel> getFiveDaysToDeadline() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from ProjectModel where (deadline - current_date) between 0 and 5", ProjectModel.class);
        transaction.commit();
        return query.getResultList();
    }

    public List<ProjectModel> getAllByUser(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" FROM ProjectModel proj\n" +
                " join proj.taskModelList tasks join tasks.subTaskModelList subT\n" +
                "\t join subT.userModel user where user.id = "+id+" ", ProjectModel.class);
        transaction.commit();
        return query.getResultList();
    }


}
