/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weathercompany.weatherpackage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author salma
 */
public class HomeController implements Initializable {
 
    
    public HomeController() {
//        System.out.println("Welcome");
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    @FXML
    void getWeatherData(ActionEvent event) throws MalformedURLException {   //action on button
        if (event.getSource() instanceof Button) {    
            Button clickedButton = (Button) event.getSource();
            String governorate = clickedButton.getText();    //get text on button -> governorate
            
            WeatherThread weatherThread = new WeatherThread(governorate);
            weatherThread.start();
                
        }
        
    }
    
    private void openWeatherDetailsForm(String governorate) {
        try {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/com/weathercompany/weatherpackage/weatherDetails.fxml"));
            Parent parent = Loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            
            WeatherDetailsController controller = Loader.getController();
            controller.initialize(governorate);
            
            stage.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    private class WeatherThread extends Thread {
        private String governorate;

        public WeatherThread(String governorate) {
            this.governorate = governorate;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        openWeatherDetailsForm(governorate);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
