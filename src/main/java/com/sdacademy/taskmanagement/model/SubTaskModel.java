package com.sdacademy.taskmanagement.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SubTasks")
public class SubTaskModel extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private Date deadline;
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_Task")
    private TaskModel taskModel;

    @ManyToOne
    @JoinColumn(name = "id_User")
    private UserModel userModel;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskModel getTaskModel() {
        return taskModel;
    }

    public void setTaskModel(TaskModel taskModel) {
        this.taskModel = taskModel;
    }

    public UserModel getUsers() {
        return userModel;
    }

    public void setUsers(UserModel userModel) {
        this.userModel = userModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
