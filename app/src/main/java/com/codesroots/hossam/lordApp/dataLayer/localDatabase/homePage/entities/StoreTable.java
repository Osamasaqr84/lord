package com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "StoreTable")
public class StoreTable implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;


    @ColumnInfo(name = "bank_accounts")
    private String bank_accounts;

    @ColumnInfo(name = "phone")
    private String phone;


    @ColumnInfo(name = "alternative_phone")
    private String alternative_phone;

    @ColumnInfo(name = "commercial_register_photo")
    private String commercial_register_photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBank_accounts() {
        return bank_accounts;
    }

    public void setBank_accounts(String bank_accounts) {
        this.bank_accounts = bank_accounts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAlternative_phone() {
        return alternative_phone;
    }

    public void setAlternative_phone(String alternative_phone) {
        this.alternative_phone = alternative_phone;
    }

    public String getCommercial_register_photo() {
        return commercial_register_photo;
    }

    public void setCommercial_register_photo(String commercial_register_photo) {
        this.commercial_register_photo = commercial_register_photo;
    }
}
