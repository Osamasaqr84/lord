package com.codesroots.hossam.lordApp.entities;

import java.util.List;

public class MoreRate {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sum : 23
         * count : 6
         * id : 1
         * name : هواوي مايت 10 شريحتين اتصال - 64 جيجا, 4 جيجا رام, جيل الرابع ال اي تي, اسود
         * subcat_id : 1
         * category_id : 1
         * price : 15
         * last_price : 10
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
         * smallstore : {"id":1,"name":"متجرك"}
         * productphotos : [{"id":1,"photo":"http://parashot.codesroots.com/library/default/35768252.jpeg","main":"ahmed2","product_id":1,"created":"2018-09-18T14:47:31+0000","modified":"2018-10-15T13:59:48+0000"},{"id":2,"photo":"http://parashot.codesroots.com/library/default/35768252.jpeg","main":"ahmed5","product_id":1,"created":"2018-09-19T17:10:00+0000","modified":"2018-09-21T19:45:41+0000"},{"id":3,"photo":"http://parashot.codesroots.com/library/default/35768252.jpeg","main":"ahmed7","product_id":1,"created":"2018-09-21T19:48:22+0000","modified":"2018-09-21T19:48:22+0000"}]
         */

        private int sum;
        private int count;
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
        private SmallstoreBean smallstore;
        private List<ProductphotosBean> productphotos;

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
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

        public SmallstoreBean getSmallstore() {
            return smallstore;
        }

        public void setSmallstore(SmallstoreBean smallstore) {
            this.smallstore = smallstore;
        }

        public List<ProductphotosBean> getProductphotos() {
            return productphotos;
        }

        public void setProductphotos(List<ProductphotosBean> productphotos) {
            this.productphotos = productphotos;
        }

        public static class SmallstoreBean {
            /**
             * id : 1
             * name : متجرك
             */

            private int id;
            private String name;

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
        }

        public static class ProductphotosBean {
            /**
             * id : 1
             * photo : http://parashot.codesroots.com/library/default/35768252.jpeg
             * main : ahmed2
             * product_id : 1
             * created : 2018-09-18T14:47:31+0000
             * modified : 2018-10-15T13:59:48+0000
             */

            private int id;
            private String photo;
            private String main;
            private int product_id;
            private String created;
            private String modified;

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

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
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
