package com.example.demo.controller;

import com.example.demo.entity.Treasure;
import com.example.demo.service.TreasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/treasures")
@RestController
public class TreasureController {

    @Autowired
    private TreasureService treasureService;

    @GetMapping("/add")
    public Object insertTreasure(){
        List<Treasure> treasure = treasureService.insert();
        return treasure;
    }

    @GetMapping("/delete")
    public Object deleteAll(){
        treasureService.deleteAll();
        return "宝物已经全部删除";
    }
}
