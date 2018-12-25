package com.cskaoyan.demo2.service;

import com.cskaoyan.demo2.bean.WeatherResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@Service
public class WeatherApiServiceImpl implements WeatherApiService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate; //使用redis 缓存优化速度,spring 对redis支持


    //定义常量uri
    private static final String uri="http://wthrcdn.etouch.cn/weather_mini";

    //http://wthrcdn.etouch.cn/weather_mini?city=北京
    @Override
    public WeatherResponse getWeatherInfoByCityName(String cityName) throws IOException {
        //HttpGet httpGet = new HttpGet("http://wthrcdn.etouch.cn/weather_mini?city="+cityName);
        String url =uri+"?city="+cityName;
        WeatherResponse weatherResponse = getWeatherResponse(url);
        return weatherResponse;
    }


    //http://wthrcdn.etouch.cn/weather_mini?citykey=101280301
    @Override
    public WeatherResponse getWeatherInfoByCityId(String cityId) throws IOException {
        String url =uri+"?citykey="+cityId;
        WeatherResponse weatherResponse = getWeatherResponse(url);
        return weatherResponse;
    }

    private WeatherResponse getWeatherResponse(String url) throws IOException {
        String key =url;
        String stringBody = "";

        //将截取到的json str 转为对应的对象封装
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        if (stringRedisTemplate.hasKey(key)){
            System.out.println("find date from redis!");
            stringBody = opsForValue.get(key);

        }else {
            System.out.println("find date from onlineServer!");
            ResponseEntity<String> respString = restTemplate.getForEntity(url, String.class);
            if (respString.getStatusCodeValue() == 200) {
                stringBody = respString.getBody(); //过滤掉报文其他数据
                opsForValue.set(key,stringBody);//塞入redis 中
            }
        }
        ObjectMapper mapper = new ObjectMapper();//str 转为对应的对象注入数据
        WeatherResponse weatherResponse = mapper.readValue(stringBody, WeatherResponse.class);
        return weatherResponse;

    }


}
