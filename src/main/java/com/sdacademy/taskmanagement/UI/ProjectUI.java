package com.sdacademy.taskmanagement.UI;

import com.sdacademy.taskmanagement.dao.Dao;
import com.sdacademy.taskmanagement.model.ProjectModel;
import com.sdacademy.taskmanagement.services.ProjectService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ProjectUI {

    Scanner scanner = new Scanner(System.in);
    ProjectService projectService = new ProjectService();
    Dao dao = new Dao();



    public void projectsMenu() throws ParseException {
        int option = 0;
        while (option != 9) {
            printMenu();
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                addProject();
            } else if (option == 2) {
                printProjects();
            } else if (option == 3) {
                updateProject();
            } else if (option == 4) {
                deleteProject();
            }else if (option == 5) {
                printProjectsInProgress();
            } else if (option == 6) {
                printProjectsExceeded();
            } else if (option == 7) {
                printFiveDaysToDeadline();
            }
        }
    }

    public void printMenu() {
        System.out.println("ProjectModel Menu");
        System.out.println("1. Add project");
        System.out.println("2. Print projects");
        System.out.println("3. Update project");
        System.out.println("4. Delete project");
        System.out.println("5. Projects in progress");
        System.out.println("6. Deadline exceeded");
        System.out.println("7. Five days to deadline");

    }

    public void addProject() throws ParseException {
        ProjectModel project = new ProjectModel();
        System.out.println("Enter project name");
        String pName = scanner.nextLine();
        project.setName(pName);
        System.out.println("Enter deadline  dd/MM/yyyy");
        String date = scanner.next();
        Date dateD = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        project.setDeadline(dateD);
        projectService.addProject(project);
    }

    public void printProjects() {
        List<ProjectModel> projectModelList = dao.getProjects();
        projectModelList.forEach(p -> {
            System.out.println("(id) " + p.getId() + " (Name) " + p.getName() + " (Dead Line) "
                    + p.getDeadline());
        });
    }

    public void updateProject() throws ParseException {
        int option = 0;
        while (option != 9) {
            printUpdateMenu();
            option = scanner.nextInt();
            if (option == 1) {
               changeName();
            } else if (option == 2) {
                changeDeadLine();
            }
        }
    }

    public void printUpdateMenu() {
        System.out.println("1. Change project's name");
        System.out.println("2. Change project's deadline");
        System.out.println("9. Exit");
    }

    public void changeName(){
        System.out.println("Select id of the project you want to rename");
        printProjects();
        System.out.println();
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new name");
        String newName = scanner.nextLine();
        projectService.changeName(id, newName);
    }

    public void changeDeadLine() throws ParseException {
        System.out.println("Select id of the project for deadline change");
        printProjects();
        System.out.println();
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new deadline");
        String date = scanner.nextLine();
        Date dateD = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        projectService.changeDeadline(id, dateD);
    }


    public void deleteProject() {
        System.out.println("Select project id to delete");
        printProjects();
        System.out.println();
        int id = scanner.nextInt();
        scanner.nextLine();
        projectService.deleteProject(id);
    }

    public void printProjectsInProgress(){
        List<ProjectModel> projectModelList = dao.getProjectsInProgress();
        projectModelList.forEach(p -> {
            System.out.println("(id) " + p.getId() + " (Name) " + p.getName() + " (Dead Line) "
                    + p.getDeadline());
        });
    }

    public void printProjectsExceeded(){
        List<ProjectModel> projectModelList = dao.getProjectsExceeded();
        projectModelList.forEach(p -> {
            System.out.println("(id) " + p.getId() + " (Name) " + p.getName() + " (Dead Line) "
                    + p.getDeadline());
        });
    }

    public void printFiveDaysToDeadline(){
        List<ProjectModel> projectModelList = dao.getFiveDaysToDeadline();
        projectModelList.forEach(p -> {
            System.out.println("(id) " + p.getId() + " (Name) " + p.getName() + " (Dead Line) "
                    + p.getDeadline());
        });
    }

}
