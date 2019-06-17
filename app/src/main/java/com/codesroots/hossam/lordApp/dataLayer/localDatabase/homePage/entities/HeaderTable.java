package com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.entities;

import android.arch.persistence.room.*;
import java.io.Serializable;



@Entity(tableName = "HeaderTable")
public class HeaderTable implements Serializable {

    public HeaderTable(String background, String right_icon, String left_icon) {
        this.background = background;
        this.right_icon = right_icon;
        this.left_icon = left_icon;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "background")
    String background;

    @ColumnInfo(name = "right_icon")
    String right_icon;

    @ColumnInfo(name = "left_icon")
    String left_icon;

    @ColumnInfo(name = "template_id")
    private  int template_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getRight_icon() {
        return right_icon;
    }

    public void setRight_icon(String right_icon) {
        this.right_icon = right_icon;
    }

    public String getLeft_icon() {
        return left_icon;
    }

    public void setLeft_icon(String left_icon) {
        this.left_icon = left_icon;
    }

    public int getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(int template_id) {
        this.template_id = template_id;
    }
}
