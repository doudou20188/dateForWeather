package com.cskaoyan.demo2.config;

import com.cskaoyan.demo2.job.WeatherDataJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定时器配置类，注入bean
 * @Auther: YangTao
 * @Date: 2018/12/25 0025
 */
@Configuration
public class QuartzConfiguration {
    private  static final int Time_repeat=60*10;
    @Bean
    public JobDetail getWeatherDateFromServer(){
        return JobBuilder.newJob(WeatherDataJob.class).withIdentity("WeatherDataJob").storeDurably().build();

    }
    @Bean
    public Trigger weatherDateTrigger(){
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(Time_repeat).repeatForever();
        return TriggerBuilder.newTrigger().forJob(getWeatherDateFromServer()).withIdentity("weatherDateTrigger").withSchedule(simpleScheduleBuilder).build();
    }



}
