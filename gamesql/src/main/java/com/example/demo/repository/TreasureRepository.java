package com.example.demo.repository;

import com.example.demo.entity.Treasure;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TreasureRepository extends JpaRepository<Treasure, Long> {
    Treasure findByValueAndAttribute(int value, String attribute);
    Treasure findByTreasureName(String treasureName);
}
