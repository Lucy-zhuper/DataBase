package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name="MyStorage")
public class Storage {

    @Id
    @GeneratedValue
    @Column(name = "storage_id")
    Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "treasure_name")
    private String treasureName;

    @Column(name = "number")
    private int number;

    public Storage(){

    }
    public Storage(String userName, String treasureName){
        this.userName = userName;
        this.treasureName = treasureName;
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
}
