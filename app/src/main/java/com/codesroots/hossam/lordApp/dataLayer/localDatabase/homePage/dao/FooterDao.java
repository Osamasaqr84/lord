package com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.entities.BodyTable;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.entities.FooterEntity;

@Dao
public interface FooterDao {


    @Insert
    public void insertFooter(FooterEntity bodyTable);



    @Update
    public void upateFooter(BodyTable bodyTable);

    @Delete
    public void deleteFooter(BodyTable  bodyTable);
}
