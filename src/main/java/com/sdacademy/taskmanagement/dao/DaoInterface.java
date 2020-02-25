package com.sdacademy.taskmanagement.dao;

import com.sdacademy.taskmanagement.model.Model;
import com.sdacademy.taskmanagement.model.ProjectModel;

import java.util.List;
import java.util.Optional;

public interface DaoInterface <T extends Model> {

    List<T> getAll();

    void remove(T objectToBeRemoved);

    void add(T objectToBeAdded);



}
