package com.cskaoyan.demo2.job;

import com.cskaoyan.demo2.bean.City;
import com.cskaoyan.demo2.service.CityService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 定时类，定时任务锁定的类，执行定时任务具体的代码
 * @Auther: YangTao
 * @Date: 2018/12/25 0025
 */
public class WeatherDataJob extends QuartzJobBean {
    //定时取请求服务器，将各个城市的数据定时更新，存入到 redis \
    //目的 用户访问的是redis 中的数据，速度快
    @Autowired
    CityService cityService;
    @Autowired
    RestTemplate restTemplate;

    private static  final String server_uir="http://wthrcdn.etouch.cn/weather_mini";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("getDataFromserver!start!");
        System.out.println("反复执行任务");

        String jsonString="";

        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();

        List<City> allCities = cityService.getCityList();

        for (City city:allCities) {

            String key1 = server_uir + "?city=" + city.getCityName();
            String key2 = server_uir + "?citykey=" + city.getCityId();

            //发请求
            ResponseEntity<String> forEntity = restTemplate.getForEntity(key1, String.class);

            if (forEntity.getStatusCodeValue()==200){

                jsonString = forEntity.getBody().toString();

                System.out.println("jsonString"+jsonString);

                //存到redis缓存里
                stringStringValueOperations.set(key1,jsonString);

                //牺牲点空间，再存一个key
                stringStringValueOperations.set(key2,jsonString);


            }

        }




    }


}
