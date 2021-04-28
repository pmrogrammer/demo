package com.example.demo.serviceImpl;

import com.example.demo.mapper.HelloMapper;
import com.example.demo.service.HelloService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HelloServiceImpl  implements HelloService {


    @Resource
    private HelloMapper helloMapper;

    @Override
    public List<Map<String, String>> gangland(String bingeBH) {
//        String getzonglan = helloMapper.getzonglan();
//        System.out.println(getzonglan);
        List<Map<String,String>> tiwen = helloMapper.seetiwen(bingeBH);
        List<Map<String,String>> maibo = helloMapper.seemaibo(bingeBH);
        List<Map<String,String>> xinlv = helloMapper.seexinlv(bingeBH);
        List<Map<String,String>> xueyang = helloMapper.seexueyang(bingeBH);

        List<Map<String, String>> list = new ArrayList<>();
        list.add(tiwen.get(0));
        list.add(maibo.get(0));
        list.add(xinlv.get(0));
        list.add(xueyang.get(0));
        return list;
    }

    @Override
    public List<Map<String, String>> seetiwen(String bingeBH) {
        return helloMapper.seetiwen(bingeBH);
    }

    @Override
    public List<Map<String, String>> seexinlv(String bingeBH) {
        return helloMapper.seexinlv(bingeBH);
    }

    @Override
    public List<Map<String, String>> seexueyang(String bingeBH) {
        return helloMapper.seexueyang(bingeBH);
    }

    @Override
    public List<Map<String, String>> seemaibo(String bingeBH) {
        return helloMapper.seemaibo(bingeBH);
    }

    @Override
    public List<Map<String, String>> baojing() {
        return helloMapper.baojing();
    }

    @Override
    public boolean login(String username, String password) {
        String flag = helloMapper.login(username, password);
        if (flag == null || flag.equals(""))
            return false;
        return true;
    }

    @Override
    public void insertbaojing(String MC, String max, String min) {
        helloMapper.insertbaojing(MC, max, min);
    }

    @Override
    public List<Map<String, String>> getbaojing() {
        return helloMapper.baojing();
    }

}
