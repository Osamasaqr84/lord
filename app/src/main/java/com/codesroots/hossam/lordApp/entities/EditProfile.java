package com.codesroots.hossam.lordApp.entities;

import com.google.gson.annotations.SerializedName;

public class EditProfile {


    /**
     * success : false
     * user : {"id":130,"username":"1","active":1,"email_verified":1,"user_group_id":"1","long":"","lat":"","gender":"","phone":"0","purchase_way":0,"points":0,"type_phone":0,"created":"2018-10-14T15:06:27+0000","modified":"2018-12-16T12:33:34+0000","email":"qwssaasxs","photo":null,"birthdate":null,"facebook_id":18}
     */

    private boolean success;
    private UserBean user;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * id : 130
         * username : 1
         * active : 1
         * email_verified : 1
         * user_group_id : 1
         * long :
         * lat :
         * gender :
         * phone : 0
         * purchase_way : 0
         * points : 0
         * type_phone : 0
         * created : 2018-10-14T15:06:27+0000
         * modified : 2018-12-16T12:33:34+0000
         * email : qwssaasxs
         * photo : null
         * birthdate : null
         * facebook_id : 18
         */

        private int id;
        private String username;
        private int active;
        private int email_verified;
        private String user_group_id;
        @SerializedName("long")
        private String longX;
        private String lat;
        private String gender;
        private String phone;
        private int purchase_way;
        private int points;
        private int type_phone;
        private String created;
        private String modified;
        private String email;
        private Object photo;
        private Object birthdate;
        private int facebook_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getActive() {
            return active;
        }

        public void setActive(int active) {
            this.active = active;
        }

        public int getEmail_verified() {
            return email_verified;
        }

        public void setEmail_verified(int email_verified) {
            this.email_verified = email_verified;
        }

        public String getUser_group_id() {
            return user_group_id;
        }

        public void setUser_group_id(String user_group_id) {
            this.user_group_id = user_group_id;
        }

        public String getLongX() {
            return longX;
        }

        public void setLongX(String longX) {
            this.longX = longX;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getPurchase_way() {
            return purchase_way;
        }

        public void setPurchase_way(int purchase_way) {
            this.purchase_way = purchase_way;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public int getType_phone() {
            return type_phone;
        }

        public void setType_phone(int type_phone) {
            this.type_phone = type_phone;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Object getPhoto() {
            return photo;
        }

        public void setPhoto(Object photo) {
            this.photo = photo;
        }

        public Object getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(Object birthdate) {
            this.birthdate = birthdate;
        }

        public int getFacebook_id() {
            return facebook_id;
        }

        public void setFacebook_id(int facebook_id) {
            this.facebook_id = facebook_id;
        }
    }
}
