package com.example.demo.controller;


import com.example.demo.entity.Market;
import com.example.demo.entity.Treasure;
import com.example.demo.entity.User;
import com.example.demo.entity.WearTreasure;
import com.example.demo.repository.TreasureRepository;
import com.example.demo.service.GameService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private GameService gameService;

    @Autowired
    private TreasureRepository treasureRepository;
    @GetMapping("/addone/{pwd}")
    public User addOneUser(@PathVariable("pwd") String pwd){
        User user = userService.addOneUser(pwd);
        return  user;
    }

    @GetMapping("/clear")
    public Object deleteAll(){
        userService.deleteAll();
        String result="所有用户信息已经全部删除";
        return  result;
    }

    @GetMapping("/findall")
    public Object findAll(){
        List<User> all = userService.findAll();
        return  all;
    }
    //---------

    @GetMapping("/{id}")
    public  User getUser(@PathVariable("id") String id){
        User user = userService.getUserById(id);
//        userService.wearTreasure(user.getName(),"Thomson");
        return  user;
    }

    @GetMapping("/work/{username}")
    public String work(@PathVariable("username") String username){
        String s = gameService.work(username);
        return s;
    }

    @GetMapping("/hunt/{username}")
    public Object huntTreasure(@PathVariable("username") String username){
        Treasure s = gameService.treasureHunt(username);
        return s;
    }

    @GetMapping("/wear/{userName}/{treasureName}")
    public Object wear(@PathVariable("userName") String userName, @PathVariable("treasureName") String treasureName){
        WearTreasure wearTreasure = userService.wearTreasure(userName,treasureName);
        return wearTreasure;
    }

    @GetMapping("/underwear/{userName}/{treasureName}")
    public Object underwear(@PathVariable("userName") String userName, @PathVariable("treasureName") String treasureName){
        WearTreasure wearTreasure = userService.underWearTreasure(userName,treasureName);
        return userName+"脱下"+treasureName;
    }

    @GetMapping("/sell/{userName}/{treasureName}/{price}")
    public Object sell(@PathVariable("userName") String userName, @PathVariable("treasureName") String treasureName,
                       @PathVariable("price")int price){
        String result = userService.sellTreasure(userName,treasureName,price);
        return result;
    }

    @GetMapping("/cancelsell/{userName}/{treasureName}/{price}")
    public Object cancelsell(@PathVariable("userName") String userName, @PathVariable("treasureName") String treasureName,
                             @PathVariable("price")int price){
        Market market = userService.cancelSellTreasure(userName,treasureName,price);
        return market;
    }

    @GetMapping("/buy/{buyer}/{seller}/{treasureName}/{price}")
    public Object buy(@PathVariable("buyer") String buyer, @PathVariable("seller") String seller,
                      @PathVariable("treasureName") String treasureName, @PathVariable("price")int price){

        boolean ok = userService.buyTreasure(buyer,seller,treasureName,price);
        return ok? "购买成功":"购买失余额不足或宝物不存在或不能购买自己的装备";
    }
}
