package com.sdacademy.taskmanagement.UI;

import com.sdacademy.taskmanagement.dao.SubTaskDao;
import com.sdacademy.taskmanagement.dao.TaskDao;
import com.sdacademy.taskmanagement.model.SubTaskModel;
import com.sdacademy.taskmanagement.model.TaskModel;
import com.sdacademy.taskmanagement.model.UserModel;
import com.sdacademy.taskmanagement.services.SubTaskService;
import com.sdacademy.taskmanagement.services.TaskService;
import com.sdacademy.taskmanagement.services.UsersService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SubTaskUI {

    Scanner scanner = new Scanner(System.in);
    TaskService taskService = new TaskService();
    UsersService usersService = new UsersService();
    SubTaskDao subTaskDao = new SubTaskDao();
    SubTaskService subTaskService = new SubTaskService();

    TaskDao taskDao = new TaskDao();

    public void subTaskMenu() throws ParseException {
        int option = 0;
        while (option != 9) {
            printSubTaskMenu();
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                addSubTask();
            } else if (option == 2) {
                assignUserToSubTask();
            } else if (option == 3) {
                printSubTasksByUser();
            } else if (option == 4) {
                changeDeadLine();

            } else if (option == 5) {
                chooseStatus();
            } else if (option == 6) {

            }
        }
    }

    public void printSubTaskMenu() {
        System.out.println("SubTask Menu");
        System.out.println("1. Add subTask");
        System.out.println("2. Assign user to subTask");
        System.out.println("3. Print subTasks by User");
        System.out.println("4. Change deadline");
        System.out.println("5. Choose status");

        System.out.println("9. Exit");


    }

    public void addSubTask() throws ParseException {
        SubTaskModel subTaskModel = new SubTaskModel();
        List<TaskModel> taskServiceList = taskService.getTasks();
        taskServiceList.forEach(p -> {
            System.out.println("(id) " + p.getId() + " (Task Name) " + p.getName()
            +" (Project) "+ p.getProjectModel().getName());
        });

        System.out.println("Select task");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter subTask name");
        String name = scanner.nextLine();
        subTaskModel.setName(name);
        System.out.println("Enter deadline  dd/MM/yyyy");
        String date = scanner.next();
        Date dateD = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        subTaskModel.setDeadline(dateD);

        TaskModel taskModel = taskDao.findById(id);
        List<SubTaskModel> subTaskModelList = taskModel.getSubTaskModelList();
        subTaskModelList.add(subTaskModel);
        taskModel.setSubTaskModelList(subTaskModelList);
        subTaskModel.setTaskModel(taskModel);
        subTaskDao.updateSubTask(subTaskModel);
    }

    public void assignUserToSubTask() {
        List<SubTaskModel> subTaskModelList = subTaskService.getAllSubTasks();
        subTaskModelList.forEach(s -> {
            System.out.println(" (id) " + s.getId() + " (name) " + s.getName());
        });
        System.out.println("Enter subTask id");
        int id = scanner.nextInt();
        scanner.nextLine();
        SubTaskModel subTaskModel = subTaskService.findSubTaskById(id);
        List<UserModel> userModelList = usersService.getUsers();
        System.out.println("List of users:");
        userModelList.forEach(u -> {
            System.out.println(u.getId() + " " + u.getFirstName());
        });
        System.out.println("Select user");
        int idUser = scanner.nextInt();
        scanner.nextLine();
        UserModel userModel = usersService.findUserById(idUser);
        subTaskModel.setUserModel(userModel);
        subTaskService.updateSubTask(subTaskModel);
    }

    public void changeDeadLine() throws ParseException {

        List<SubTaskModel> subTaskModelList = subTaskDao.getAll();
        subTaskModelList.forEach(st -> {
            System.out.println(st.getId() + " (subTask name) " + st.getName());
        });
        System.out.println("Select id of the subTask for deadline change");
        System.out.println();
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new deadline");
        String date = scanner.nextLine();
        Date dateD = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        subTaskService.changeDeadline(id, dateD);
    }

    public List<SubTaskModel> findSubTaskByUser() {
        List<UserModel> userModelList = usersService.getUsers();
        userModelList.forEach(u -> {
            System.out.println(u.getId() + " " + u.getFirstName()+" "+u.getLastName());
        });
        System.out.println("Select user id");
        int idUser = scanner.nextInt();
        scanner.nextLine();
        List<SubTaskModel> subTaskModelList = subTaskService.getSubTaskByUser(idUser);
        return subTaskModelList;
    }

    private void printSubTasksByUser() {
        List<SubTaskModel> subTaskModelList = findSubTaskByUser();
        subTaskModelList.forEach(s -> {
            System.out.println("(subTask name) " + s.getName() + " (User name) " + s.getUserModel().getFirstName());
        });
    }

    public void chooseStatus() {
        System.out.println("Choose status:");
        System.out.println("1 for IN PROGRESS");
        System.out.println("2 for EXCEEDED");
        System.out.println("3 for COMPLETED");
        int option = scanner.nextInt();
        scanner.nextLine();
        String status;
        if (option == 1) {
            status = "in progress";
        } else if (option == 2) {
            status = "exceeded";
        } else
            status = "completed";
        List<SubTaskModel> subTaskModelList = subTaskService.getAllSubTasks().stream()
                .filter(s -> s.getStatus().equals(status))
                .collect(Collectors.toList());
        subTaskModelList.forEach(sT -> {
            System.out.println("(id) " + sT.getId() + " (Name) " + sT.getName() + " (Dead Line) "
                    + sT.getDeadline());
        });

    }


}
