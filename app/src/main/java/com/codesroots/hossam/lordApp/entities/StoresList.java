package com.codesroots.hossam.lordApp.entities;

import java.util.List;

public class StoresList {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }



    public static class DataBean {
        /**
         * id : 1
         * name : متجرك
         * bank_accounts : 4545
         * phone : 454545
         * alternative_phone : 787878
         * commercial_register_photo : http://parashot.codesroots.com/library/default/Supreme_pizza@2X.png
         * commercial_register_number : 4545
         * link : dasds
         * description : sss
         * branches : 3
         * birthdate : null
         * city_id : 0
         * subcat_id : 1
         * logodesign_id : 0
         * visible : true
         * user_id : 1
         * template_id : 1
         * category_id : 1
         * logo : http://parashot.codesroots.com/library/default/logo@1X.png
         * created : null
         * modified : null
         * longitude : -73.8248
         * latitude : 40.7324
         * category : {"id":1,"name":"مطاعم","created":"2018-10-08T12:23:51+0000","cover":"http://parashot.codesroots.com/library/default/burger.png","modified":"2018-10-08T12:14:41+0000"}
         * storerates : [{"smallstore_id":1,"stars":3,"count":1,"commentcount":1}]
         * likes : [{"smallstore_id":1,"likes":1,"count":1}]
         */

        private int id;
        private String name;
        private String bank_accounts;
        private String phone;
        private String cover;
        private String address;
        private String alternative_phone;
        private String commercial_register_photo;
        private int commercial_register_number;
        private String link;
        private String description;
        private int branches;
        private Object birthdate;
        private int city_id;
        private int subcat_id;
        private int logodesign_id;
        private String visible;
        private int user_id;
        private int template_id;
        private int category_id;
        private String logo;
        private Object created;
        private Object modified;
        private double longitude;
        private double latitude;
        private CategoryBean category;
        private List<StoreratesBean> storerates;
        private List<LikesBean> likes;
        private boolean from_google;
        private int maxwidth;
        private  float rate;

        public float getRate() {
            return rate;
        }

        public void setRate(float rate) {
            this.rate = rate;
        }

        public int getMaxwidth() {
            return maxwidth;
        }

        public void setMaxwidth(int maxwidth) {
            this.maxwidth = maxwidth;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public boolean isFrom_google() {
            return from_google;
        }

        public void setFrom_google(boolean from_google) {
            this.from_google = from_google;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public int getCommercial_register_number() {
            return commercial_register_number;
        }

        public void setCommercial_register_number(int commercial_register_number) {
            this.commercial_register_number = commercial_register_number;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getBranches() {
            return branches;
        }

        public void setBranches(int branches) {
            this.branches = branches;
        }

        public Object getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(Object birthdate) {
            this.birthdate = birthdate;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getSubcat_id() {
            return subcat_id;
        }

        public void setSubcat_id(int subcat_id) {
            this.subcat_id = subcat_id;
        }

        public int getLogodesign_id() {
            return logodesign_id;
        }

        public void setLogodesign_id(int logodesign_id) {
            this.logodesign_id = logodesign_id;
        }

        public String getVisible() {
            return visible;
        }

        public void setVisible(String visible) {
            this.visible = visible;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getTemplate_id() {
            return template_id;
        }

        public void setTemplate_id(int template_id) {
            this.template_id = template_id;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
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

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public CategoryBean getCategory() {
            return category;
        }

        public void setCategory(CategoryBean category) {
            this.category = category;
        }

        public List<StoreratesBean> getStorerates() {
            return storerates;
        }

        public void setStorerates(List<StoreratesBean> storerates) {
            this.storerates = storerates;
        }

        public List<LikesBean> getLikes() {
            return likes;
        }

        public void setLikes(List<LikesBean> likes) {
            this.likes = likes;
        }

        public static class CategoryBean {
            /**
             * id : 1
             * name : مطاعم
             * created : 2018-10-08T12:23:51+0000
             * cover : http://parashot.codesroots.com/library/default/burger.png
             * modified : 2018-10-08T12:14:41+0000
             */

            private int id;
            private String name;
            private String created;
            private String photo;
            private String modified;

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

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getModified() {
                return modified;
            }

            public void setModified(String modified) {
                this.modified = modified;
            }
        }

        public static class StoreratesBean {
            /**
             * smallstore_id : 1
             * stars : 3
             * count : 1
             * commentcount : 1
             */

            private int smallstore_id;
            private int stars;
            private int count;
            private int commentcount;

            public int getSmallstore_id() {
                return smallstore_id;
            }

            public void setSmallstore_id(int smallstore_id) {
                this.smallstore_id = smallstore_id;
            }

            public int getStars() {
                return stars;
            }

            public void setStars(int stars) {
                this.stars = stars;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getCommentcount() {
                return commentcount;
            }

            public void setCommentcount(int commentcount) {
                this.commentcount = commentcount;
            }
        }

        public static class LikesBean {
            /**
             * smallstore_id : 1
             * likes : 1
             * count : 1
             */

            private int smallstore_id;
            private int likes;
            private int count;

            public int getSmallstore_id() {
                return smallstore_id;
            }

            public void setSmallstore_id(int smallstore_id) {
                this.smallstore_id = smallstore_id;
            }

            public int getLikes() {
                return likes;
            }

            public void setLikes(int likes) {
                this.likes = likes;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }
    }
}
