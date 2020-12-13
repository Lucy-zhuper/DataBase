package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "markets")
public class Market {

    @Id
    @GeneratedValue
    @Column(name = "market_id")
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "user_name")
    private String userName;

    @Column(name = "treasure_name")
    private String treasureName;

    @Column(name = "price")
    private int price;

    @Column(name = "number")
    private int number;

    public Market(){}

    public Market(String userName, String treasureName, int price){
        this.userName = userName;
        this.treasureName = treasureName;
        this.price=price;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTreasureName() {
        return treasureName;
    }

    public void setTreasureName(String treasureName) {
        this.treasureName = treasureName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
