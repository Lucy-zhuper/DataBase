package com.example.demo.service;


import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private TreasureRepository treasureRepository;
    @Autowired
    private WearTreasureRepository wearTreasureRepository;
    @Autowired
    private MarketRepository marketRepository;

    //新注册的用户
    public User addOneUser(String pwd){
        User user = new User();
        user.setPwd(pwd);
        user.setBalance(10);
        user.setAbility(0);
        user.setLuck(0);
        User insert = userRepository.save(user);
        return insert;
    }

    //删除所有用户
    public User deleteAll(){
        List<User> all = userRepository.findAll();
        all.forEach((u)->{
            userRepository.delete(u);
        });
        return null;
    }

    //找出所有用户
    public List<User> findAll(){
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public User getUserById(String id) {
        User byId = userRepository.findByUserName(id);
        return  byId;
    }

    public User login(String id, String pwd) {

        User one = userRepository.findByUserName(id);
        String result = null;
        if (one!=null && one.getPwd().equals(pwd)){
            result = "恭喜成功进入游戏";
        }else if(one==null){
            result = "该用户名不存在，请重新输入";
        }else if(!one.getPwd().equals(pwd)) {
            result = "密码输入错误，请重新输入";
        }
        return one;
    }

    public List<Storage> findStorage(String userName){

        List<Storage> storageList = storageRepository.findByUserName(userName);
        return storageList;
    }

    public List<WearTreasure> findWearTreasure(String username){
        List<WearTreasure> wearTreasureList = wearTreasureRepository.findByUserName(username);
        return wearTreasureList;
    }
    //穿戴宝物
    public WearTreasure wearTreasure(String userName, String treasureName){
        //先判断用户存储箱中是否存在该宝物
        int tool = 0; //用户穿的工具数
        int decoration = 0; //配饰数
        User user = userRepository.findByUserName(userName);
        Treasure treasure = treasureRepository.findByTreasureName(treasureName);
        if (user==null) {
            //System.out.println("用户名不存在哦，仔细检查一下是不是输错了哟！");
            return null;
        }
        Storage storage = storageRepository.findByUserNameAndTreasureName(userName,treasureName);
        if(storage==null){
            //System.out.println("存储箱中不存在该宝物，不能穿戴该宝物哦！");
            return null;
        }else{
            //获取宝物价值
            Integer value = treasure.getValue();
            List<WearTreasure> wearTreasureList = wearTreasureRepository.findByUserName(userName);
            for (int i = 0; i < wearTreasureList.size(); i++) {
                Treasure t = treasureRepository.findByTreasureName(wearTreasureList.get(i).getTreasureName());
                if(t.getAttribute().equals("tool")){
                    tool+=1;
                }else{
                    decoration+=1;
                }
            }
            WearTreasure wearTreasure = wearTreasureRepository.findByUserNameAndTreasureName(userName,treasureName);
            if(tool<1&&treasure.getAttribute().equals("tool")){
                //查看wear_treasure表中是否存在该记录
                wearTreasure = new WearTreasure(userName,treasureName);
                wearTreasure.setNumber(1);
                user.setBalance(treasure.getValue());
                userRepository.save(user);
                wearTreasureRepository.save(wearTreasure);
                return wearTreasure;
            }else if(decoration<1&&treasure.getAttribute().equals("decoration")){
                wearTreasure = new WearTreasure(userName,treasureName);
                user.setLuck(treasure.getValue());
                wearTreasure.setNumber(1);
                userRepository.save(user);
                wearTreasureRepository.save(wearTreasure);
                return wearTreasure;
            }else{
                System.out.println("您的装备已满，包括一个配饰，一个工具，如果想佩戴新的，请先卸下佩戴的宝物");
                return null;
            }


//                if(treasure.getAttribute().equals("tool")){
//                    //只能佩戴一个工具
//                    if(wearTreasure!=null){
//                        System.out.println("只能佩戴一个工具");
//                    }else{
//                        wearTreasure = new WearTreasure(userName,treasureName);
//                        wearTreasure.setNumber(1);
//                        user.setAbility(value);
//                    }
//                }else{
//                    //只能佩戴两个配饰
//                    if(wearTreasure!=null&&wearTreasure.getNumber()==2){
//                        System.out.println("只能佩戴两个配饰");
//                    }else if(wearTreasure!=null&&wearTreasure.getNumber()==1){
//                        wearTreasure.setNumber(wearTreasure.getNumber()+1);
//                        user.setLuck(user.getLuck()+value);
//                    }else if(wearTreasure==null){
//                        wearTreasure = new WearTreasure(userName,treasureName);
//                        wearTreasure.setNumber(1);
//                        user.setLuck(user.getLuck()+value);
//                    }
//                }
//                userRepository.save(user);
//                wearTreasureRepository.save(wearTreasure);
//
//                return wearTreasure;


//            }
        }
//        User user = userRepository.findByUserName(userName);
        //user更新能力值或运气值，成功穿戴
//        System.out.println(user);
//        Treasure treasure = treasureRepository.findByTreasureName(treasureName);
//        if (user==null) return null;
//        List<Storage> storage = storageRepository.findAll();
//        WearTreasure wearTreasure = new WearTreasure(userName,treasureName);
//        WearTreasure wearTreasure = wearTreasureRepository.findByUserNameAndTreasureName(userName,treasureName);

//        Integer value = treasure.getValue();

//        if(treasure.getAttribute().equals("tool")){
//            //只能佩戴一个工具
//            if(wearTreasure!=null){
//                System.out.println("只能佩戴一个工具");
//            }else{
//                wearTreasure = new WearTreasure(userName,treasureName);
//                wearTreasure.setNumber(1);
//                user.setAbility(value);
//            }
//        }else{
//            //只能佩戴两个配饰
//            if(wearTreasure!=null&&wearTreasure.getNumber()==2){
//                System.out.println("只能佩戴两个配饰");
//            }else if(wearTreasure!=null&&wearTreasure.getNumber()==1){
//                wearTreasure.setNumber(wearTreasure.getNumber()+1);
//                user.setDecorations(user.getLuck()+value);
//            }else if(wearTreasure==null){
//                wearTreasure = new WearTreasure(userName,treasureName);
//                wearTreasure.setNumber(1);
//                user.setDecorations(user.getLuck()+value);
//            }
//        }
//        userRepository.save(user);
//        wearTreasureRepository.save(wearTreasure);
    }

    //脱下宝物
    public WearTreasure underWearTreasure(String userName, String treasureName){
        User user = userRepository.findByUserName(userName);
        //user更新能力值或运气值，成功脱下
        Treasure treasure = treasureRepository.findByTreasureName(treasureName);
        if (user==null){
            System.out.println("用户名不存在哦，仔细检查一下是不是输错了哟！");
            return null; }
        WearTreasure wearTreasure = wearTreasureRepository.findByUserNameAndTreasureName(userName,treasureName);
        if (wearTreasure==null){//判断用户是否已经穿上宝物
            System.out.println("糊涂了，都还没穿上宝物，怎么能脱下呢");
        }else {
            if (treasure.getAttribute().equals("tool")) {
                wearTreasureRepository.delete(wearTreasure);
                user.setAbility(0);
            } else{
                wearTreasureRepository.delete(wearTreasure);
                user.setLuck(0);
            }
        }
        userRepository.save(user);
        return wearTreasure;
    }

    //出售宝物
    public String sellTreasure(String userName, String treasureName, int price){

        Storage storage = storageRepository.findByUserNameAndTreasureName(userName,treasureName);
        Market market = marketRepository.findByUserNameAndTreasureNameAndPrice(userName,treasureName,price);
        WearTreasure wearTreasure = wearTreasureRepository.findByUserNameAndTreasureName(userName,treasureName);
        if(storage==null){
            //宝物不在存储箱中
            System.out.println("该宝物不在存储箱中");
            return "该宝物不在存储箱中";
        }else{
            if(market==null){
                market = new Market(userName,treasureName,price);
                market.setNumber(1);
            }else{
                market.setNumber(market.getNumber()+1);
            }
        }
        marketRepository.save(market);
        return "您的宝物已成功挂牌出售";
    }

    //取消出售宝物
    public Market cancelSellTreasure(String userName, String treasureName, int price){
        Market market = marketRepository.findByUserNameAndTreasureNameAndPrice(userName,treasureName,price);
        if(market==null){
            System.out.println("该宝物还未出售");
        }else{
            if (market.getNumber()==1){
                marketRepository.delete(market);
            }else {
                market.setNumber(market.getNumber()-1);
            }
        }
        return market;
    }

    //购买宝物
    public boolean buyTreasure(String buyerName, String sellerName, String treasureName, int price){
        //从市场找到该宝物
        Market market = marketRepository.findByUserNameAndTreasureNameAndPrice(sellerName,treasureName,price);

        if(market==null){
            System.out.println("市场上都没有该宝物怎么能购买呢");
            return false;
        }
        //不能购买自己出售的宝物
        if(buyerName.equals(sellerName)){
            return false;
        }
        //查看买家余额是否足够购买宝物,扣除买家余额,增加卖家余额
        User buyer  = userRepository.findByUserName(buyerName);
        User seller = userRepository.findByUserName(sellerName);
        Treasure treasure = treasureRepository.findByTreasureName(treasureName);
        Storage sellerStorage = storageRepository.findByUserNameAndTreasureName(sellerName,treasureName);
        Storage buyerStorage = storageRepository.findByUserNameAndTreasureName(buyerName,treasureName);
        if(buyer.getBalance()<price){
            System.out.println("买家余额不足");
            return false;
        }else{
            buyer.setBalance(buyer.getBalance()-price);
            seller.setBalance(seller.getBalance()+price);
            //从市场删除该宝物,或者将宝物数目减1
            if (market.getNumber()==1){
                marketRepository.delete(market);
            }else {
                market.setNumber(market.getNumber()-1);
                marketRepository.save(market);
            }
            //从卖家存储箱中删除
            if (sellerStorage.getNumber()!=1) {
                sellerStorage.setNumber(sellerStorage.getNumber() - 1);
                storageRepository.save(sellerStorage);
            } else {
                storageRepository.delete(sellerStorage);
            }
            //添加到买家存储箱,先判断是否超出买家存储箱容量
            List<Storage> storageList = storageRepository.findByUserName(buyer.getName());
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
            }

            //设置存储箱大小
            if(total<=6) {
                Storage storage = storageRepository.findByUserNameAndTreasureName(buyer.getName(), treasure.getTreasureName());
                if (storage!=null&&storage.getTreasureName().equals(treasure.getTreasureName())) {
                    storage.setNumber(storage.getNumber() + 1);
                } else {
                    storage = new Storage(buyer.getName(), treasure.getTreasureName());
                    storage.setNumber(1);
                }
                storageRepository.save(storage);
            }else{
                //丢失存储箱中能力值最低的宝物
                System.out.println("超出容量");
                Storage min_storage = storageRepository.findByUserNameAndTreasureName(buyer.getName(),min_treasure);
                if(min_storage.getNumber()==1){
                    storageRepository.delete(min_storage);
                }else{
                    min_storage.setNumber(min_storage.getNumber()-1);
                    storageRepository.save(min_storage);
                }
                Storage storage = storageRepository.findByUserNameAndTreasureName(buyer.getName(), treasure.getTreasureName());
                if (storage!=null&&storage.getTreasureName().equals(treasure.getTreasureName())) {
                    storage.setNumber(storage.getNumber() + 1);
                } else {
                    storage = new Storage(buyer.getName(), treasure.getTreasureName());
                    storage.setNumber(1);
                }
            }

//            if (buyerStorage==null){
//                buyerStorage = new Storage(buyerName,treasureName);
//                buyerStorage.setNumber(1);
//            }else{
//                buyerStorage.setNumber(buyerStorage.getNumber()+1);
//            }
            return true;
        }
    }
}
