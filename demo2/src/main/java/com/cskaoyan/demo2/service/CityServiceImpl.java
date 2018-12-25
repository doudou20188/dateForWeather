package com.cskaoyan.demo2.service;

import com.cskaoyan.demo2.bean.City;
import com.cskaoyan.demo2.dao.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: YangTao
 * @Date: 2018/12/25 0025
 */
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityMapper cityMapper;
    @Override
    public List<City> getCityList() {
        List<City> allCities = cityMapper.findAllCities();
        return allCities;
    }
}
