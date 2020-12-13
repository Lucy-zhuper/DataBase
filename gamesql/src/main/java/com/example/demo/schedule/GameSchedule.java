package com.example.demo.schedule;


import com.example.demo.repository.TreasureRepository;
import com.example.demo.service.GameService;
import com.example.demo.service.TreasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GameSchedule {
    @Autowired
    private GameService gameService;
    @Autowired
    private TreasureService treasureService;
    @Autowired
    private TreasureRepository treasureRepository;
    /*游戏每天*/
    @Scheduled(fixedRate = 1000*60)
    public  void  everyDay() throws  Exception{
//        gameService.treasureHuntAndWork();
        treasureRepository.findAll();
    }


}
