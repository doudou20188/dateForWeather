package com.cskaoyan.demo2.service;

import com.cskaoyan.demo2.bean.WeatherResponse;

import java.io.IOException;

public interface WeatherApiService {

    WeatherResponse getWeatherInfoByCityName(String cityName) throws IOException;

    WeatherResponse getWeatherInfoByCityId(String cityId) throws IOException;

}
