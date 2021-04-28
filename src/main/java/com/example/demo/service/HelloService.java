package com.example.demo.service;

import java.util.List;
import java.util.Map;

public interface HelloService {

    List<Map<String, String>> gangland(String bingeBH);
    List<Map<String, String>> seetiwen(String bingeBH);
    List<Map<String, String>> seexinlv(String bingeBH);
    List<Map<String, String>> seexueyang(String bingeBH);
    List<Map<String, String>> seemaibo(String bingeBH);
    List<Map<String, String>> baojing();

    boolean login(String username, String password);

    void insertbaojing(String MC, String max, String min);

    List<Map<String, String>> getbaojing();
}
