package com.codesroots.hossam.lordApp.dataLayer.repositories;

import android.os.AsyncTask;
import android.support.v4.util.Consumer;

import com.codesroots.hossam.lordApp.dataLayer.localDatabase.userCart.deo.ProductDeo;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.userCart.entities.Product;

import java.util.List;


public class UserCartRepository {


    private Consumer<List<Product>> cartItems;
    private Consumer<Throwable> cartItemsError;
    private Consumer<Throwable> onError;
    private ProductDeo productDeo;
    int storid;
    public UserCartRepository( ProductDeo pDeo, int storeid)
    {

        productDeo=pDeo;
        storid =storeid;
    }


    public void GetProductsFromDB() {
        new ProductAsyncTask(productDeo).execute(storid);
    }

    public void deleteProductFromDB(Product product) {
        new DeleteProductAsyncTask(productDeo).execute(product);
    }
    private  class ProductAsyncTask extends AsyncTask<Integer, List<Product>,  List<Product>> {

        private ProductDeo productdeo;

        public ProductAsyncTask(ProductDeo productDeo) {
            productdeo = productDeo;
        }

        @Override
        protected List<Product> doInBackground(Integer... storeid) {
            List<Product> Allproducts = productdeo.selectAllProductForStore(storeid[0]);
            return Allproducts;
        }

        @Override
        protected void onPostExecute(List<Product> products) {

            if (cartItems != null)
                cartItems.accept(products);
            else {
                if (cartItemsError != null) {
                    cartItemsError.accept(new Exception());
                }
            }
        }
    }

    private  class DeleteProductAsyncTask extends AsyncTask<Product, Void, Void> {
        private ProductDeo productdeo;
        public DeleteProductAsyncTask(ProductDeo productDeo) {
            productdeo = productDeo;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productdeo.deleteProduct(products[0]);
            Product product= productdeo.selectAll();
            List<Product> Allproducts= productdeo.selectAllProductForStore(storid);
            return null;
        }
    }


    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }


    public void setcartItemss(Consumer<List<Product>> cartItems) {
        this.cartItems = cartItems;
    }


}
