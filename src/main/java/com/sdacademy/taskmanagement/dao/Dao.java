package com.sdacademy.taskmanagement.dao;

import com.sdacademy.taskmanagement.model.Projects;
import com.sdacademy.taskmanagement.model.SubTasks;
import com.sdacademy.taskmanagement.model.Tasks;
import com.sdacademy.taskmanagement.model.Users;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

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
        configuration.addAnnotatedClass(Projects.class);
        configuration.addAnnotatedClass(Tasks.class);
        configuration.addAnnotatedClass(SubTasks.class);

        sessionFactory = configuration.buildSessionFactory();

    }


}
