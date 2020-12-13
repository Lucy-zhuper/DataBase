package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.repository.MarketRepository;
import com.example.demo.repository.StorageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WearTreasureRepository;
import com.example.demo.service.GameService;
import com.example.demo.service.TreasureService;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.persistence.Table;
import java.util.List;

@SpringBootTest
class SqlApplicationTests {

	@Autowired
	private GameService gameService;
	@Autowired
	private UserService userService;
	@Autowired
	private TreasureService treasureService;
	@Autowired
	private StorageRepository storageRepository;
	@Autowired
	private WearTreasureRepository wearTreasureRepository;
	@Autowired
	private MarketRepository marketRepository;
	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
		treasureService.insert();
		System.out.println("已成功插入所有宝物");
	}
	//treasureService测试
	@Test
	public void insertTest(){

	}

	@Test
	public void deleteAll(){
		treasureService.deleteAll();
		System.out.println("已成功删除所有宝物");
	}

	@Test
	public void findAll(){
		treasureService.findAll();
		System.out.println("已成功找出所有宝物");
	}

	//GameService测试
	@Test
	public void workTest(){
		User user = userService.addOneUser("123");
		gameService.work(user.getName());
		gameService.work("11");
	}

	@Test
	public void treasureHuntTest(){
		gameService.treasureHunt("123");
		User user = userService.addOneUser("123");
//		gameService.treasureHunt(user.getName());
		for(int i=0;i<15;i++){
			gameService.treasureHunt(user.getName());
		}

	}
	@Test
	public void findAllMarketsTest(){
		gameService.findAllMarkets();
	}

	//userService测试
	@Test
	public void addOneUserTest(){
		User user = userService.addOneUser("123");
		System.out.println("用户已成功注册");
	}

	@Test
	public void deleteAllTest(){
		userService.deleteAll();
		System.out.println("用户已全部删除");
	}

	@Test
	public void findAllTest(){
		userService.findAll();
		System.out.println("用户已全部找出");
	}

	@Test
	public void getUserByIdTest(){
		User user = userService.addOneUser("123");
		userService.getUserById(user.getName());
		System.out.println("根据用户名"+user.getName()+",成功找到该用户");
	}

	@Test
	public void loginTest(){
		User user = userService.addOneUser("123");
		userService.login(user.getName(),user.getPwd());
		System.out.println("恭喜成功进入游戏");
		userService.login(user.getName(),"1");
		System.out.println("密码输入错误，请重新输入");
		userService.login("123",user.getPwd());
		System.out.println("该用户名不存在，请重新输入");
	}

	@Test
	public void findStorageTest(){
		User user = userService.addOneUser("123");
		userService.findStorage(user.getName());
		System.out.println("成功找到该用户存储箱");
	}

	@Test
	public void wearTreasureTest(){
		//用户名不存在
		userService.wearTreasure("123","一级包");
		//宝物不在存储箱
		User user = userService.addOneUser("123");
		userService.wearTreasure(user.getName(),"一级包");

		Storage storage = new Storage();
		storage.setUserName(user.getName());
		storage.setTreasureName("一级包");
		storage.setNumber(2);
		storageRepository.save(storage);
		Storage storage1 = new Storage();
		storage1.setUserName(user.getName());
		storage1.setTreasureName("UZI");
		storage1.setNumber(2);
		storageRepository.save(storage1);
		Storage storage2 = new Storage();
		storage2.setUserName(user.getName());
		storage2.setTreasureName("一级甲");
		storage2.setNumber(1);
		storageRepository.save(storage2);

//		WearTreasure wearTreasure = new WearTreasure();
//		wearTreasure.setUserName(user.getName());
//		wearTreasure.setTreasureName("一级包");
//		wearTreasure.setNumber(1);
//		wearTreasureRepository.save(wearTreasure);
//		WearTreasure wearTreasure1 = userService.wearTreasure(user.getName(),"一级包");

//		userService.wearTreasure(user.getName(),"一级包");
//		userService.wearTreasure(user.getName(),"一级包");
//		userService.underWearTreasure(user.getName(),"一级包");
//		userService.wearTreasure(user.getName(),"UZI");
//		userService.wearTreasure(user.getName(),"UZI");
//		userService.wearTreasure(user.getName(),"一级甲");
	}

	@Test
	public void underWearTreasureTest(){
		//用户名不存在
		userService.underWearTreasure("123","一级包");
		//宝物不在存储箱
		User user = userService.addOneUser("123");
		userService.underWearTreasure(user.getName(),"一级包");

		Storage storage = new Storage();
		storage.setUserName(user.getName());
		storage.setTreasureName("一级包");
		storage.setNumber(2);
		storageRepository.save(storage);
		Storage storage1 = new Storage();
		storage1.setUserName(user.getName());
		storage1.setTreasureName("UZI");
		storage1.setNumber(2);
		storageRepository.save(storage1);
		Storage storage2 = new Storage();
		storage2.setUserName(user.getName());
		storage2.setTreasureName("一级甲");
		storage2.setNumber(1);
		storageRepository.save(storage2);

		WearTreasure wearTreasure = new WearTreasure();
		wearTreasure.setUserName(user.getName());
		wearTreasure.setTreasureName("UZI");
		wearTreasure.setNumber(1);
		wearTreasureRepository.save(wearTreasure);
		WearTreasure wearTreasure1 = new WearTreasure();
		wearTreasure1.setUserName(user.getName());
		wearTreasure1.setTreasureName("一级包");
		wearTreasure1.setNumber(1);
		wearTreasureRepository.save(wearTreasure1);
//		WearTreasure wearTreasure1 = userService.wearTreasure(user.getName(),"一级包");

//		userService.underWearTreasure(user.getName(),"一级包");
//		userService.underWearTreasure(user.getName(),"一级包");
//		userService.underWearTreasure(user.getName(),"UZI");
//		userService.wearTreasure(user.getName(),"一级包");

//		userService.wearTreasure(user.getName(),"UZI");
//		userService.wearTreasure(user.getName(),"一级甲");
	}

	@Test
	public void sellTreasureTest(){
		User user = userService.addOneUser("123");
		userService.sellTreasure(user.getName(),"一级包",1);

		Storage storage1 = new Storage();
		storage1.setUserName(user.getName());
		storage1.setTreasureName("UZI");
		storage1.setNumber(2);
		storageRepository.save(storage1);

		userService.sellTreasure(user.getName(),storage1.getTreasureName(),10);
		userService.sellTreasure(user.getName(),storage1.getTreasureName(),10);
	}

	@Test
	public void cancelTreasureTest(){
		User user = userService.addOneUser("123");
		userService.cancelSellTreasure(user.getName(),"一级包",1);

		Storage storage1 = new Storage();
		storage1.setUserName(user.getName());
		storage1.setTreasureName("UZI");
		storage1.setNumber(2);
		storageRepository.save(storage1);

		Market market = new Market();
		market.setUserName(user.getName());
		market.setTreasureName("UZI");
		market.setPrice(10);
		market.setNumber(2);
		marketRepository.save(market);

		userService.cancelSellTreasure(user.getName(),"UZI",10);
		userService.cancelSellTreasure(user.getName(),"UZI",10);
	}

	@Test
	public void buyTreasureTest(){
		User user = userService.addOneUser("123");
		User buyer = userService.addOneUser("123");
		buyer.setBalance(100);


		User buyer1 = userService.addOneUser("123");
		buyer1.setBalance(0);
		userRepository.save(buyer1);
		marketRepository.deleteAll();
		userService.buyTreasure(user.getName(),"123","一级包",10);

		Storage storage1 = new Storage();
		storage1.setUserName(user.getName());
		storage1.setTreasureName("UZI");
		storage1.setNumber(3);
		storageRepository.save(storage1);
		Storage storage2 = new Storage();
		storage2.setUserName(user.getName());
		storage2.setTreasureName("一级包");
		storage2.setNumber(1);
		storageRepository.save(storage2);

		Market market = new Market();
		market.setUserName(user.getName());
		market.setTreasureName("UZI");
		market.setPrice(10);
		market.setNumber(3);
		Market market1 = new Market();
		market1.setUserName(user.getName());
		market1.setTreasureName("一级包");
		market1.setPrice(10);
		market1.setNumber(1);
		marketRepository.save(market);
		userService.buyTreasure(user.getName(),user.getName(),"UZI",10);
		userService.buyTreasure(buyer1.getName(),user.getName(),"UZI",10);
//		userService.buyTreasure(buyer.getName(),user.getName(),"UZI",10);

		for(int i=0;i<15;i++){
			gameService.treasureHunt(buyer.getName());
		}
		userService.buyTreasure(buyer.getName(),user.getName(),"UZI",10);
		userService.buyTreasure(buyer.getName(),user.getName(),"一级包",10);
	}
}
