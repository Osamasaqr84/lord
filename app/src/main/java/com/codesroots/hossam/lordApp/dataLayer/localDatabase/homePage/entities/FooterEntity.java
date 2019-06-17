package com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.entities;

import android.arch.persistence.room.*;

@Entity(tableName = "FooterTable")
public class FooterEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "border")
    private String border ;

    @ColumnInfo(name = "shadow")
    private String shadow  ;

    @ColumnInfo(name = "background")
    private String background;

    @ColumnInfo(name = "first_icon")
    private String first_icon;

    @ColumnInfo(name = "first_label")
    private String first_label;

    @ColumnInfo(name = "second_icon")
    private String second_icon;

    @ColumnInfo(name = "second_label")
    private String second_label;

    @ColumnInfo(name = "third_icon")
    private String third_icon;


    @ColumnInfo(name = "third_label")
    private String third_label;

    @ColumnInfo(name = "forth_icon")
    private String forth_icon ;

    @ColumnInfo(name = "forth_label")
    private String forth_label ;


    @ColumnInfo(name = "fifth_icon")
    private String fifth_icon  ;

    @ColumnInfo(name = "fifth_label")
    private String fifth_label ;


    @ColumnInfo(name = "template_id")
    private int template_id;

    @ColumnInfo(name = "font_color")
    private String font_color;

    public String getBorder() {
        return border;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public String getShadow() {
        return shadow;
    }

    public void setShadow(String shadow) {
        this.shadow = shadow;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getFirst_icon() {
        return first_icon;
    }

    public void setFirst_icon(String first_icon) {
        this.first_icon = first_icon;
    }

    public String getFirst_label() {
        return first_label;
    }

    public void setFirst_label(String first_label) {
        this.first_label = first_label;
    }

    public String getSecond_icon() {
        return second_icon;
    }

    public void setSecond_icon(String second_icon) {
        this.second_icon = second_icon;
    }

    public String getSecond_label() {
        return second_label;
    }

    public void setSecond_label(String second_label) {
        this.second_label = second_label;
    }

    public String getThird_icon() {
        return third_icon;
    }

    public void setThird_icon(String third_icon) {
        this.third_icon = third_icon;
    }

    public String getThird_label() {
        return third_label;
    }

    public void setThird_label(String third_label) {
        this.third_label = third_label;
    }

    public String getForth_icon() {
        return forth_icon;
    }

    public void setForth_icon(String forth_icon) {
        this.forth_icon = forth_icon;
    }

    public String getForth_label() {
        return forth_label;
    }

    public void setForth_label(String forth_label) {
        this.forth_label = forth_label;
    }

    public String getFifth_icon() {
        return fifth_icon;
    }

    public void setFifth_icon(String fifth_icon) {
        this.fifth_icon = fifth_icon;
    }

    public String getFifth_label() {
        return fifth_label;
    }

    public void setFifth_label(String fifth_label) {
        this.fifth_label = fifth_label;
    }

    public int getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(int template_id) {
        this.template_id = template_id;
    }

    public String getFont_color() {
        return font_color;
    }

    public void setFont_color(String font_color) {
        this.font_color = font_color;
    }
}
