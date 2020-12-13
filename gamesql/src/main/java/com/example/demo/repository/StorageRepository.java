package com.example.demo.repository;

import com.example.demo.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage, Long> {

    Storage findByUserNameAndTreasureName(String userName, String treasureName);
    List<Storage> findByUserName(String userName);
}
