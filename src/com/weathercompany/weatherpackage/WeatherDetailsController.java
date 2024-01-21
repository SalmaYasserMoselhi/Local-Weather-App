/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weathercompany.weatherpackage;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author salma
 */
public class WeatherDetailsController implements Initializable {
    
    private final String weatherAPI = "http://api.weatherapi.com/v1/current.json?key=6774f7dcc289422fbbd81610232512&aqi=no&q=";
    String governorate;
    
    @FXML
    private Text weatherText;
    
    @FXML
    private Text temprature;

    @FXML
    private Text real_feel;

    @FXML
    private TextField cityInput;

    @FXML
    private Text humidity;

    @FXML
    private Text wind_speed;

    @FXML
    private Text pressure;

    @FXML
    private Text wind_dir;
    
    @FXML
    private Text country;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    @FXML
    void getWeatherDataFromSearch(ActionEvent event) throws MalformedURLException {
        JSONObject todaysWeather = GetTodaysWeatherInformationUsingSearch();

        System.out.println(todaysWeather);
        
        temprature.setText(
            "Current temperature: " + todaysWeather.get("temp_c") 
        );
        humidity.setText(
            "Humidity: " + todaysWeather.get("humidity") 
        );
        real_feel.setText(
            "Real Feel: " + todaysWeather.get("feelslike_c")
        );
        
        pressure.setText(
            "Pressure: " + todaysWeather.get("pressure_mb")
        );
        wind_dir.setText(
            "Wind Direction: " + todaysWeather.get("wind_dir")
        );
        wind_speed.setText(
            "Wind Speed: " + todaysWeather.get("wind_kph")
        );
        country.setText(
            "Governate: " + cityInput.getText()
        );
        
        
    }
    

    
    
    void initialize(String governorate) throws MalformedURLException, UnsupportedEncodingException {
        this.governorate = governorate;
        JSONObject todaysWeather = GetTodaysWeatherInformation();
         
        System.out.println(todaysWeather);
        
        temprature.setText(
            "Current temperature: " + todaysWeather.get("temp_c") 
        );
        humidity.setText(
            "Humidity: " + todaysWeather.get("humidity") 
        );
        real_feel.setText(
            "Real Feel: " + todaysWeather.get("feelslike_c")
        );
        
        pressure.setText(
            "Pressure: " + todaysWeather.get("pressure_mb")
        );
        wind_dir.setText(
            "Wind Direction: " + todaysWeather.get("wind_dir")
        );
        wind_speed.setText(
            "Wind Speed: " + todaysWeather.get("wind_kph")
        );
        country.setText(
            "Governorate: " + governorate
        );
        
    }
    
    
    
    public JSONObject GetTodaysWeatherInformation() throws MalformedURLException {
        APIConnector apiConnectorWeather = new APIConnector(weatherAPI);   //call api using api url

        JSONObject weatherJSONObject = apiConnectorWeather.getJSONObject(governorate);   //call getJSONObjext function returns JSON Object
        System.out.println("data: "+weatherJSONObject.toJSONString());

        JSONObject weatherArray = (JSONObject) weatherJSONObject.get("current");

        return  weatherArray;
    }

    private JSONObject GetTodaysWeatherInformationUsingSearch() throws MalformedURLException {
        APIConnector apiConnectorWeather = new APIConnector(weatherAPI);   //call api using api url

        JSONObject weatherJSONObject = apiConnectorWeather.getJSONObject(cityInput.getText());   //call getJSONObjext function returns JSON Object
        System.out.println("data: "+weatherJSONObject.toJSONString());

        JSONObject weatherArray = (JSONObject) weatherJSONObject.get("current");

        return  weatherArray;
    }
}
