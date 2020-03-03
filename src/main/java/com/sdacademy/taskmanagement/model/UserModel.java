package com.sdacademy.taskmanagement.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
public class UserModel extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String lastName;
    private String firstName;
    private String userName;
    private String password;

    @OneToMany(mappedBy = "userModel")
    List<SubTaskModel> subTaskModelList = new ArrayList<>();

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
