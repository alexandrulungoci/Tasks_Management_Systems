package com.sdacademy.taskmanagement;

import com.sdacademy.taskmanagement.UI.ProgramUI;

import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws ParseException {

        ProgramUI programUI = new ProgramUI();
        programUI.setStatus();
        programUI.programMenu();

    }


}
