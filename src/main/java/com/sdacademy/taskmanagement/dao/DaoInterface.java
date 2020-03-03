package com.sdacademy.taskmanagement.dao;

import com.sdacademy.taskmanagement.model.Model;

import java.util.List;

public interface DaoInterface<T extends Model> {

    List<T> getAll();

    void remove(T objectToBeRemoved);

    void add(T objectToBeAdded);


}
