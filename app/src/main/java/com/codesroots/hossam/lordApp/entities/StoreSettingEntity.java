package com.codesroots.hossam.lordApp.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class StoreSettingEntity implements Serializable {


    private List<DataBean> data;

    protected StoreSettingEntity(Parcel in) {
    }

    public static final Parcelable.Creator<StoreSettingEntity> CREATOR = new Parcelable.Creator<StoreSettingEntity>() {
        @Override
        public StoreSettingEntity createFromParcel(Parcel in) {
            return new StoreSettingEntity(in);
        }

        @Override
        public StoreSettingEntity[] newArray(int size) {
            return new StoreSettingEntity[size];
        }
    };

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }



    public static class DataBean implements Serializable {
        /**
         * id : 171
         * name : ahmed ramzy33
         * bank_accounts : null
         * phone : null
         * alternative_phone : null
         * commercial_register_photo : null
         * commercial_register_number : 0
         * link : null
         * description : null
         * branches : null
         * birthdate : null
         * city_id : null
         * subcat_id : null
         * logodesign_id : null
         * created : 2018-10-07T14:40:59+0000
         * modified : 2018-10-07T14:40:59+0000
         * visible : null
         * user_id : 85
         * design_id : 95
         * template_id : 3
         * storesettings : [{"id":25,"store_id":171,"payment_id":1,"design_id":95,"font":"arial","first_splash":"1537032934741514971.jpg","second_splash":"","third_splash":"","forth_splash":"","longitude":10,"latitude":25,"logo":"1537032935392413716.jpg","open_time":"","close_time":"","update_":"","created":"2018-10-07T00:00:00+0000","modified":"2018-10-07T00:00:00+0000","design":{"Id":95,"header_id":132,"body_id":116,"footer_id":115,"created":"2018-10-07T14:40:58+0000","modified":"2018-10-07T14:40:58+0000","footer":{"id":115,"border":"false","shadow":"false","background":"#ffffff","red":0,"green":0,"blue":0,"first_icon":"15388186611922255985.png","first_label":"الرئيسيه","second_icon":"1538818661553888991.png","second_label":"طلباتي","third_icon":"15388186612053260190.png","third_label":"عروض","forth_icon":"1538818661253716771.png","forth_label":"اشعارات","fifth_icon":"1538818661177352465.png","fifth_label":"المزيد","template_id":3,"created":"2018-10-07T14:40:58+0000","modified":"2018-10-07T14:40:58+0000","font_color":"#686868","font_red":0,"font_green":0,"font_blue":0},"body":{"id":116,"background":"#a6eaed","first_label":"اختر حسب القسم","second_label":"تصفح الافضل تقيما","third_label":"تصفح حسب الفئه","template_id":"3","categorybackground":"#ffffff","font_color":"","created":"2018-10-07T14:40:58+0000","modified":"2018-10-07T14:40:58+0000","red":0,"green":0,"blue":0,"category_red":0,"category_green":0,"category_blue":0,"font_red":0,"font_green":0,"font_blue":0},"header":{"id":132,"background":"#67236c","red":0,"green":0,"blue":0,"logo":"15371356551079414738.jpg","right_icon":"1538817047785890060.png","left_icon":"15388170471708638659.png","template_id":3,"created":"2018-10-07T14:40:58+0000","modified":"2018-10-07T14:40:58+0000"},"sliders":[]}}]
         */

        private int id;
        private String name;
        private Object bank_accounts;
        private Object phone;
        private Object alternative_phone;
        private Object commercial_register_photo;
        private int commercial_register_number;
        private Object link;
        private Object description;
        private Object branches;
        private Object birthdate;
        private Object city_id;
        private Object subcat_id;
        private Object logodesign_id;
        private String created;
        private String modified;
        private Object visible;
        private int user_id;
        private int design_id;
        private int template_id;
        private List<StoresettingsBean> storesettings;

        protected DataBean(Parcel in) {
            id = in.readInt();
            name = in.readString();
            commercial_register_number = in.readInt();
            created = in.readString();
            modified = in.readString();
            user_id = in.readInt();
            design_id = in.readInt();
            template_id = in.readInt();
        }


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

        public Object getBank_accounts() {
            return bank_accounts;
        }

        public void setBank_accounts(Object bank_accounts) {
            this.bank_accounts = bank_accounts;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public Object getAlternative_phone() {
            return alternative_phone;
        }

        public void setAlternative_phone(Object alternative_phone) {
            this.alternative_phone = alternative_phone;
        }

        public Object getCommercial_register_photo() {
            return commercial_register_photo;
        }

        public void setCommercial_register_photo(Object commercial_register_photo) {
            this.commercial_register_photo = commercial_register_photo;
        }

        public int getCommercial_register_number() {
            return commercial_register_number;
        }

        public void setCommercial_register_number(int commercial_register_number) {
            this.commercial_register_number = commercial_register_number;
        }

        public Object getLink() {
            return link;
        }

        public void setLink(Object link) {
            this.link = link;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public Object getBranches() {
            return branches;
        }

        public void setBranches(Object branches) {
            this.branches = branches;
        }

        public Object getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(Object birthdate) {
            this.birthdate = birthdate;
        }

        public Object getCity_id() {
            return city_id;
        }

        public void setCity_id(Object city_id) {
            this.city_id = city_id;
        }

        public Object getSubcat_id() {
            return subcat_id;
        }

        public void setSubcat_id(Object subcat_id) {
            this.subcat_id = subcat_id;
        }

        public Object getLogodesign_id() {
            return logodesign_id;
        }

        public void setLogodesign_id(Object logodesign_id) {
            this.logodesign_id = logodesign_id;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public Object getVisible() {
            return visible;
        }

        public void setVisible(Object visible) {
            this.visible = visible;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getDesign_id() {
            return design_id;
        }

        public void setDesign_id(int design_id) {
            this.design_id = design_id;
        }

        public int getTemplate_id() {
            return template_id;
        }

        public void setTemplate_id(int template_id) {
            this.template_id = template_id;
        }

        public List<StoresettingsBean> getStoresettings() {
            return storesettings;
        }

        public void setStoresettings(List<StoresettingsBean> storesettings) {
            this.storesettings = storesettings;
        }


        public static class StoresettingsBean implements Serializable {
            /**
             * id : 25
             * store_id : 171
             * payment_id : 1
             * design_id : 95
             * font : arial
             * first_splash : 1537032934741514971.jpg
             * second_splash :
             * third_splash :
             * forth_splash :
             * longitude : 10
             * latitude : 25
             * logo : 1537032935392413716.jpg
             * open_time :
             * close_time :
             * update_ :
             * created : 2018-10-07T00:00:00+0000
             * modified : 2018-10-07T00:00:00+0000
             * design : {"Id":95,"header_id":132,"body_id":116,"footer_id":115,"created":"2018-10-07T14:40:58+0000","modified":"2018-10-07T14:40:58+0000","footer":{"id":115,"border":"false","shadow":"false","background":"#ffffff","red":0,"green":0,"blue":0,"first_icon":"15388186611922255985.png","first_label":"الرئيسيه","second_icon":"1538818661553888991.png","second_label":"طلباتي","third_icon":"15388186612053260190.png","third_label":"عروض","forth_icon":"1538818661253716771.png","forth_label":"اشعارات","fifth_icon":"1538818661177352465.png","fifth_label":"المزيد","template_id":3,"created":"2018-10-07T14:40:58+0000","modified":"2018-10-07T14:40:58+0000","font_color":"#686868","font_red":0,"font_green":0,"font_blue":0},"body":{"id":116,"background":"#a6eaed","first_label":"اختر حسب القسم","second_label":"تصفح الافضل تقيما","third_label":"تصفح حسب الفئه","template_id":"3","categorybackground":"#ffffff","font_color":"","created":"2018-10-07T14:40:58+0000","modified":"2018-10-07T14:40:58+0000","red":0,"green":0,"blue":0,"category_red":0,"category_green":0,"category_blue":0,"font_red":0,"font_green":0,"font_blue":0},"header":{"id":132,"background":"#67236c","red":0,"green":0,"blue":0,"logo":"15371356551079414738.jpg","right_icon":"1538817047785890060.png","left_icon":"15388170471708638659.png","template_id":3,"created":"2018-10-07T14:40:58+0000","modified":"2018-10-07T14:40:58+0000"},"sliders":[]}
             */

            private int id;
            private int store_id;
            private int payment_id;
            private int design_id;
            private String font;
            private String first_splash;
            private String second_splash;
            private String third_splash;
            private String forth_splash;
            private int longitude;
            private int latitude;
            private String logo;
            private String open_time;
            private String close_time;
            private String update_;
            private String created;
            private String modified;
            private DesignBean design;

            protected StoresettingsBean(Parcel in) {
                id = in.readInt();
                store_id = in.readInt();
                payment_id = in.readInt();
                design_id = in.readInt();
                font = in.readString();
                first_splash = in.readString();
                second_splash = in.readString();
                third_splash = in.readString();
                forth_splash = in.readString();
                longitude = in.readInt();
                latitude = in.readInt();
                logo = in.readString();
                open_time = in.readString();
                close_time = in.readString();
                update_ = in.readString();
                created = in.readString();
                modified = in.readString();
            }


            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
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

            public String getFont() {
                return font;
            }

            public void setFont(String font) {
                this.font = font;
            }

            public String getFirst_splash() {
                return first_splash;
            }

            public void setFirst_splash(String first_splash) {
                this.first_splash = first_splash;
            }

            public String getSecond_splash() {
                return second_splash;
            }

            public void setSecond_splash(String second_splash) {
                this.second_splash = second_splash;
            }

            public String getThird_splash() {
                return third_splash;
            }

            public void setThird_splash(String third_splash) {
                this.third_splash = third_splash;
            }

            public String getForth_splash() {
                return forth_splash;
            }

            public void setForth_splash(String forth_splash) {
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

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getOpen_time() {
                return open_time;
            }

            public void setOpen_time(String open_time) {
                this.open_time = open_time;
            }

            public String getClose_time() {
                return close_time;
            }

            public void setClose_time(String close_time) {
                this.close_time = close_time;
            }

            public String getUpdate_() {
                return update_;
            }

            public void setUpdate_(String update_) {
                this.update_ = update_;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public String getModified() {
                return modified;
            }

            public void setModified(String modified) {
                this.modified = modified;
            }

            public DesignBean getDesign() {
                return design;
            }

            public void setDesign(DesignBean design) {
                this.design = design;
            }

            public static class DesignBean implements Serializable {
                /**
                 * Id : 95
                 * header_id : 132
                 * body_id : 116
                 * footer_id : 115
                 * created : 2018-10-07T14:40:58+0000
                 * modified : 2018-10-07T14:40:58+0000
                 * footer : {"id":115,"border":"false","shadow":"false","background":"#ffffff","red":0,"green":0,"blue":0,"first_icon":"15388186611922255985.png","first_label":"الرئيسيه","second_icon":"1538818661553888991.png","second_label":"طلباتي","third_icon":"15388186612053260190.png","third_label":"عروض","forth_icon":"1538818661253716771.png","forth_label":"اشعارات","fifth_icon":"1538818661177352465.png","fifth_label":"المزيد","template_id":3,"created":"2018-10-07T14:40:58+0000","modified":"2018-10-07T14:40:58+0000","font_color":"#686868","font_red":0,"font_green":0,"font_blue":0}
                 * body : {"id":116,"background":"#a6eaed","first_label":"اختر حسب القسم","second_label":"تصفح الافضل تقيما","third_label":"تصفح حسب الفئه","template_id":"3","categorybackground":"#ffffff","font_color":"","created":"2018-10-07T14:40:58+0000","modified":"2018-10-07T14:40:58+0000","red":0,"green":0,"blue":0,"category_red":0,"category_green":0,"category_blue":0,"font_red":0,"font_green":0,"font_blue":0}
                 * header : {"id":132,"background":"#67236c","red":0,"green":0,"blue":0,"logo":"15371356551079414738.jpg","right_icon":"1538817047785890060.png","left_icon":"15388170471708638659.png","template_id":3,"created":"2018-10-07T14:40:58+0000","modified":"2018-10-07T14:40:58+0000"}
                 * sliders : []
                 */

                private int Id;
                private int header_id;
                private int body_id;
                private int footer_id;
                private String created;
                private String modified;
                private FooterBean footer;
                private BodyBean body;
                private HeaderBean header;
                private List<Sliders> sliders;

                protected DesignBean(Parcel in) {
                    Id = in.readInt();
                    header_id = in.readInt();
                    body_id = in.readInt();
                    footer_id = in.readInt();
                    created = in.readString();
                    modified = in.readString();
                }



                public int getId() {
                    return Id;
                }

                public void setId(int Id) {
                    this.Id = Id;
                }

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

                public String getCreated() {
                    return created;
                }

                public void setCreated(String created) {
                    this.created = created;
                }

                public String getModified() {
                    return modified;
                }

                public void setModified(String modified) {
                    this.modified = modified;
                }

                public FooterBean getFooter() {
                    return footer;
                }

                public void setFooter(FooterBean footer) {
                    this.footer = footer;
                }

                public BodyBean getBody() {
                    return body;
                }

                public void setBody(BodyBean body) {
                    this.body = body;
                }

                public HeaderBean getHeader() {
                    return header;
                }

                public void setHeader(HeaderBean header) {
                    this.header = header;
                }

                public List<Sliders> getSliders() {
                    return sliders;
                }

                public void setSliders(List<Sliders> sliders) {
                    this.sliders = sliders;
                }


                public static class FooterBean implements Serializable {

                    /**
                     * id : 115
                     * border : false
                     * shadow : false
                     * background : #ffffff
                     * red : 0
                     * green : 0
                     * blue : 0
                     * first_icon : 15388186611922255985.png
                     * first_label : الرئيسيه
                     * second_icon : 1538818661553888991.png
                     * second_label : طلباتي
                     * third_icon : 15388186612053260190.png
                     * third_label : عروض
                     * forth_icon : 1538818661253716771.png
                     * forth_label : اشعارات
                     * fifth_icon : 1538818661177352465.png
                     * fifth_label : المزيد
                     * template_id : 3
                     * created : 2018-10-07T14:40:58+0000
                     * modified : 2018-10-07T14:40:58+0000
                     * font_color : #686868
                     * font_red : 0
                     * font_green : 0
                     * font_blue : 0
                     */

                    private int id;
                    private String border;
                    private String shadow;
                    private String background;
                    private int red;
                    private int green;
                    private int blue;
                    private String first_icon;
                    private String first_label;
                    private String second_icon;
                    private String second_label;
                    private String third_icon;
                    private String third_label;
                    private String forth_icon;
                    private String forth_label;
                    private String fifth_icon;
                    private String fifth_label;
                    private int template_id;
                    private String created;
                    private String modified;
                    private String font_color;
                    private int font_red;
                    private int font_green;
                    private int font_blue;

                    protected FooterBean(Parcel in) {
                        id = in.readInt();
                        border = in.readString();
                        shadow = in.readString();
                        background = in.readString();
                        red = in.readInt();
                        green = in.readInt();
                        blue = in.readInt();
                        first_icon = in.readString();
                        first_label = in.readString();
                        second_icon = in.readString();
                        second_label = in.readString();
                        third_icon = in.readString();
                        third_label = in.readString();
                        forth_icon = in.readString();
                        forth_label = in.readString();
                        fifth_icon = in.readString();
                        fifth_label = in.readString();
                        template_id = in.readInt();
                        created = in.readString();
                        modified = in.readString();
                        font_color = in.readString();
                        font_red = in.readInt();
                        font_green = in.readInt();
                        font_blue = in.readInt();
                    }


                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getBorder() {
                        return border;
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

                    public int getRed() {
                        return red;
                    }

                    public void setRed(int red) {
                        this.red = red;
                    }

                    public int getGreen() {
                        return green;
                    }

                    public void setGreen(int green) {
                        this.green = green;
                    }

                    public int getBlue() {
                        return blue;
                    }

                    public void setBlue(int blue) {
                        this.blue = blue;
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

                    public String getCreated() {
                        return created;
                    }

                    public void setCreated(String created) {
                        this.created = created;
                    }

                    public String getModified() {
                        return modified;
                    }

                    public void setModified(String modified) {
                        this.modified = modified;
                    }

                    public String getFont_color() {
                        return font_color;
                    }

                    public void setFont_color(String font_color) {
                        this.font_color = font_color;
                    }

                    public int getFont_red() {
                        return font_red;
                    }

                    public void setFont_red(int font_red) {
                        this.font_red = font_red;
                    }

                    public int getFont_green() {
                        return font_green;
                    }

                    public void setFont_green(int font_green) {
                        this.font_green = font_green;
                    }

                    public int getFont_blue() {
                        return font_blue;
                    }

                    public void setFont_blue(int font_blue) {
                        this.font_blue = font_blue;
                    }


                }
                public static class Sliders implements Serializable
                {

                    /**
                     * id : 1
                     * photo : Shopclues.jpg
                     * design_id : 57
                     * created : null
                     * modified : null
                     */

                    private int id;
                    private String photo;
                    private String design_id;
                    private Object created;
                    private Object modified;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getPhoto() {
                        return photo;
                    }

                    public void setPhoto(String photo) {
                        this.photo = photo;
                    }

                    public String getDesign_id() {
                        return design_id;
                    }

                    public void setDesign_id(String design_id) {
                        this.design_id = design_id;
                    }

                    public Object getCreated() {
                        return created;
                    }

                    public void setCreated(Object created) {
                        this.created = created;
                    }

                    public Object getModified() {
                        return modified;
                    }

                    public void setModified(Object modified) {
                        this.modified = modified;
                    }
                }
                public static class BodyBean implements Serializable {
                    /**
                     * id : 116
                     * background : #a6eaed
                     * first_label : اختر حسب القسم
                     * second_label : تصفح الافضل تقيما
                     * third_label : تصفح حسب الفئه
                     * template_id : 3
                     * categorybackground : #ffffff
                     * font_color :
                     * created : 2018-10-07T14:40:58+0000
                     * modified : 2018-10-07T14:40:58+0000
                     * red : 0
                     * green : 0
                     * blue : 0
                     * category_red : 0
                     * category_green : 0
                     * category_blue : 0
                     * font_red : 0
                     * font_green : 0
                     * font_blue : 0
                     */

                    private int id;
                    private String background;
                    private String first_label;
                    private String second_label;
                    private String third_label;
                    private String template_id;
                    private String categorybackground;
                    private String font_color;
                    private String created;
                    private String modified;
                    private int red;
                    private int green;
                    private int blue;
                    private int category_red;
                    private int category_green;
                    private int category_blue;
                    private int font_red;
                    private int font_green;
                    private int font_blue;

                    protected BodyBean(Parcel in) {
                        id = in.readInt();
                        background = in.readString();
                        first_label = in.readString();
                        second_label = in.readString();
                        third_label = in.readString();
                        template_id = in.readString();
                        categorybackground = in.readString();
                        font_color = in.readString();
                        created = in.readString();
                        modified = in.readString();
                        red = in.readInt();
                        green = in.readInt();
                        blue = in.readInt();
                        category_red = in.readInt();
                        category_green = in.readInt();
                        category_blue = in.readInt();
                        font_red = in.readInt();
                        font_green = in.readInt();
                        font_blue = in.readInt();
                    }


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

                    public String getTemplate_id() {
                        return template_id;
                    }

                    public void setTemplate_id(String template_id) {
                        this.template_id = template_id;
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

                    public String getCreated() {
                        return created;
                    }

                    public void setCreated(String created) {
                        this.created = created;
                    }

                    public String getModified() {
                        return modified;
                    }

                    public void setModified(String modified) {
                        this.modified = modified;
                    }

                    public int getRed() {
                        return red;
                    }

                    public void setRed(int red) {
                        this.red = red;
                    }

                    public int getGreen() {
                        return green;
                    }

                    public void setGreen(int green) {
                        this.green = green;
                    }

                    public int getBlue() {
                        return blue;
                    }

                    public void setBlue(int blue) {
                        this.blue = blue;
                    }

                    public int getCategory_red() {
                        return category_red;
                    }

                    public void setCategory_red(int category_red) {
                        this.category_red = category_red;
                    }

                    public int getCategory_green() {
                        return category_green;
                    }

                    public void setCategory_green(int category_green) {
                        this.category_green = category_green;
                    }

                    public int getCategory_blue() {
                        return category_blue;
                    }

                    public void setCategory_blue(int category_blue) {
                        this.category_blue = category_blue;
                    }

                    public int getFont_red() {
                        return font_red;
                    }

                    public void setFont_red(int font_red) {
                        this.font_red = font_red;
                    }

                    public int getFont_green() {
                        return font_green;
                    }

                    public void setFont_green(int font_green) {
                        this.font_green = font_green;
                    }

                    public int getFont_blue() {
                        return font_blue;
                    }

                    public void setFont_blue(int font_blue) {
                        this.font_blue = font_blue;
                    }


                }

                public static class HeaderBean implements Serializable {
                    /**
                     * id : 132
                     * background : #67236c
                     * red : 0
                     * green : 0
                     * blue : 0
                     * logo : 15371356551079414738.jpg
                     * right_icon : 1538817047785890060.png
                     * left_icon : 15388170471708638659.png
                     * template_id : 3
                     * created : 2018-10-07T14:40:58+0000
                     * modified : 2018-10-07T14:40:58+0000
                     */

                    private int id;
                    private String background;
                    private int red;
                    private int green;
                    private int blue;
                    private String logo;
                    private String right_icon;
                    private String left_icon;
                    private int template_id;
                    private String created;
                    private String modified;

                    protected HeaderBean(Parcel in) {
                        id = in.readInt();
                        background = in.readString();
                        red = in.readInt();
                        green = in.readInt();
                        blue = in.readInt();
                        logo = in.readString();
                        right_icon = in.readString();
                        left_icon = in.readString();
                        template_id = in.readInt();
                        created = in.readString();
                        modified = in.readString();
                    }



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

                    public int getRed() {
                        return red;
                    }

                    public void setRed(int red) {
                        this.red = red;
                    }

                    public int getGreen() {
                        return green;
                    }

                    public void setGreen(int green) {
                        this.green = green;
                    }

                    public int getBlue() {
                        return blue;
                    }

                    public void setBlue(int blue) {
                        this.blue = blue;
                    }

                    public String getLogo() {
                        return logo;
                    }

                    public void setLogo(String logo) {
                        this.logo = logo;
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

                    public String getCreated() {
                        return created;
                    }

                    public void setCreated(String created) {
                        this.created = created;
                    }

                    public String getModified() {
                        return modified;
                    }

                    public void setModified(String modified) {
                        this.modified = modified;
                    }


                }
            }
        }
    }
}
