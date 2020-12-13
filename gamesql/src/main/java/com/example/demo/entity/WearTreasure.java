package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "wear_treasure")
public class WearTreasure {

    @Id
    @GeneratedValue
    private Long id;

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

    @Column(name = "number")
    private int number;

    public  WearTreasure(){

    }
    public WearTreasure(String userName, String treasureName){
        this.userName = userName;
        this.treasureName = treasureName;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
