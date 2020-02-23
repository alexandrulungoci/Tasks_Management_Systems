package com.sdacademy.taskmanagement.dao;

import com.sdacademy.taskmanagement.model.ProjectModel;
import com.sdacademy.taskmanagement.model.SubTasks;
import com.sdacademy.taskmanagement.model.Tasks;
import com.sdacademy.taskmanagement.model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Properties;

public class Dao {

    private SessionFactory sessionFactory;

    public Dao(){

        Configuration configuration = new Configuration();
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/task_management");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "MySQL2020#$");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(Users.class);
        configuration.addAnnotatedClass(ProjectModel.class);
        configuration.addAnnotatedClass(Tasks.class);
        configuration.addAnnotatedClass(SubTasks.class);

        sessionFactory = configuration.buildSessionFactory();

    }

    public void addProject(ProjectModel project) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(project);
        transaction.commit();
    }

    public List<ProjectModel> getProjects() {
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

    public void updateProject(ProjectModel project) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(project);
        transaction.commit();
    }

    public void deleteProject(ProjectModel project) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(project);
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


}
