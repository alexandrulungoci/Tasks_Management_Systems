package com.sdacademy.taskmanagement;

import com.sdacademy.taskmanagement.UI.ProgramUI;
import com.sdacademy.taskmanagement.UI.ProjectUI;
import com.sdacademy.taskmanagement.dao.ProjectDao;
import com.sdacademy.taskmanagement.dao.TaskDao;
import com.sdacademy.taskmanagement.model.ProjectModel;

import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws ParseException {


        ProgramUI programUI = new ProgramUI();
        programUI.programMenu();


//        TaskDao taskDao = new TaskDao();
//        taskDao.getAllByUser(1);

    }
}
