package com.example.journalApp.services;

import com.example.journalApp.api_response.WeatherAPIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;

    private static final String api = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    RestTemplate restTemplate;

    public WeatherAPIResponse getWeather(String city){
        String finalApi = api.replace("API_KEY", apiKey).replace("CITY", city);
        ResponseEntity<WeatherAPIResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherAPIResponse.class);
        WeatherAPIResponse body = response.getBody();
        return body;
    }
}
