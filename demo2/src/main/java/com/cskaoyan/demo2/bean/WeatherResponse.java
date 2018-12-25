package com.cskaoyan.demo2.bean;

public class WeatherResponse {

    private  int status ;
    private String desc;
    private Weather   data;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Weather getData() {
        return data;
    }

    public void setData(Weather data) {
        this.data = data;
    }


}
