package com.sdacademy.taskmanagement.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Tasks")
public class TaskModel extends Model{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_Project")
   private ProjectModel projectModel;

    @OneToMany(mappedBy = "taskModel")
    List<SubTaskModel> subTaskModelList = new ArrayList<>();




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectModel getProjectModel() {
        return projectModel;
    }

    public void setProjectModel(ProjectModel projectModel) {
        this.projectModel = projectModel;
    }

    public List<SubTaskModel> getSubTaskModelList() {
        return subTaskModelList;
    }

    public void setSubTaskModelList(List<SubTaskModel> subTaskModelList) {
        this.subTaskModelList = subTaskModelList;
    }

    public int getId() {
        return id;
    }
}
