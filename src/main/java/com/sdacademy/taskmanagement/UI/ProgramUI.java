package com.sdacademy.taskmanagement.UI;

import com.sdacademy.taskmanagement.dao.SubTaskDao;
import com.sdacademy.taskmanagement.model.SubTaskModel;
import com.sdacademy.taskmanagement.services.SubTaskService;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ProgramUI {

    Scanner scanner = new Scanner(System.in);
    ProjectUI projectUI = new ProjectUI();
    TaskUI taskUI = new TaskUI();
    SubTaskUI subTaskUI = new SubTaskUI();
    UserUI userUI = new UserUI();
    SubTaskService subTaskService = new SubTaskService();
    SubTaskDao subTaskDao = new SubTaskDao();


    public void setStatus() {
        List<SubTaskModel> subTaskModelList = subTaskService.getAllSubTasks();
        Date date = new Date();
        subTaskModelList.forEach(sT -> {
            if (sT.getDeadline().before(date) && !sT.getStatus().equals("completed")) {
                sT.setStatus("exceeded");
                subTaskService.updateSubtask(sT);
            } else if (sT.getDeadline().after(date) && !sT.getStatus().equals("completed")) {
                sT.setStatus("in progress");
                subTaskService.updateSubtask(sT);
            }
        });
    }


    public void programMenu() throws ParseException {
        int option = 0;
        while (option != 9) {
            printProgramMenu();
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                projectUI.projectsMenu();
            } else if (option == 2) {
                taskUI.taskMenu();
            } else if (option == 3) {
                subTaskUI.subTaskMenu();
            } else if (option == 4) {
                userUI.userMenu();
            }
        }
    }

    public void printProgramMenu() {
        System.out.println("Program Menu");
        System.out.println("1. Project menu");
        System.out.println("2. Task menu");
        System.out.println("3. SubTask menu");
        System.out.println("4. User menu");

    }
}
