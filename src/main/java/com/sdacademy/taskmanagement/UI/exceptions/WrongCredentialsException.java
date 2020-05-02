package com.sdacademy.taskmanagement.UI.exceptions;

public class WrongCredentialsException extends Exception{

public WrongCredentialsException(){
    super("Wrong username or password");
}
}
