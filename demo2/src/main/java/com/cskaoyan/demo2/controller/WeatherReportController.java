package com.cskaoyan.demo2.controller;

import com.cskaoyan.demo2.bean.City;
import com.cskaoyan.demo2.bean.Weather;
import com.cskaoyan.demo2.service.CityService;
import com.cskaoyan.demo2.service.WeatherReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/report")
public class WeatherReportController {


    @Autowired
    CityService cityService;

    @Autowired
    WeatherReportService weatherReportService;

    @RequestMapping("/cityId/{cityId}")
    public String getWeatherReportByCityId(@PathVariable("cityId")  String cityId , Model model){
        HashMap<String,Object> reportModel=new HashMap<>();
        reportModel.put("title","cityName");
        List<City> aLlCities = cityService.getCityList();
        reportModel.put("cityList",aLlCities);
        reportModel.put("cityId",cityId);

        Weather weather= null;
        try {
            weather = weatherReportService.getWeatherByCityId(cityId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        reportModel.put("report",weather);
        model.addAttribute("reportModel",reportModel);
       return "/weather/report.html";
    }
    @RequestMapping("/cityName/{cityName}")
    public String getWeatherReportByCityName(@PathVariable("cityName")  String cityName , Model model){
        HashMap<String,Object> reportModel=new HashMap<>();
        reportModel.put("title","cityName");
        List<City> aLlCities = cityService.getCityList();
        reportModel.put("cityList",aLlCities);
        reportModel.put("cityId",cityName);//
        Weather weather= null;
        try {
            weather = weatherReportService.getWeatherByCityName(cityName);

        } catch (IOException e) {
            e.printStackTrace();
        }
        reportModel.put("report",weather);
        model.addAttribute("reportModel",reportModel);
       return "/weather/report.html";
    }









}
