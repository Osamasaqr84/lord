package com.codesroots.hossam.lordApp.entities;

public class Menuitem {

    String title;
    int imageResorce;

    public Menuitem(String title, int imageResorce) {
        this.title = title;
        this.imageResorce = imageResorce;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageResorce() {
        return imageResorce;
    }

    public void setImageResorce(int imageResorce) {
        this.imageResorce = imageResorce;
    }
}
