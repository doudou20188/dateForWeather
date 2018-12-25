package com.cskaoyan.demo2.service;
import com.cskaoyan.demo2.bean.Weather;
import com.cskaoyan.demo2.bean.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class WeatherReportServiceImpl implements WeatherReportService {


    @Autowired
    WeatherApiService weatherApiService;

    @Override
    public Weather getWeatherByCityId(String cityId) throws IOException {
        WeatherResponse weatherInfoByCityId = weatherApiService.getWeatherInfoByCityId(cityId);
        Weather data = weatherInfoByCityId.getData();
        return data;
    }
    @Override
    public Weather getWeatherByCityName(String cityName) throws IOException {
        WeatherResponse weatherInfoByCityName = weatherApiService.getWeatherInfoByCityName(cityName);
        Weather data = weatherInfoByCityName.getData();
        return data;
    }
}
