package com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.entities.BodyTable;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.entities.DesignTable;


@Dao
public interface DesignDao {


    @Insert
    public void insertDesign(DesignTable bodyTable);

    @Update
    public void upateDesign(BodyTable bodyTable);

    @Delete
    public void deleteDesign(BodyTable  bodyTable);
}
