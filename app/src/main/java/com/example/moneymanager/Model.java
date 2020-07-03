package com.example.moneymanager;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "giao_dich")
public class Model {
    @PrimaryKey (autoGenerate = true)
    private int uid;
    @ColumnInfo (name = "ten_giao_dich")
    private String ten_giao_dich;
    @ColumnInfo (name = "so_tien")
    private int so_tien;
    @ColumnInfo (name = "ghi_chu")
    private String ghi_chu;

    public Model() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTen_giao_dich() {
        return ten_giao_dich;
    }

    public void setTen_giao_dich(String ten_giao_dich) {
        this.ten_giao_dich = ten_giao_dich;
    }

    public int getSo_tien() {
        return so_tien;
    }

    public void setSo_tien(int so_tien) {
        this.so_tien = so_tien;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }
}
