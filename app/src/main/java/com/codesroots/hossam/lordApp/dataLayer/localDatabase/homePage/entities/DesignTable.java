package com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.entities;

import android.arch.persistence.room.*;
import java.io.Serializable;

@Entity(tableName = "DesignTable"
//        foreignKeys = {
//                @ForeignKey(entity = HeaderTable.class, parentColumns = "id", childColumns = "header_id",onDelete = CASCADE),
//                @ForeignKey(entity = BodyTable.class, parentColumns = "id", childColumns = "body_id",onDelete = CASCADE),
//                @ForeignKey(entity = FooterTable.class, parentColumns = "id", childColumns = "footer_id",onDelete = CASCADE)
//        }
    )

public class DesignTable implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "header_id")
    private int header_id;

    @ColumnInfo(name = "body_id")
    private int body_id;

    @ColumnInfo(name = "footer_id")
    private int footer_id;

    public int getHeader_id() {
        return header_id;
    }

    public void setHeader_id(int header_id) {
        this.header_id = header_id;
    }

    public int getBody_id() {
        return body_id;
    }

    public void setBody_id(int body_id) {
        this.body_id = body_id;
    }

    public int getFooter_id() {
        return footer_id;
    }

    public void setFooter_id(int footer_id) {
        this.footer_id = footer_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
