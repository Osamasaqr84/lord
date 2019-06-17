package com.codesroots.hossam.lordApp.entities;

public class RegisterResponseModel {


    /**
     * success : true
     * data : {"id":191,"code":200,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjE5MSwiZXhwIjoxNTQyMDM3ODkzfQ.WtM7cA6K4DEag4C6XMJtk1P_g3nr2DW9z_4qon94NYo"}
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
         * id : 191
         * code : 200
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjE5MSwiZXhwIjoxNTQyMDM3ODkzfQ.WtM7cA6K4DEag4C6XMJtk1P_g3nr2DW9z_4qon94NYo
         */

        private int id;
        private int code;
        private String token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
