package com.example.demo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String pwd;

    @Column(name = "balance")
    private Integer balance;

    @Column(name = "ability")
    private Integer ability;   //能力值，根据装备工具计算得出

    @Column(name = "luck")
    private Integer luck;      //运气值，根据佩戴配饰计算得出

    public User(){

    }
    public User(String name, String password){
        this.userName = name;
        this.pwd = password;
    }
    public String getName() {
        return userName;
    }

    public void setName(String name) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getAbility() {
        return ability;
    }

    public void setAbility(Integer ability) {
        this.ability=ability;
    }

    public Integer getLuck() {
        return luck;
    }

    public void setLuck(Integer luck) {
        this.luck = luck;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("City{");
        sb.append("name=").append(userName);
        sb.append(", password='").append(pwd);
        sb.append(", balance=").append(balance);
        sb.append(", ability=").append(balance);
        sb.append('}');
        return sb.toString();
    }
}
