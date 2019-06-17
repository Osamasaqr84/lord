package com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.entities.BodyTable;


@Dao
public interface BodyDao {


    @Insert
    public void insertBody(BodyTable bodyTable);

    @Update
    public void upateBody(BodyTable  bodyTable);

    @Delete
    public void deleteBody(BodyTable  bodyTable);

}
