package com.codesroots.hossam.lordApp.entities;

public class LoginResponseModel {


    /**
     * success : true
     * data : {"id":186,"group":"1","token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjE4NiwiZXhwIjoxNTQyMDMyMjUyfQ.DYjY0XkiB3UijSeqUp8QOS-kHKyIx-ZuEC3KFd2Am_I"}
     */

    private boolean success;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 186
         * group : 1
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjE4NiwiZXhwIjoxNTQyMDMyMjUyfQ.DYjY0XkiB3UijSeqUp8QOS-kHKyIx-ZuEC3KFd2Am_I
         */

        private int id;
        private String group;
        private String token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
