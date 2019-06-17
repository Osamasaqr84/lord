package com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment;

import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codesroots.hossam.lordApp.entities.StoreSettingEntity;

public class HeaderFooterViewModel extends ViewModel {

    private String footer_firstlabel,footer_secondlabel,footer_thirdlabel,footer_fourthlabel,footer_fifthlabel; //// textviews in footer
    private  String footer_firstimg,footer_secondimg,footer_thirdimg,footer_fourthimg,footer_fifthimg;        //// imagesviews in footer
    private String header_leftimg,header_rightimg;     //// imagesviews in header

     String headerbackground,footerbackground,bodybackground,footertextcolor;


    public  void init(StoreSettingEntity.DataBean.StoresettingsBean.DesignBean.HeaderBean header, StoreSettingEntity.DataBean.StoresettingsBean.DesignBean.FooterBean footer){

    header_leftimg = "http://parashot.codesroots.com/library/default/"+header.getLeft_icon();
    header_rightimg = "http://parashot.codesroots.com/library/default/"+ header.getRight_icon();

    ///////footer
        footer_firstimg = "http://parashot.codesroots.com/library/default/"+footer.getFirst_icon();
        footer_secondimg = "http://parashot.codesroots.com/library/default/"+footer.getSecond_icon();
        footer_thirdimg = "http://parashot.codesroots.com/library/default/"+footer.getThird_icon();
        footer_fourthimg = "http://parashot.codesroots.com/library/default/"+footer.getForth_icon();
        footer_fifthimg = "http://parashot.codesroots.com/library/default/"+footer.getFifth_icon();

        footer_firstlabel = footer.getFirst_label();
        footer_secondlabel = footer.getSecond_label();
        footer_thirdlabel = footer.getThird_label();
        footer_fourthlabel = footer.getForth_label();
        footer_fifthlabel = footer.getFifth_label();
        headerbackground = header.getBackground();

        footertextcolor = footer.getFont_color();
    }

    ////// to glid img
    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }


    @BindingAdapter("bind:background")
    public static void setback(View view, String backgroundcolor) {
        if (backgroundcolor!=null)
        view.setBackgroundColor(Color.parseColor(backgroundcolor));
    }

    @BindingAdapter("bind:textcolorvalue")
    public static void settextt(TextView view, String backgroundcolor) {
        if (backgroundcolor!=null)
        view.setTextColor(Color.parseColor(backgroundcolor));
    }


    public String getBodybackground() {
        return bodybackground;
    }

    public void setBodybackground(String bodybackground) {
        this.bodybackground = bodybackground;
    }

    public  String getHeaderbackground() {
        return headerbackground;
    }

    public  void setHeaderbackground(String headerbackground) {
        this.headerbackground = headerbackground;
    }

    public String getFooterbackground() {
        return footerbackground;
    }

    public void setFooterbackground(String footerbackground) {
        this.footerbackground = footerbackground;
    }

    //////// geter and seter
    public String getFooter_firstlabel() {
        return footer_firstlabel;
    }

    public String getFootertextcolor() {
        return footertextcolor;
    }

    public void setFootertextcolor(String footertextcolor) {
        this.footertextcolor = footertextcolor;
    }

    public void setFooter_firstlabel(String footer_firstlabel) {
        this.footer_firstlabel = footer_firstlabel;
    }

    public String getFooter_secondlabel() {
        return footer_secondlabel;
    }

    public void setFooter_secondlabel(String footer_secondlabel) {
        this.footer_secondlabel = footer_secondlabel;
    }

    public String getFooter_thirdlabel() {
        return footer_thirdlabel;
    }

    public void setFooter_thirdlabel(String footer_thirdlabel) {
        this.footer_thirdlabel = footer_thirdlabel;
    }

    public String getFooter_fourthlabel() {
        return footer_fourthlabel;
    }

    public void setFooter_fourthlabel(String footer_fourthlabel) {
        this.footer_fourthlabel = footer_fourthlabel;
    }

    public String getFooter_fifthlabel() {
        return footer_fifthlabel;
    }

    public void setFooter_fifthlabel(String footer_fifthlabel) {
        this.footer_fifthlabel = footer_fifthlabel;
    }

    public String getFooter_firstimg() {
        return footer_firstimg;
    }

    public void setFooter_firstimg(String footer_firstimg) {
        this.footer_firstimg = footer_firstimg;
    }

    public String getFooter_secondimg() {
        return footer_secondimg;
    }

    public void setFooter_secondimg(String footer_secondimg) {
        this.footer_secondimg = footer_secondimg;
    }

    public String getFooter_thirdimg() {
        return footer_thirdimg;
    }

    public void setFooter_thirdimg(String footer_thirdimg) {
        this.footer_thirdimg = footer_thirdimg;
    }

    public String getFooter_fourthimg() {
        return footer_fourthimg;
    }

    public void setFooter_fourthimg(String footer_fourthimg) {
        this.footer_fourthimg = footer_fourthimg;
    }

    public String getFooter_fifthimg() {
        return footer_fifthimg;
    }

    public void setFooter_fifthimg(String footer_fifthimg) {
        this.footer_fifthimg = footer_fifthimg;
    }

    public String getHeader_leftimg() {
        return header_leftimg;
    }

    public void setHeader_leftimg(String header_leftimg) {
        this.header_leftimg = header_leftimg;
    }

    public String getHeader_rightimg() {
        return header_rightimg;
    }

    public void setHeader_rightimg(String header_rightimg) {
        this.header_rightimg = header_rightimg;
    }
}
