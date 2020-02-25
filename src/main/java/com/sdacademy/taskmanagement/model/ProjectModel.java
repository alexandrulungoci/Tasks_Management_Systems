package com.sdacademy.taskmanagement.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Projects")
public class ProjectModel extends Model{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private Date deadline;
    private String status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectModel")
    List<TaskModel> taskModelList = new ArrayList<>();




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<TaskModel> getTaskModelList() {
        return taskModelList;
    }

    public void setTaskModelList(List<TaskModel> taskModelList) {
        this.taskModelList = taskModelList;
    }

    public int getId() {
        return id;
    }
}


