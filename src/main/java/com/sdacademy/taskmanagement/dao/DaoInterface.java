package com.sdacademy.taskmanagement.dao;

import com.sdacademy.taskmanagement.model.Model;

public interface DaoInterface<T extends Model> {

    void add(T objectToBeAdded);

    void update(T objectToBeUpdated);

    void remove(T objectToBeRemoved);

}
