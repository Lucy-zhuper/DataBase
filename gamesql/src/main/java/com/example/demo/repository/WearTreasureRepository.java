package com.example.demo.repository;

import com.example.demo.entity.WearTreasure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WearTreasureRepository extends JpaRepository<WearTreasure, Long> {
    WearTreasure findByUserNameAndTreasureName(String userName, String treasureName);
    List<WearTreasure> findByUserName(String userName);
}
