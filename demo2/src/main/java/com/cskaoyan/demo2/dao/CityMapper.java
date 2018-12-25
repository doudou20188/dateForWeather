package com.cskaoyan.demo2.dao;

import com.cskaoyan.demo2.bean.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther: YangTao
 * @Date: 2018/12/25 0025
 */
@Mapper
public interface CityMapper {
    @Select("select * from city")
    List<City>findAllCities();



}
