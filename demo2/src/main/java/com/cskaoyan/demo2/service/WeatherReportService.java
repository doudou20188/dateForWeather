package com.cskaoyan.demo2.service;


import com.cskaoyan.demo2.bean.Weather;

import java.io.IOException;

public interface WeatherReportService {
      Weather getWeatherByCityId(String cityId) throws IOException;
       Weather getWeatherByCityName(String cityName)throws IOException;
}
