package com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.entities;

import android.arch.persistence.room.*;

import java.io.Serializable;

@Entity(tableName = "BodyTable")

public class BodyTable implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

     @ColumnInfo(name = "background")
    private String background;

     @ColumnInfo(name = "first_label")
    private String first_label;

     @ColumnInfo(name = "second_label")
     private String second_label;

     @ColumnInfo(name = "third_label")
     private String third_label;

     @ColumnInfo(name = "categorybackground")
     private String categorybackground;

     @ColumnInfo(name = "font_color")
     private String font_color;

     @ColumnInfo(name = "template_id")
    private int template_id;

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getFirst_label() {
        return first_label;
    }

    public void setFirst_label(String first_label) {
        this.first_label = first_label;
    }

    public String getSecond_label() {
        return second_label;
    }

    public void setSecond_label(String second_label) {
        this.second_label = second_label;
    }

    public String getThird_label() {
        return third_label;
    }

    public void setThird_label(String third_label) {
        this.third_label = third_label;
    }

    public String getCategorybackground() {
        return categorybackground;
    }

    public void setCategorybackground(String categorybackground) {
        this.categorybackground = categorybackground;
    }

    public String getFont_color() {
        return font_color;
    }

    public void setFont_color(String font_color) {
        this.font_color = font_color;
    }

    public int getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(int template_id) {
        this.template_id = template_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
