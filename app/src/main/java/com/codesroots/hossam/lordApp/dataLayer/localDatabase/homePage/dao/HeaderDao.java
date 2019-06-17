package com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.entities.BodyTable;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.entities.HeaderTable;


@Dao
public interface HeaderDao {

    @Insert
    public void insertHeader(HeaderTable headerTable);


    @Update
    public void upateHeader(BodyTable bodyTable);

    @Delete
    public void deleteHeader(BodyTable  bodyTable);

    @Query("select * from HeaderTable where id = 1")
     LiveData<HeaderTable>  getHeader();
}
