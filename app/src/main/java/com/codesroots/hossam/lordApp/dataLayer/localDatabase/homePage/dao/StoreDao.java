package com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.entities.BodyTable;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.entities.StoreTable;


@Dao
public interface StoreDao {

    @Insert
    public void insertStore(StoreTable storeTable);


    @Update
    public void upateStore(BodyTable bodyTable);

    @Delete
    public void deleteStore(BodyTable  bodyTable);
}
