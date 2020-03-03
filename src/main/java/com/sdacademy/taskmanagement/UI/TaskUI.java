package com.sdacademy.taskmanagement.UI;

import com.sdacademy.taskmanagement.model.ProjectModel;
import com.sdacademy.taskmanagement.model.SubTaskModel;
import com.sdacademy.taskmanagement.model.TaskModel;
import com.sdacademy.taskmanagement.services.ProjectService;
import com.sdacademy.taskmanagement.services.SubTaskService;
import com.sdacademy.taskmanagement.services.TaskService;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class TaskUI {

    Scanner scanner = new Scanner(System.in);
    ProjectUI projectUI = new ProjectUI();
    ProjectService projectService = new ProjectService();
    TaskService taskService = new TaskService();
    SubTaskUI subTaskUI = new SubTaskUI();
    SubTaskService subTaskService = new SubTaskService();

    public void taskMenu() {
        int option = 0;
        while (option != 9) {
            printTaskMenu();
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                addTask();
            } else if (option == 2) {
                findTasksByUser();
            } else if (option == 3) {
                deleteTask();
            } else if (option == 4) {
                printTasks();
            } else if (option == 5) {

            } else if (option == 6) {

            } else if (option == 7) {

            }
        }
    }

    public void printTaskMenu() {
        System.out.println("Task Menu");
        System.out.println("1. Add task");
        System.out.println("2. Find task by user");
        System.out.println("3. Delete task");
        System.out.println("4. Print tasks");
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

    public void findTasksByUser() {
        List<SubTaskModel> subTaskModelList = subTaskUI.findSubTaskByUser();

        System.out.println("Tasks by user");
        Set<String> tasks = new HashSet<>();
        subTaskModelList.forEach(s -> {
            tasks.add(s.getTaskModel().getName());
        });
        tasks.forEach(t -> {
            System.out.println(t);
        });

    }

    public void deleteTask() {
        System.out.println("Select task id to delete");
        printTasks();
        System.out.println();
        int id = scanner.nextInt();
        scanner.nextLine();
        taskService.deleteTask(id);
    }

    public void printTasks() {
        List<SubTaskModel> subTaskModelList = subTaskService.getAllSubTasks();
        subTaskModelList.forEach(p -> {
            System.out.println("(id) " + p.getTaskModel().getId()
                    + "     (Task) " + p.getTaskModel().getName()
                    + "     (Project) " + p.getTaskModel().getProjectModel().getName()
                    + "     (User) " + p.getUserModel().getFirstName() + " " + p.getUserModel().getLastName());
        });
    }

}



