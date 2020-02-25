package com.sdacademy.taskmanagement.UI;

import com.sdacademy.taskmanagement.dao.SubTaskDao;
import com.sdacademy.taskmanagement.dao.TaskDao;
import com.sdacademy.taskmanagement.model.SubTaskModel;
import com.sdacademy.taskmanagement.model.TaskModel;
import com.sdacademy.taskmanagement.model.UserModel;
import com.sdacademy.taskmanagement.services.ProjectService;
import com.sdacademy.taskmanagement.services.TaskService;
import com.sdacademy.taskmanagement.services.UsersService;

import java.util.List;
import java.util.Scanner;

public class SubTaskUI {

    Scanner scanner = new Scanner(System.in);
    TaskUI taskUI = new TaskUI();
    TaskService taskService = new TaskService();
    ProjectService projectService = new ProjectService();
    UserUI userUI = new UserUI();
    UsersService usersService = new UsersService();
    SubTaskDao subTaskDao = new SubTaskDao();

    TaskDao taskDao = new TaskDao();

    public void subTaskMenu()  {
        int option = 0;
        while (option != 9) {
            printSubTaskMenu();
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                addSubTask();
            } else if (option == 2) {
                findSubTaskByUser();
            } else if (option == 3) {

            } else if (option == 4) {

            }else if (option == 5) {

            } else if (option == 6) {

            } else if (option == 7) {

            }
        }
    }

    public void printSubTaskMenu() {
        System.out.println("SubTask Menu");
        System.out.println("1. Add subTask");
        System.out.println("2. Find subTasks by User");
        System.out.println("9. Exit");


    }

    public void addSubTask(){
        SubTaskModel subTaskModel = new SubTaskModel();
        List<TaskModel> taskServiceList = taskService.getTasks();
        taskServiceList.forEach(p -> {
            System.out.println("(id) " + p.getId() + " (Name) "+p.getName());
        });

        System.out.println("Select task");
        int id = scanner.nextInt();
        scanner.nextLine();
        List<UserModel> userModelList = usersService.getUsers();
        userModelList.forEach(u->{
            System.out.println(u.getId()+" "+u.getFirstName());
        });
        System.out.println("Select user");
        int idUser = scanner.nextInt();
        scanner.nextLine();
        UserModel userModel = usersService.findUserById(idUser);
        System.out.println("Enter subTask name");
        String name = scanner.nextLine();
        subTaskModel.setName(name);
        subTaskModel.setUserModel(userModel);
        TaskModel taskModel = taskDao.findTaskById(id);
        List<SubTaskModel> subTaskModelList = taskModel.getSubTaskModelList();
        subTaskModelList.add(subTaskModel);
        taskModel.setSubTaskModelList(subTaskModelList);
        subTaskModel.setTaskModel(taskModel);
       subTaskDao.updateSubTask(subTaskModel);
    }

    public void findSubTaskByUser(){
        List<UserModel> userModelList = usersService.getUsers();
        userModelList.forEach(u->{
            System.out.println(u.getId()+" "+u.getFirstName());
        });
        System.out.println("Select user");
        int idUser = scanner.nextInt();
        scanner.nextLine();
        UserModel userModel = usersService.findUserById(idUser);
        List<SubTaskModel> subTaskModelList = subTaskDao.getSubTaskByUser(idUser);
        subTaskModelList.forEach(s->{
            System.out.println("(subTask name) " + s.getName() +" (User name) " +userModel.getFirstName());
        });
    }
}
