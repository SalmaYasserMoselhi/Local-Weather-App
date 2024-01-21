/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weathercompany.weatherpackage;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Rana
 */
public class APIConnector {

    private final String urlString;  //api url


    public APIConnector(String urlString) throws MalformedURLException {
        this.urlString = urlString;
    }
    
    public JSONObject getJSONObject(String governorate){   
        governorate = governorate.replaceAll(" ", "+");
        try {
            URL url = new URL(urlString + governorate);   //make url -> (api url + city) to access weather data from api

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } 
            else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();

                JSONParser parser = new JSONParser();

                return (JSONObject) parser.parse(String.valueOf(informationString));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

