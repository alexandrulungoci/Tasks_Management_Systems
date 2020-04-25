package com.sdacademy.taskmanagement.dao;

import com.sdacademy.taskmanagement.model.Model;

public interface DaoInterface<T extends Model> {



    void remove(T objectToBeRemoved);

    void add(T objectToBeAdded);


}
