package com.example.moneymanager;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ModelDAO {
    @Query("SELECT * FROM giao_dich")
    List<Model> getAll();

    @Query("SELECT * FROM giao_dich WHERE uid IN (:userIds)")
    List<Model> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM giao_dich WHERE ten_giao_dich LIKE :ten LIMIT 1")
    Model findByName(String ten);

    @Insert
    void insertAll(Model... models);

    @Delete
    void delete(Model model);
}
