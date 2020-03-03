package com.sdacademy.taskmanagement.UI;

import com.sdacademy.taskmanagement.dao.ProjectDao;
import com.sdacademy.taskmanagement.model.ProjectModel;
import com.sdacademy.taskmanagement.model.SubTaskModel;
import com.sdacademy.taskmanagement.services.ProjectService;
import com.sdacademy.taskmanagement.services.SubTaskService;
import com.sdacademy.taskmanagement.services.UsersService;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ProjectUI {

    Scanner scanner = new Scanner(System.in);
    ProjectService projectService = new ProjectService();
    ProjectDao projectDao = new ProjectDao();
    UsersService usersService = new UsersService();
    SubTaskUI subTaskUI = new SubTaskUI();
    SubTaskService subTaskService = new SubTaskService();


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
            } else if (option == 5) {
                findProjectsByUser();
            }
        }
    }

    public void printMenu() {
        System.out.println("ProjectModel Menu");
        System.out.println("1. Add project");
        System.out.println("2. Print projects");
        System.out.println("3. Update project");
        System.out.println("4. Delete project");
        System.out.println("5. Find project by user");

        System.out.println("9. Exit");

    }

    public void addProject() throws ParseException {
        ProjectModel project = new ProjectModel();
        System.out.println("Enter project name");
        String pName = scanner.nextLine();
        project.setName(pName);
        projectService.addProject(project);
    }

    public void printProjects() {
        List<SubTaskModel> subTaskModelList = subTaskService.getAllSubTasks();
        subTaskModelList.forEach(p -> {
            System.out.println("(id) " + p.getTaskModel().getProjectModel().getId()
                    + "     (Project) " + p.getTaskModel().getProjectModel().getName()
                    + "     (Task) " + p.getTaskModel().getName()
                    + "     (SubTask) " + p.getName()
                    + "     (User) " + p.getUserModel().getFirstName() + " " + p.getUserModel().getLastName());
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

            }
        }
    }

    public void printUpdateMenu() {
        System.out.println("1. Change project's name");

        System.out.println("9. Exit");
    }

    public void changeName() {
        System.out.println("Select id of the project you want to rename");
        printProjects();
        System.out.println();
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new name");
        String newName = scanner.nextLine();
        projectService.changeName(id, newName);
    }


    public void deleteProject() {
        System.out.println("Select project id to delete");
        printProjects();
        System.out.println();
        int id = scanner.nextInt();
        scanner.nextLine();
        projectService.removeProject(id);
    }


    public void findProjectsByUser() {
        List<SubTaskModel> subTaskModelList = subTaskUI.findSubTaskByUser();

        System.out.println("project by user");
        Set<String> project = new HashSet<>();
        subTaskModelList.forEach(s -> {
            project.add(s.getTaskModel().getProjectModel().getName());
        });
        project.forEach(p -> {
            System.out.println(p);
        });

    }

}
