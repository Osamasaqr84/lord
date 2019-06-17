package com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "StoreSettingTable")
public class StoreSettingTable {

    @PrimaryKey(autoGenerate = true)
    private int id;


    @ColumnInfo(name = "payment_id")
    private int payment_id;


    @ColumnInfo(name = "design_id ")
    private int design_id ;

    @ColumnInfo(name = "font")
    private int font;

    @ColumnInfo(name = "first_splash")
    private int first_splash ;

    @ColumnInfo(name = "second_splash ")
    private int second_splash  ;

    @ColumnInfo(name = "third_splash ")
    private int third_splash ;

    @ColumnInfo(name = "forth_splash")
    private int forth_splash;


    @ColumnInfo(name = "longitude")
    private int longitude;

    @ColumnInfo(name = "latitude")
    private int latitude;


    @ColumnInfo(name = "logo")
    private int logo;

    @ColumnInfo(name = "open_time ")
    private int open_time ;

    @ColumnInfo(name = "close_time  ")
    private int close_time  ;


    @ColumnInfo(name = "update")
    private int update ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public int getDesign_id() {
        return design_id;
    }

    public void setDesign_id(int design_id) {
        this.design_id = design_id;
    }

    public int getFont() {
        return font;
    }

    public void setFont(int font) {
        this.font = font;
    }

    public int getFirst_splash() {
        return first_splash;
    }

    public void setFirst_splash(int first_splash) {
        this.first_splash = first_splash;
    }

    public int getSecond_splash() {
        return second_splash;
    }

    public void setSecond_splash(int second_splash) {
        this.second_splash = second_splash;
    }

    public int getThird_splash() {
        return third_splash;
    }

    public void setThird_splash(int third_splash) {
        this.third_splash = third_splash;
    }

    public int getForth_splash() {
        return forth_splash;
    }

    public void setForth_splash(int forth_splash) {
        this.forth_splash = forth_splash;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public int getOpen_time() {
        return open_time;
    }

    public void setOpen_time(int open_time) {
        this.open_time = open_time;
    }

    public int getClose_time() {
        return close_time;
    }

    public void setClose_time(int close_time) {
        this.close_time = close_time;
    }

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }
}
