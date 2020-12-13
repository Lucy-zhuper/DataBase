package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.MarketRepository;
import com.example.demo.repository.TreasureRepository;
import com.example.demo.service.GameService;
import com.example.demo.service.TreasureService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class IndexController {

    @Autowired
    private UserService userService;
    @Autowired
    private TreasureService treasureService;
    @Autowired
    private GameService gameService;
    @Autowired
    private TreasureRepository treasureRepository;

    @GetMapping("/register")
    public String gg(){
        return "index.html";
    }

//    @GetMapping("/register")
//    public String register(){
//        return "register.html";
//    }
//    @GetMapping("/")
////    public String login(){
////        return "game.html";
////    }

    @PostMapping("/register")
    public  String doregister(@RequestParam("pwd")String pwd, Model model){
        User user = userService.addOneUser(pwd);
        model.addAttribute("info","注册成功你的id是:"+user.getName()+"  你的密码是:"+pwd);
        return  "login.html";
    }

    @PostMapping("/login")
    public  String login(@RequestParam("id")String id, @RequestParam("pwd")String pwd,
                         Model model, HttpServletRequest request){
        User user = userService.login(id,pwd);
        if (user!=null){
            model.addAttribute("user",user);
            HttpSession session = request.getSession();
            session.setAttribute("user",id);
            return "redirect:/";
        }
        return  "login.html";
    }

    @GetMapping("/")
    public  String index(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        String user1 = (String) session.getAttribute("user");
        if (user1==null){
            return  "login.html";
        }
        User user = userService.getUserById(user1);
        List<WearTreasure> wearTreasureList = userService.findWearTreasure(user.getName());
        Treasure tool = new Treasure();
        Treasure decoration = new Treasure();


        for (int i = 0; i < wearTreasureList.size(); i++) {
            Treasure t = treasureRepository.findByTreasureName(wearTreasureList.get(i).getTreasureName());
            if(t.getAttribute().equals("tool")){
                tool.setAttribute("tool");
                tool.setTreasureName(t.getTreasureName());
                tool.setValue(t.getValue());
            }else{
                decoration.setAttribute("decoration");
                decoration.setTreasureName(t.getTreasureName());
                decoration.setValue(t.getValue());
            }
        }
        model.addAttribute("user",user);
        model.addAttribute("tool",tool);
        model.addAttribute("decoration",decoration);
        session.setAttribute("username",user.getName());
        return  "game.html";
    }

    @GetMapping("/storage")
    public String storage(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        String user1 = (String) session.getAttribute("user");
        if (user1==null){
            return  "login.html";
        }
        User user = userService.getUserById(user1);
        List<Storage> storageList = userService.findStorage(user.getName());

        model.addAttribute("storageList",storageList);
        return "storage.html";
    }

    @GetMapping("/treasure")
    public String treasure(HttpServletRequest request,Model model){
//        HttpSession session = request.getSession();
//        String user1 = (String) session.getAttribute("user");
//        if (user1==null){
//            return  "login.html";
//        }
//        User user = userService.getUserById(user1);
        List<Treasure> treasureList = treasureService.findAll();
        model.addAttribute("treasureList",treasureList);
        return "treasure.html";
    }

    @GetMapping("/market")
    public String market(HttpServletRequest request,Model model){

        List<Market> marketList = gameService.findAllMarkets();
        model.addAttribute("uid", request.getSession().getAttribute("username"));
        model.addAttribute("marketList", marketList);

        return "market.html";
    }
}