package com.sdacademy.taskmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainFxml extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = buildMainScene();
        stage.setTitle("Main menu");
        stage.setScene(scene);
        stage.show();

    }

    private  Scene buildMainScene() throws IOException {
        URL fxml = getClass().getClassLoader().getResource("main.fxml");
        Parent root = FXMLLoader.load(fxml);
        Scene scene = new Scene(root);
        return scene;

    }

 }
