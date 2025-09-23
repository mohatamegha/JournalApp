package com.example.journalApp.api_response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@Setter
public class WeatherAPIResponse {
    private Current current;

    @Getter
    @Setter
    public class Current{
        private int temperature;
        private List<String> weatherDescriptions;
        private int precip;
        private int humidity;
        private int feelslike;
    }

}
