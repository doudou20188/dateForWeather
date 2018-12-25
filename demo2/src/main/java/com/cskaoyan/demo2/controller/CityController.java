package com.cskaoyan.demo2.controller;

import com.cskaoyan.demo2.bean.City;
import com.cskaoyan.demo2.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: YangTao
 * @Date: 2018/12/25 0025
 */
@RestController
public class CityController {
    @Autowired
    CityService cityService;

    @GetMapping("/getcities") //指定get 方法取获取
    public List<City> getAllCities(){
        List<City> cityList=cityService.getCityList();
        return cityList;

    }
}
