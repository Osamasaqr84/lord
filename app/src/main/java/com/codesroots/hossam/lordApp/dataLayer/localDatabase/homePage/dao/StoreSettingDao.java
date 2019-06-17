package com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.entities.StoreSettingTable;


@Dao
public interface StoreSettingDao {

    @Insert
    public void insertStore(StoreSettingTable settingTable);


    @Update
    public void upateStore(StoreSettingTable settingTable);


    @Delete
    public void deleteStore(StoreSettingTable settingTable);



}
