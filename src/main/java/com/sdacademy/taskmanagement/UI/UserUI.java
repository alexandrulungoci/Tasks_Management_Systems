package com.sdacademy.taskmanagement.UI;

import com.sdacademy.taskmanagement.model.UserModel;
import com.sdacademy.taskmanagement.services.UsersService;

import java.util.Scanner;

public class UserUI {

    Scanner scanner = new Scanner(System.in);
    UsersService usersService = new UsersService();

    public void userMenu() {
        int option = 0;
        while (option != 9) {
            printUserMenu();
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                addUser();
            } else if (option == 2) {

            } else if (option == 3) {

            } else if (option == 4) {

            } else if (option == 5) {

            } else if (option == 6) {

            } else if (option == 7) {

            }
        }
    }

    public void printUserMenu() {
        System.out.println("User Menu");
        System.out.println("1. Add user");
        System.out.println("2. ");
        System.out.println("9. Exit");


    }

    public void addUser(){
        UserModel userModel = new UserModel();
//        System.out.println("Enter name");
//        String name = scanner.nextLine();
        System.out.println("Enter firstName");
        String firstName = scanner.nextLine();
        userModel.setFirstName(firstName);
        usersService.addUser(userModel);

    }

}
