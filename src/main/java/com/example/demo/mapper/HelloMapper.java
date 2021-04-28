package com.example.demo.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface HelloMapper {


    String getzonglan();

    List<Map<String,String>> seetiwen(@Param("bingrenBH") String bingrenBH);
    List<Map<String,String>> seemaibo(@Param("bingrenBH") String bingrenBH);
    List<Map<String,String>> seexinlv(@Param("bingrenBH") String bingrenBH);
    List<Map<String,String>> seexueyang(@Param("bingrenBH") String bingrenBH);

    List<Map<String,String>> baojing();

    String login(@Param("username")String username, @Param("password")String password);

    void insertbaojing(@Param("MC") String MC, @Param("max") String max, @Param("min") String min);
}
