package com.sdacademy.taskmanagement.UI;

import com.sdacademy.taskmanagement.dao.SubTaskDao;
import com.sdacademy.taskmanagement.model.SubTaskModel;
import com.sdacademy.taskmanagement.model.UserModel;
import com.sdacademy.taskmanagement.services.SubTaskService;
import com.sdacademy.taskmanagement.services.UsersService;

import java.util.List;
import java.util.Scanner;

public class UserUI {

    Scanner scanner = new Scanner(System.in);
    UsersService usersService = new UsersService();
    private UserModel loggedinUser;
    SubTaskService subTaskService = new SubTaskService();
    SubTaskDao subTaskDao = new SubTaskDao();

    public void userMenu() {
        int option = 0;
        while (option != 9) {
            printUserMenu();
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                registerUser();
            } else if (option == 2) {
                login();
            } else if (option == 3) {
                autoAssignSubTask();
            } else if (option == 4) {
                changeSubTaskStatus();
            } else if (option == 5) {
                changeUsername();
            } else if (option == 6) {
                printUsers();
            } else if (option == 7) {
                removeUser();
            }
        }
    }


    public void printUserMenu() {
        System.out.println("User Menu");
        System.out.println("1. Register user");
        System.out.println("2. Login");
        System.out.println("3. Auto assign subTask");
        System.out.println("4. Change subTask status");
        System.out.println("5. Change username");
        System.out.println("6. Print users");
        System.out.println("7. Remove user");
        System.out.println();
        System.out.println("9. Exit");

    }

    public void registerUser() {
        UserModel userModel = new UserModel();
        System.out.println("Enter first name");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name");
        String lastName = scanner.nextLine();
        System.out.println("Enter User Name");
        String userName = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setUserName(userName);
        userModel.setPassword(password);
        usersService.addUser(userModel);
    }

    public UserModel login() {
        System.out.println("Enter User Name");
        String userName = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        loggedinUser = usersService.findUserByUserName(userName);
        if (
                loggedinUser.getPassword().equals(password)) {
            System.out.println("Login successfully");
            return loggedinUser;
        } else
            System.out.println("error");

        printUserMenu();
        return null;
    }

    public void autoAssignSubTask() {
        System.out.println("You ere loggedin in as " + loggedinUser.getUserName());
        List<SubTaskModel> subTaskModels = subTaskService.getSubTaskWithoutUser();
        System.out.println("SubTasks without user:");
        subTaskModels.forEach(s -> {
            System.out.println("(id) " + s.getId() + " (SubTask name) " + s.getName()
                    + " (Task) " + s.getTaskModel().getName()
                    + " (Project) " + s.getTaskModel().getProjectModel().getName());
        });
        System.out.println("Select subTask id to assign");
        int idSubtask = scanner.nextInt();
        scanner.nextLine();
        SubTaskModel subTaskById = subTaskService.findSubTaskById(idSubtask);
        subTaskById.setUserModel(loggedinUser);
        subTaskService.updateSubtask(subTaskById);

    }

    public void changeSubTaskStatus() {
        List<SubTaskModel> subTaskModelsByUser = subTaskService.getSubTaskByUser(loggedinUser.getId());
        subTaskModelsByUser.forEach(s -> {
            System.out.println("(id) " + s.getId() + " (SubTask name) " + s.getName()
                    + " (Task) " + s.getTaskModel().getName()
                    + " (Project) " + s.getTaskModel().getProjectModel().getName());
        });
        System.out.println("Select subTask id to change status in 'completed'");
        int id = scanner.nextInt();
        scanner.nextLine();
        SubTaskModel subTaskModel = subTaskService.findSubTaskById(id);
        subTaskModel.setStatus("completed");
        System.out.println("Status changed");
        subTaskService.updateSubtask(subTaskModel);
    }

    public void changeUsername() {
        if (loggedinUser == null) {
            System.out.println("Please login");
            login();
        }
        System.out.println("You ere loggedin in as " + loggedinUser.getUserName());
        System.out.println("Enter new username");
        String newUsername = scanner.nextLine();
        loggedinUser.setUserName(newUsername);
        usersService.updateUsername(loggedinUser);

    }

    public void printUsers() {
        List<UserModel> allUsers = usersService.getUsers();
        allUsers.forEach(u -> {
            System.out.println("(Id) " + u.getId() + " (Name) " + u.getFirstName() + " " + u.getLastName()
                    + " (username) " + u.getUserName());
        });
    }

    public void removeUser() {
        printUsers();
        System.out.println("Select user id to remove");
        int id = scanner.nextInt();
        scanner.nextLine();
        UserModel userToRemove = usersService.findUserById(id);
        usersService.removeUser(userToRemove);
    }

}
