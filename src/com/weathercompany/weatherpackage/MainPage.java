/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weathercompany.weatherpackage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author salma
 */
public class MainPage extends Application {
    final String HomePath = "/com/weathercompany/weatherpackage/Home.fxml";
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader HomeLoader = new FXMLLoader(getClass().getResource("/com/weathercompany/weatherpackage/Home.fxml"));
            Parent parent = HomeLoader.load();
            Scene scene = new Scene(parent);
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            primaryStage.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
    