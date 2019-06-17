package com.codesroots.hossam.lordApp.entities;

import java.util.List;

public class DeliveryOffers {


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
         * start_date : 2018-11-10T10:21:18+0000
         * end_date : 2018-11-10T10:21:18+0000
         * percentage : 20%
         * product_id : 1
         * order_id : 1
         * order : {"id":1,"user_id":2,"store_id":1,"order_status":"","delivry_id":1,"created":"2018-11-18T12:38:19+0000","modified":"2018-11-18T12:38:19+0000","storename":"","notes":"osama is here","store_icon":"","delivery_price":0,"user_lat":"","user_long":"","store_lat":"","store_long":"","photo":"","user_address":"","delivery_time":"","rate":null,"store_address":null,"payment_id":0,"delivry":{"id":1,"name":"حازم","personal_id":454545,"personal_id_image":"","personal_license_image":"","bank_accounts":"8888","phone":"010","alternative_phone":"010","gender":"male","code":0,"created":null,"modified":null,"delivery_long":"17.5","delivery_lat":"18","deliveryrates":[{"id":1,"rate":3,"comments":"good","amount_outstanding":2,"user_id":113,"created":"2018-11-27T13:23:41+0000","modified":"2018-11-27T13:23:41+0000","delivry_id":1}]}}
         */

        private int id;
        private String start_date;
        private String end_date;
        private String percentage;
        private String price;
        private int product_id;
        private int order_id;
        private OrderBean order;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public String getPercentage() {
            return percentage;
        }

        public void setPercentage(String percentage) {
            this.percentage = percentage;
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

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public static class OrderBean {
            /**
             * id : 1
             * user_id : 2
             * store_id : 1
             * order_status :
             * delivry_id : 1
             * created : 2018-11-18T12:38:19+0000
             * modified : 2018-11-18T12:38:19+0000
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
             * delivry : {"id":1,"name":"حازم","personal_id":454545,"personal_id_image":"","personal_license_image":"","bank_accounts":"8888","phone":"010","alternative_phone":"010","gender":"male","code":0,"created":null,"modified":null,"delivery_long":"17.5","delivery_lat":"18","deliveryrates":[{"id":1,"rate":3,"comments":"good","amount_outstanding":2,"user_id":113,"created":"2018-11-27T13:23:41+0000","modified":"2018-11-27T13:23:41+0000","delivry_id":1}]}
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
            private Object rate;
            private Object store_address;
            private int payment_id;
            private DelivryBean delivry;

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

            public Object getRate() {
                return rate;
            }

            public void setRate(Object rate) {
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

            public DelivryBean getDelivry() {
                return delivry;
            }

            public void setDelivry(DelivryBean delivry) {
                this.delivry = delivry;
            }

            public static class DelivryBean {
                /**
                 * id : 1
                 * name : حازم
                 * personal_id : 454545
                 * personal_id_image :
                 * personal_license_image :
                 * bank_accounts : 8888
                 * phone : 010
                 * alternative_phone : 010
                 * gender : male
                 * code : 0
                 * created : null
                 * modified : null
                 * delivery_long : 17.5
                 * delivery_lat : 18
                 * deliveryrates : [{"id":1,"rate":3,"comments":"good","amount_outstanding":2,"user_id":113,"created":"2018-11-27T13:23:41+0000","modified":"2018-11-27T13:23:41+0000","delivry_id":1}]
                 */

                private int id;
                private String name;
                private int personal_id;
                private String personal_id_image;
                private String personal_license_image;
                private String bank_accounts;
                private String phone;
                private String alternative_phone;
                private String gender;
                private int code;
                private Object created;
                private Object modified;
                private String delivery_long;
                private String delivery_lat;
                private String photo;
                private List<DeliveryratesBean> deliveryrates;

                public String getPhoto() {
                    return photo;
                }

                public void setPhoto(String photo) {
                    this.photo = photo;
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

                public int getPersonal_id() {
                    return personal_id;
                }

                public void setPersonal_id(int personal_id) {
                    this.personal_id = personal_id;
                }

                public String getPersonal_id_image() {
                    return personal_id_image;
                }

                public void setPersonal_id_image(String personal_id_image) {
                    this.personal_id_image = personal_id_image;
                }

                public String getPersonal_license_image() {
                    return personal_license_image;
                }

                public void setPersonal_license_image(String personal_license_image) {
                    this.personal_license_image = personal_license_image;
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

                public String getGender() {
                    return gender;
                }

                public void setGender(String gender) {
                    this.gender = gender;
                }

                public int getCode() {
                    return code;
                }

                public void setCode(int code) {
                    this.code = code;
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

                public String getDelivery_long() {
                    return delivery_long;
                }

                public void setDelivery_long(String delivery_long) {
                    this.delivery_long = delivery_long;
                }

                public String getDelivery_lat() {
                    return delivery_lat;
                }

                public void setDelivery_lat(String delivery_lat) {
                    this.delivery_lat = delivery_lat;
                }

                public List<DeliveryratesBean> getDeliveryrates() {
                    return deliveryrates;
                }

                public void setDeliveryrates(List<DeliveryratesBean> deliveryrates) {
                    this.deliveryrates = deliveryrates;
                }

                public static class DeliveryratesBean {
                    /**
                     * id : 1
                     * rate : 3
                     * comments : good
                     * amount_outstanding : 2
                     * user_id : 113
                     * created : 2018-11-27T13:23:41+0000
                     * modified : 2018-11-27T13:23:41+0000
                     * delivry_id : 1
                     */

                    private int id;
                    private int rate;
                    private String comments;
                    private int amount_outstanding;
                    private int user_id;
                    private String created;
                    private String modified;
                    private int delivry_id;

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

                    public String getComments() {
                        return comments;
                    }

                    public void setComments(String comments) {
                        this.comments = comments;
                    }

                    public int getAmount_outstanding() {
                        return amount_outstanding;
                    }

                    public void setAmount_outstanding(int amount_outstanding) {
                        this.amount_outstanding = amount_outstanding;
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

                    public int getDelivry_id() {
                        return delivry_id;
                    }

                    public void setDelivry_id(int delivry_id) {
                        this.delivry_id = delivry_id;
                    }
                }
            }
        }
    }
}
