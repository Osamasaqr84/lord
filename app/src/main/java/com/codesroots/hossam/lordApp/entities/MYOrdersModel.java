package com.codesroots.hossam.lordApp.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MYOrdersModel {


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
         * user_id : 114
         * store_id : 1
         * order_status : 2
         * delivry_id : 1
         * created : 2018-11-18T12:38:19+0000
         * modified : 2018-11-28T18:13:54+0000
         * storename :
         * notes : osama is here
         * store_icon :
         * delivery_price : 0
         * user_lat :
         * user_long :
         * store_lat :
         * store_long :
         * photo :
         * user_address :
         * delivery_time :
         * rate : null
         * store_address : null
         * payment_id : 0
         * orderdetails : [{"id":1,"product_id":1,"price":"a","type":"","created":"2018-11-18T12:38:20+0000","modified":"2018-11-18T12:38:20+0000","order_id":1,"count":null,"product":{"id":1,"name":"هواوي مايت 10 شريحتين اتصال - 64 جيجا, 4 جيجا رام, جيل الرابع ال اي تي, اسود","subcat_id":"1","category_id":"1","price":"15","last_price":"10","description":"iphone is a line of smartphones designed and marketed by Apple Inc","smallstore_id":1,"brand":"apple","product_info":"<html>\r\n<head>\r\n<\/head>\r\n<body>\r\n<ul>\r\n<li>دقه الكاميرا:5 ميجابكسل<\/li>\r\n<li>رام: 1 جيجابايت<\/li>\r\n<li>كاميرا ثانيه:5 ميجابكسل<\/li>\r\n<li>بنيه الشبكات:4جي ال تي اي<\/li>\r\n<li>دقه الكاميرا:5 ميجابكسل<\/li>\r\n<li>رام: 1 جيجابايت<\/li>\r\n<li>كاميرا ثانيه:5 ميجابكسل<\/li>\r\n<li>بنيه الشبكات:4جي ال تي اي<\/li>\r\n<li>دقه الكاميرا:5 ميجابكسل<\/li>\r\n<li>رام: 1 جيجابايت<\/li>\r\n<li>كاميرا ثانيه:5 ميجابكسل<\/li>\r\n<li>بنيه الشبكات:4جي ال تي اي<\/li>\r\n<\/ul>\r\n<\/body>\r\n<\/html>","amount":2,"guarantee":1,"created":"2018-09-17T16:37:29+0000","modified":"2018-09-17T16:38:03+0000","visible":"true","total_rating":[{"product_id":1,"stars":23,"count":6}],"productphotos":[{"id":1,"photo":"http://parashot.codesroots.com/library/default/35768252.jpeg","main":"ahmed2","product_id":1,"created":"2018-09-18T14:47:31+0000","modified":"2018-10-15T13:59:48+0000"},{"id":2,"photo":"http://parashot.codesroots.com/library/default/35768252.jpeg","main":"ahmed5","product_id":1,"created":"2018-09-19T17:10:00+0000","modified":"2018-09-21T19:45:41+0000"},{"id":3,"photo":"http://parashot.codesroots.com/library/default/35768252.jpeg","main":"ahmed7","product_id":1,"created":"2018-09-21T19:48:22+0000","modified":"2018-09-21T19:48:22+0000"}],"productrates":[{"id":1,"rate":3,"product_id":1,"user_id":113,"created":"2018-09-19T09:29:00+0000","modified":"2018-09-18T09:32:08+0000","comment":"hazem"},{"id":6,"rate":1,"product_id":1,"user_id":113,"created":"2018-10-08T09:29:00+0000","modified":"2018-09-18T09:32:08+0000","comment":null},{"id":11,"rate":5,"product_id":1,"user_id":113,"created":"2018-12-02T14:46:30+0000","modified":"2018-12-02T14:46:30+0000","comment":"osama"},{"id":12,"rate":4,"product_id":1,"user_id":188,"created":"2018-12-02T17:10:35+0000","modified":"2018-12-02T17:10:35+0000","comment":"السلام عليكم"},{"id":13,"rate":5,"product_id":1,"user_id":228,"created":"2018-12-05T17:27:02+0000","modified":"2018-12-05T17:27:02+0000","comment":"حازم راجل مخترم"},{"id":14,"rate":5,"product_id":1,"user_id":228,"created":"2018-12-05T17:57:29+0000","modified":"2018-12-05T17:57:29+0000","comment":"ناب"}]}}]
         * smallstore : {"id":1,"name":"متجرك","phone":"454545","hasdelivery":0,"longitude":-73.8248,"latitude":40.7324,"address":"حازم بيمسي"}
         * delivry : {"id":1,"name":"حازم","photo":"http://parashot.codesroots.com/library/osama.jpeg"}
         * user : {"id":114,"username":"aasssaaeewa","lat":"31.2099091","long":"30.0384395"}
         */

        private int id;
        private int user_id;
        private int store_id;
        private String order_status;
        private int delivry_id;
        private String created;
        private String modified;
        private String storename;
        private String notes;
        private String store_icon;
        private int delivery_price;
        private String user_lat;
        private String user_long;
        private String store_lat;
        private String store_long;
        private String photo;
        private String user_address;
        private String delivery_time;
        private float rate;
        private Object store_address;
        private int payment_id;
        private SmallstoreBean smallstore;
        private DelivryBean delivry;
        private UserBean user;
        private List<OrderdetailsBean> orderdetails;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public int getDelivry_id() {
            return delivry_id;
        }

        public void setDelivry_id(int delivry_id) {
            this.delivry_id = delivry_id;
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

        public String getStorename() {
            return storename;
        }

        public void setStorename(String storename) {
            this.storename = storename;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public String getStore_icon() {
            return store_icon;
        }

        public void setStore_icon(String store_icon) {
            this.store_icon = store_icon;
        }

        public int getDelivery_price() {
            return delivery_price;
        }

        public void setDelivery_price(int delivery_price) {
            this.delivery_price = delivery_price;
        }

        public String getUser_lat() {
            return user_lat;
        }

        public void setUser_lat(String user_lat) {
            this.user_lat = user_lat;
        }

        public String getUser_long() {
            return user_long;
        }

        public void setUser_long(String user_long) {
            this.user_long = user_long;
        }

        public String getStore_lat() {
            return store_lat;
        }

        public void setStore_lat(String store_lat) {
            this.store_lat = store_lat;
        }

        public String getStore_long() {
            return store_long;
        }

        public void setStore_long(String store_long) {
            this.store_long = store_long;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getUser_address() {
            return user_address;
        }

        public void setUser_address(String user_address) {
            this.user_address = user_address;
        }

        public String getDelivery_time() {
            return delivery_time;
        }

        public void setDelivery_time(String delivery_time) {
            this.delivery_time = delivery_time;
        }

        public float getRate() {
            return rate;
        }

        public void setRate(float rate) {
            this.rate = rate;
        }

        public Object getStore_address() {
            return store_address;
        }

        public void setStore_address(Object store_address) {
            this.store_address = store_address;
        }

        public int getPayment_id() {
            return payment_id;
        }

        public void setPayment_id(int payment_id) {
            this.payment_id = payment_id;
        }

        public SmallstoreBean getSmallstore() {
            return smallstore;
        }

        public void setSmallstore(SmallstoreBean smallstore) {
            this.smallstore = smallstore;
        }

        public DelivryBean getDelivry() {
            return delivry;
        }

        public void setDelivry(DelivryBean delivry) {
            this.delivry = delivry;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public List<OrderdetailsBean> getOrderdetails() {
            return orderdetails;
        }

        public void setOrderdetails(List<OrderdetailsBean> orderdetails) {
            this.orderdetails = orderdetails;
        }

        public static class SmallstoreBean {
            /**
             * id : 1
             * name : متجرك
             * phone : 454545
             * hasdelivery : 0
             * longitude : -73.8248
             * latitude : 40.7324
             * address : حازم بيمسي
             */

            private int id;
            private String name;
            private String phone;
            private int hasdelivery;
            private double longitude;
            private double latitude;
            private String address;

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

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getHasdelivery() {
                return hasdelivery;
            }

            public void setHasdelivery(int hasdelivery) {
                this.hasdelivery = hasdelivery;
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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }

        public static class DelivryBean {
            /**
             * id : 1
             * name : حازم
             * photo : http://parashot.codesroots.com/library/osama.jpeg
             */

            private int id;
            private String name;
            private String photo;

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

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }
        }

        public static class UserBean {
            /**
             * id : 114
             * username : aasssaaeewa
             * lat : 31.2099091
             * long : 30.0384395
             */

            private int id;
            private String username;
            private String lat;
            @SerializedName("long")
            private String longX;

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

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLongX() {
                return longX;
            }

            public void setLongX(String longX) {
                this.longX = longX;
            }
        }

        public static class OrderdetailsBean {
            /**
             * id : 1
             * product_id : 1
             * price : a
             * type :
             * created : 2018-11-18T12:38:20+0000
             * modified : 2018-11-18T12:38:20+0000
             * order_id : 1
             * count : null
             * product : {"id":1,"name":"هواوي مايت 10 شريحتين اتصال - 64 جيجا, 4 جيجا رام, جيل الرابع ال اي تي, اسود","subcat_id":"1","category_id":"1","price":"15","last_price":"10","description":"iphone is a line of smartphones designed and marketed by Apple Inc","smallstore_id":1,"brand":"apple","product_info":"<html>\r\n<head>\r\n<\/head>\r\n<body>\r\n<ul>\r\n<li>دقه الكاميرا:5 ميجابكسل<\/li>\r\n<li>رام: 1 جيجابايت<\/li>\r\n<li>كاميرا ثانيه:5 ميجابكسل<\/li>\r\n<li>بنيه الشبكات:4جي ال تي اي<\/li>\r\n<li>دقه الكاميرا:5 ميجابكسل<\/li>\r\n<li>رام: 1 جيجابايت<\/li>\r\n<li>كاميرا ثانيه:5 ميجابكسل<\/li>\r\n<li>بنيه الشبكات:4جي ال تي اي<\/li>\r\n<li>دقه الكاميرا:5 ميجابكسل<\/li>\r\n<li>رام: 1 جيجابايت<\/li>\r\n<li>كاميرا ثانيه:5 ميجابكسل<\/li>\r\n<li>بنيه الشبكات:4جي ال تي اي<\/li>\r\n<\/ul>\r\n<\/body>\r\n<\/html>","amount":2,"guarantee":1,"created":"2018-09-17T16:37:29+0000","modified":"2018-09-17T16:38:03+0000","visible":"true","total_rating":[{"product_id":1,"stars":23,"count":6}],"productphotos":[{"id":1,"photo":"http://parashot.codesroots.com/library/default/35768252.jpeg","main":"ahmed2","product_id":1,"created":"2018-09-18T14:47:31+0000","modified":"2018-10-15T13:59:48+0000"},{"id":2,"photo":"http://parashot.codesroots.com/library/default/35768252.jpeg","main":"ahmed5","product_id":1,"created":"2018-09-19T17:10:00+0000","modified":"2018-09-21T19:45:41+0000"},{"id":3,"photo":"http://parashot.codesroots.com/library/default/35768252.jpeg","main":"ahmed7","product_id":1,"created":"2018-09-21T19:48:22+0000","modified":"2018-09-21T19:48:22+0000"}],"productrates":[{"id":1,"rate":3,"product_id":1,"user_id":113,"created":"2018-09-19T09:29:00+0000","modified":"2018-09-18T09:32:08+0000","comment":"hazem"},{"id":6,"rate":1,"product_id":1,"user_id":113,"created":"2018-10-08T09:29:00+0000","modified":"2018-09-18T09:32:08+0000","comment":null},{"id":11,"rate":5,"product_id":1,"user_id":113,"created":"2018-12-02T14:46:30+0000","modified":"2018-12-02T14:46:30+0000","comment":"osama"},{"id":12,"rate":4,"product_id":1,"user_id":188,"created":"2018-12-02T17:10:35+0000","modified":"2018-12-02T17:10:35+0000","comment":"السلام عليكم"},{"id":13,"rate":5,"product_id":1,"user_id":228,"created":"2018-12-05T17:27:02+0000","modified":"2018-12-05T17:27:02+0000","comment":"حازم راجل مخترم"},{"id":14,"rate":5,"product_id":1,"user_id":228,"created":"2018-12-05T17:57:29+0000","modified":"2018-12-05T17:57:29+0000","comment":"ناب"}]}
             */

            private int id;
            private int product_id;
            private String price;
            private String type;
            private String created;
            private String modified;
            private int order_id;
            private Object count;
            private ProductBean product;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public Object getCount() {
                return count;
            }

            public void setCount(Object count) {
                this.count = count;
            }

            public ProductBean getProduct() {
                return product;
            }

            public void setProduct(ProductBean product) {
                this.product = product;
            }

            public static class ProductBean {
                /**
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
                 * total_rating : [{"product_id":1,"stars":23,"count":6}]
                 * productphotos : [{"id":1,"photo":"http://parashot.codesroots.com/library/default/35768252.jpeg","main":"ahmed2","product_id":1,"created":"2018-09-18T14:47:31+0000","modified":"2018-10-15T13:59:48+0000"},{"id":2,"photo":"http://parashot.codesroots.com/library/default/35768252.jpeg","main":"ahmed5","product_id":1,"created":"2018-09-19T17:10:00+0000","modified":"2018-09-21T19:45:41+0000"},{"id":3,"photo":"http://parashot.codesroots.com/library/default/35768252.jpeg","main":"ahmed7","product_id":1,"created":"2018-09-21T19:48:22+0000","modified":"2018-09-21T19:48:22+0000"}]
                 * productrates : [{"id":1,"rate":3,"product_id":1,"user_id":113,"created":"2018-09-19T09:29:00+0000","modified":"2018-09-18T09:32:08+0000","comment":"hazem"},{"id":6,"rate":1,"product_id":1,"user_id":113,"created":"2018-10-08T09:29:00+0000","modified":"2018-09-18T09:32:08+0000","comment":null},{"id":11,"rate":5,"product_id":1,"user_id":113,"created":"2018-12-02T14:46:30+0000","modified":"2018-12-02T14:46:30+0000","comment":"osama"},{"id":12,"rate":4,"product_id":1,"user_id":188,"created":"2018-12-02T17:10:35+0000","modified":"2018-12-02T17:10:35+0000","comment":"السلام عليكم"},{"id":13,"rate":5,"product_id":1,"user_id":228,"created":"2018-12-05T17:27:02+0000","modified":"2018-12-05T17:27:02+0000","comment":"حازم راجل مخترم"},{"id":14,"rate":5,"product_id":1,"user_id":228,"created":"2018-12-05T17:57:29+0000","modified":"2018-12-05T17:57:29+0000","comment":"ناب"}]
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
                private List<TotalRatingBean> total_rating;
                private List<ProductphotosBean> productphotos;
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

                public List<TotalRatingBean> getTotal_rating() {
                    return total_rating;
                }

                public void setTotal_rating(List<TotalRatingBean> total_rating) {
                    this.total_rating = total_rating;
                }

                public List<ProductphotosBean> getProductphotos() {
                    return productphotos;
                }

                public void setProductphotos(List<ProductphotosBean> productphotos) {
                    this.productphotos = productphotos;
                }

                public List<ProductratesBean> getProductrates() {
                    return productrates;
                }

                public void setProductrates(List<ProductratesBean> productrates) {
                    this.productrates = productrates;
                }

                public static class TotalRatingBean {
                    /**
                     * product_id : 1
                     * stars : 23
                     * count : 6
                     */

                    private int product_id;
                    private int stars;
                    private int count;

                    public int getProduct_id() {
                        return product_id;
                    }

                    public void setProduct_id(int product_id) {
                        this.product_id = product_id;
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

                public static class ProductratesBean {
                    /**
                     * id : 1
                     * rate : 3
                     * product_id : 1
                     * user_id : 113
                     * created : 2018-09-19T09:29:00+0000
                     * modified : 2018-09-18T09:32:08+0000
                     * comment : hazem
                     */

                    private int id;
                    private int rate;
                    private int product_id;
                    private int user_id;
                    private String created;
                    private String modified;
                    private String comment;

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
                }
            }
        }
    }
}
