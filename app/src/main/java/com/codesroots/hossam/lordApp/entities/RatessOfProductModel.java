package com.codesroots.hossam.lordApp.entities;

import java.util.List;

public class RatessOfProductModel {

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
         * name : iphone
         * subcat_id :
         * category_id :
         * price : 10
         * last_price : 15
         * description : iphone is a line of smartphones designed and marketed by Apple Inc
         * smallstore_id : 1
         * brand : apple
         * product_info : <html>
         <head>
         </head>
         <body>
         <ul>
         <li>دقه الكاميرا:5 ميجابكسل</li>
         <li>رام: 1 جيجابايت</li>
         <li>كاميرا ثانيه:5 ميجابكسل</li>
         <li>بنيه الشبكات:4جي ال تي اي</li>
         <li>دقه الكاميرا:5 ميجابكسل</li>
         <li>رام: 1 جيجابايت</li>
         <li>كاميرا ثانيه:5 ميجابكسل</li>
         <li>بنيه الشبكات:4جي ال تي اي</li>
         <li>دقه الكاميرا:5 ميجابكسل</li>
         <li>رام: 1 جيجابايت</li>
         <li>كاميرا ثانيه:5 ميجابكسل</li>
         <li>بنيه الشبكات:4جي ال تي اي</li>
         </ul>
         </body>
         </html>
         * amount : 2
         * guarantee : 1
         * created : 2018-09-17T16:37:29+0000
         * modified : 2018-09-17T16:38:03+0000
         * visible : true
         * productrates : [{"id":1,"rate":3,"product_id":1,"user_id":113,"created":"2018-09-19T09:29:00+0000","modified":"2018-09-18T09:32:08+0000","comment":"hazem","user":{"photo":"http://parashot.codesroots.com/webroot/library/1/categoryphoto/15390008811521779367.png","username":"sssasss"}},{"id":2,"rate":2,"product_id":1,"user_id":113,"created":"2018-10-03T09:29:00+0000","modified":"2018-09-18T09:32:08+0000","comment":null,"user":{"photo":"http://parashot.codesroots.com/webroot/library/1/categoryphoto/15390008811521779367.png","username":"sssasss"}},{"id":6,"rate":1,"product_id":1,"user_id":113,"created":"2018-10-08T09:29:00+0000","modified":"2018-09-18T09:32:08+0000","comment":null,"user":{"photo":"http://parashot.codesroots.com/webroot/library/1/categoryphoto/15390008811521779367.png","username":"sssasss"}}]
         */

        private int id;
        private String name;
        private String subcat_id;
        private String category_id;
        private String price;
        private String last_price;
        private String description;
        private int smallstore_id;
        private String brand;
        private String product_info;
        private int amount;
        private int guarantee;
        private String created;
        private String modified;
        private String visible;
        private List<ProductratesBean> productrates;

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

        public String getSubcat_id() {
            return subcat_id;
        }

        public void setSubcat_id(String subcat_id) {
            this.subcat_id = subcat_id;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getLast_price() {
            return last_price;
        }

        public void setLast_price(String last_price) {
            this.last_price = last_price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getSmallstore_id() {
            return smallstore_id;
        }

        public void setSmallstore_id(int smallstore_id) {
            this.smallstore_id = smallstore_id;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getProduct_info() {
            return product_info;
        }

        public void setProduct_info(String product_info) {
            this.product_info = product_info;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getGuarantee() {
            return guarantee;
        }

        public void setGuarantee(int guarantee) {
            this.guarantee = guarantee;
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

        public String getVisible() {
            return visible;
        }

        public void setVisible(String visible) {
            this.visible = visible;
        }

        public List<ProductratesBean> getProductrates() {
            return productrates;
        }

        public void setProductrates(List<ProductratesBean> productrates) {
            this.productrates = productrates;
        }

        public static class ProductratesBean {
            /**
             * id : 1
             * rate : 3
             * product_id : 1
             * user_id : 113
             * created : 2018-09-19T09:29:00+0000
             * modified : 2018-09-18T09:32:08+0000
             * comment : hazem
             * user : {"photo":"http://parashot.codesroots.com/webroot/library/1/categoryphoto/15390008811521779367.png","username":"sssasss"}
             */

            private int id;
            private int rate;
            private int product_id;
            private int user_id;
            private String created;
            private String modified;
            private String comment;
            private UserBean user;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getRate() {
                return rate;
            }

            public void setRate(int rate) {
                this.rate = rate;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
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

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class UserBean {
                /**
                 * photo : http://parashot.codesroots.com/webroot/library/1/categoryphoto/15390008811521779367.png
                 * username : sssasss
                 */

                private String photo;
                private String username;

                public String getPhoto() {
                    return photo;
                }

                public void setPhoto(String photo) {
                    this.photo = photo;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }
            }
        }
    }
}
