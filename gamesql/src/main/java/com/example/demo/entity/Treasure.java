package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "Treasures")
public class Treasure {

//    @Id
//    @GeneratedValue(generator="system-uuid")
//    @GenericGenerator(name="system-uuid", strategy = "uuid")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)

//    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    private Long id;
//    @GeneratedValue(generator="system-uuid")
//    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Id
    @GeneratedValue
    @Column(name = "treasure_id")
Long id;

    @Column(name = "treasure_name")
    private String treasureName;

    @Column(name = "value")
    private Integer value;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_name")
//    @Column(name = "user_name")
//    private String userName;

    @Column(name = "attribute")
    private String attribute;

    public Treasure(){

    }
    public Treasure(String treasureName, int value, String attribute){
        this.treasureName = treasureName;
        this.value = value;
        this.attribute = attribute;
    }

    public String getTreasureName() {
        return treasureName;
    }

    public void setTreasureName(String treasureName) {
        this.treasureName = treasureName;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

//    public String getUser() {
//        return userName;
//    }
//
//
//    public void setUser(String user) {
//        this.userName = userName;
//    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
