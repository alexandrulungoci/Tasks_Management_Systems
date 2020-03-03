package com.sdacademy.taskmanagement;


import com.sdacademy.taskmanagement.UI.ProjectUI;
import com.sdacademy.taskmanagement.UI.SubTaskUI;
import com.sdacademy.taskmanagement.UI.TaskUI;
import com.sdacademy.taskmanagement.UI.UserUI;
import javafx.fxml.FXML;


import java.text.ParseException;


public class MainController {

    ProjectUI projectUI = new ProjectUI();
    TaskUI taskUI = new TaskUI();
    SubTaskUI subTaskUI = new SubTaskUI();
    UserUI userUI = new UserUI();

    @FXML
    private void onProjectClick() throws ParseException {
        projectUI.projectsMenu();
    }

    @FXML
    private void onTaskClick() throws ParseException {
        taskUI.taskMenu();
    }

    @FXML
    private void onSubTaskClick() throws ParseException {
        subTaskUI.subTaskMenu();
    }

    @FXML
    private void onUserClick() throws ParseException {
        userUI.userMenu();
    }

}
