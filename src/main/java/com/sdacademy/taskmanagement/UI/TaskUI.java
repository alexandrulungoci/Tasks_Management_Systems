package com.sdacademy.taskmanagement.UI;

import com.sdacademy.taskmanagement.model.ProjectModel;
import com.sdacademy.taskmanagement.model.TaskModel;
import com.sdacademy.taskmanagement.services.ProjectService;
import com.sdacademy.taskmanagement.services.TaskService;

import java.util.List;
import java.util.Scanner;

public class TaskUI {

    Scanner scanner = new Scanner(System.in);
    ProjectUI projectUI = new ProjectUI();
    ProjectService projectService = new ProjectService();
    TaskService taskService = new TaskService();

    public void taskMenu() {
        int option = 0;
        while (option != 9) {
            printTaskMenu();
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                addTask();
            } else if (option == 2) {

            } else if (option == 3) {

            } else if (option == 4) {

            } else if (option == 5) {

            } else if (option == 6) {

            } else if (option == 7) {

            }
        }
    }

    public void printTaskMenu() {
        System.out.println("Task Menu");
        System.out.println("1. Add task");
        System.out.println("2. ");
        System.out.println("9. Exit");


    }

    public void addTask() {
        TaskModel task = new TaskModel();
        projectUI.printProjects();
        System.out.println("Select project");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter task name");
        String name = scanner.nextLine();
        task.setName(name);
        ProjectModel projectModel = projectService.findProjectById(id);
        List<TaskModel> taskModelList = projectModel.getTaskModelList();
        taskModelList.add(task);
        projectModel.setTaskModelList(taskModelList);
        task.setProjectModel(projectModel);
        projectService.updateProject(projectModel);

    }


}



