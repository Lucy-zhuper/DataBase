package com.example.demo.service;

import com.example.demo.entity.Market;
import com.example.demo.entity.Storage;
import com.example.demo.entity.Treasure;
import com.example.demo.entity.User;
import com.example.demo.repository.MarketRepository;
import com.example.demo.repository.StorageRepository;
import com.example.demo.repository.TreasureRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GameService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TreasureRepository treasureRepository;
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private MarketRepository marketRepository;
    @Autowired
    private UserService userService;
    /*寻宝 劳动 */
    //对每个用户
    public String  work(String username){
        User user = userService.getUserById(username);
        if (user==null){
            return "该用户不存在";
        }else{
            int luckValue = user.getLuck();
            //计算劳动值
            int workAbility = user.getAbility();
            //劳动赚取的酬劳公式  =  workAbility*5 + 10
            Integer workBalance = workAbility*5 + 10;
            Integer userBalance = user.getBalance()+workBalance;
            user.setBalance(userBalance);

            //数据库更新金币
            userRepository.save(user);
            return user.getName()+"获得了"+user.getBalance()+"金币";
        }
    }

    public Treasure treasureHunt(String username){
        User user = userService.getUserById(username);

        String result = "";
        if (user==null){
            result = "该用户不存在";
            return null;
        }else{
            //寻宝获取的宝物
            Treasure treasure = treasureHunt(user.getLuck());

            List<Storage> storageList = storageRepository.findByUserName(user.getName());
            //如果存储箱超出容量
            int total = 0;
            int min_value = 11;
            String min_treasure = null;
            if(storageList!=null){
                for (Storage storage:
                        storageList) {
                    total+=storage.getNumber();
                    //找到能力值或者运气值最低的宝物名字
                    if(treasureRepository.findByTreasureName(storage.getTreasureName()).getValue()<min_value){
                        min_treasure = treasureRepository.findByTreasureName(storage.getTreasureName()).getTreasureName();
                    }
                }
//                total = storageList.stream().mapToInt(Storage::getNumber).sum();
            }


            //设置存储箱大小为10
            if(total<=10) {
                Storage storage = storageRepository.findByUserNameAndTreasureName(user.getName(), treasure.getTreasureName());
                if (storage==null) {
                    storage = new Storage(user.getName(), treasure.getTreasureName());
                    storage.setNumber(1);
                    storageRepository.save(storage);
                    result = "该宝物是新获得的，宝物名是"+treasure.getTreasureName();

                } else if(storage.getTreasureName().equals(treasure.getTreasureName())){
                    storage.setNumber(storage.getNumber() + 1);
                    storageRepository.save(storage);
                    result = "该宝物已经存在了，只需要数量增加1就ok啦";
                }

            }else{
                //丢失存储箱中能力值最低的宝物
                System.out.println("超出容量");
                Storage min_storage = storageRepository.findByUserNameAndTreasureName(user.getName(),min_treasure);
                if(min_storage.getNumber()==1){
                    storageRepository.delete(min_storage);
                }else{
                    min_storage.setNumber(min_storage.getNumber()-1);
                    storageRepository.save(min_storage);
                }

                Storage storage = storageRepository.findByUserNameAndTreasureName(user.getName(), treasure.getTreasureName());
                if (storage!=null&&storage.getTreasureName().equals(treasure.getTreasureName())) {
                    storage.setNumber(storage.getNumber() + 1);
                } else {
                    storage = new Storage(user.getName(), treasure.getTreasureName());
                    storage.setNumber(1);
                }
                result = "超出容量啦，已经删除存储箱中能力值最低的宝物，将新宝物放进存储箱啦";
            }
            return treasure;
        }

    }

    public List<Market> findAllMarkets(){
        List<Market> marketList = marketRepository.findAll();
        return marketList;
    }
//    public  void  treasureHuntAndWork(){
//        List<User> all = userRepository.findAll();
//        System.out.println("寻宝 劳动中 ....");
//        all.forEach(user -> {
//            //计算运气值
//            int luckValue = user.getLuck();
//            //计算劳动值
//            int workAbility = user.getAbility();
//            //劳动赚取的酬劳公式  =  workAbility*5 + 10
//            Integer workBalance = workAbility*5 + 10;
//            Integer userBalance = user.getBalance()+workBalance;
//            user.setBalance(userBalance);
//
//            //数据库更新金币
//            userRepository.save(user);
//            //寻宝获取的宝物
//            Treasure treasure = treasureHunt(luckValue);
//
//            List<Storage> storageList = storageRepository.findByUserName(user.getName());
//            //如果存储箱超出容量
//            int total = 0;
//            int min_value = 11;
//            String min_treasure = null;
//            if(storageList!=null){
//                for (Storage storage:
//                     storageList) {
//                    total+=storage.getNumber();
//                    //找到能力值或者运气值最低的宝物名字
//                    if(treasureRepository.findByTreasureName(storage.getTreasureName()).getValue()<min_value){
//                         min_treasure = treasureRepository.findByTreasureName(storage.getTreasureName()).getTreasureName();
//                    }
//                }
////                total = storageList.stream().mapToInt(Storage::getNumber).sum();
//            }
//
//
//            //设置存储箱大小
//            if(total<=6) {
//                Storage storage = storageRepository.findByUserNameAndTreasureName(user.getName(), treasure.getTreasureName());
//                if (storage!=null&&storage.getTreasureName().equals(treasure.getTreasureName())) {
//                    storage.setNumber(storage.getNumber() + 1);
//                } else {
//                    storage = new Storage(user.getName(), treasure.getTreasureName());
//                    storage.setNumber(1);
//                }
//
//                storageRepository.save(storage);
//            }else{
//                //丢失存储箱中能力值最低的宝物
//                System.out.println("超出容量");
//                Storage min_storage = storageRepository.findByUserNameAndTreasureName(user.getName(),min_treasure);
//                if(min_storage.getNumber()==1){
//                    storageRepository.delete(min_storage);
//                }else{
//                    min_storage.setNumber(min_storage.getNumber()-1);
//                    storageRepository.save(min_storage);
//                }
//
//                Storage storage = storageRepository.findByUserNameAndTreasureName(user.getName(), treasure.getTreasureName());
//                if (storage!=null&&storage.getTreasureName().equals(treasure.getTreasureName())) {
//                    storage.setNumber(storage.getNumber() + 1);
//                } else {
//                    storage = new Storage(user.getName(), treasure.getTreasureName());
//                    storage.setNumber(1);
//                }
//            }
//
//        });
//    }
    private Treasure treasureHunt(Integer luckValue){
        Random random = new Random();
        int nextInt = random.nextInt(100);
//        luckValue = luckValue/2==0? 1: luckValue/2+1;
//        int i = random.nextInt(luckValue);
//        System.out.println(luckValue+ " lucky " + i);
        Treasure treasure = null;
        if (nextInt%2==0){//偶数是 工具
            treasure = treasureRepository.findByValueAndAttribute(luckValue+1,"tool");
        }else {//奇数是饰品
            treasure = treasureRepository.findByValueAndAttribute(luckValue+1,"decoration");
        }
        return  treasure;
    }
}
