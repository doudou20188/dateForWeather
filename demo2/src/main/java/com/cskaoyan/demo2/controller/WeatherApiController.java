package com.cskaoyan.demo2.controller;



import com.cskaoyan.demo2.bean.WeatherResponse;
import com.cskaoyan.demo2.service.WeatherApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/weather")
public class WeatherApiController {


    @Autowired
    WeatherApiService weatherApiService;


    @RequestMapping("/cityName/{cityName}")
    public WeatherResponse getWeatherInfoByCityName(@PathVariable("cityName") String cityName){

        WeatherResponse weatherResponse= null;
        try {
            weatherResponse = weatherApiService.getWeatherInfoByCityName(cityName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherResponse ;

    }

    @RequestMapping("/cityId/{cityId}")
    public WeatherResponse getWeatherInfoByCityId(@PathVariable("cityId")  String cityId){

        WeatherResponse weatherResponse=null;
        try {
            weatherResponse = weatherApiService.getWeatherInfoByCityId(cityId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherResponse ;
    }

}
