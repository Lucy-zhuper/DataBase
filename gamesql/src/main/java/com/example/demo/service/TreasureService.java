package com.example.demo.service;

import com.example.demo.entity.Treasure;
import com.example.demo.repository.TreasureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreasureService {

    @Autowired
    private TreasureRepository treasureRepository;

    public void deleteAll(){
        List<Treasure> all =  treasureRepository.findAll();
        all.forEach((t)->{
            treasureRepository.delete(t);
        });
    }

    public List<Treasure> insert(){
        Treasure tool1 = new Treasure("UZI",1,"tool");
        Treasure tool2 = new Treasure("Thomson",2,"tool");
        Treasure tool3 = new Treasure("M16A4",3,"tool");
        Treasure tool4 = new Treasure("AKM",4,"tool");
        Treasure tool5 = new Treasure("M416",5,"tool");
        Treasure tool6 = new Treasure("98K",6,"tool");
        Treasure tool7 = new Treasure("GROZA",7,"tool");
        Treasure tool8 = new Treasure("AUG",8,"tool");
        Treasure tool9 = new Treasure("M24",9,"tool");
        Treasure tool10 = new Treasure("AWM",10,"tool");

        Treasure decoration1 = new Treasure("一级甲",1,"decoration");
        Treasure decoration2 = new Treasure("一级包",2,"decoration");
        Treasure decoration3 = new Treasure("一级头",3,"decoration");
        Treasure decoration4 = new Treasure("二级甲",4,"decoration");
        Treasure decoration5 = new Treasure("二级包",5,"decoration");
        Treasure decoration6 = new Treasure("二级头",6,"decoration");
        Treasure decoration7 = new Treasure("三级甲",7,"decoration");
        Treasure decoration8 = new Treasure("三级包",8,"decoration");
        Treasure decoration9 = new Treasure("三级头",9,"decoration");
        Treasure decoration10 = new Treasure("吉利服",10,"decoration");

        List<Treasure> treasureList = new ArrayList<Treasure>();
        treasureList.add(tool1);
        treasureList.add(decoration1);
        treasureList.add(tool2);
        treasureList.add(decoration2);
        treasureList.add(tool3);
        treasureList.add(decoration3);
        treasureList.add(tool4);
        treasureList.add(decoration4);
        treasureList.add(tool5);
        treasureList.add(decoration5);
        treasureList.add(tool6);
        treasureList.add(decoration6);
        treasureList.add(tool7);
        treasureList.add(decoration7);
        treasureList.add(tool8);
        treasureList.add(decoration8);
        treasureList.add(tool9);
        treasureList.add(decoration9);
        treasureList.add(tool10);
        treasureList.add(decoration10);

        List<Treasure> list = treasureRepository.saveAll(treasureList);

        return list;
    }

    public List<Treasure> findAll(){
        return treasureRepository.findAll();
    }
}
