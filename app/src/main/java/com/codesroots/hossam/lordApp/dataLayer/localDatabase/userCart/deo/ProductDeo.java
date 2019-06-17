package com.codesroots.hossam.lordApp.dataLayer.localDatabase.userCart.deo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.userCart.entities.Product;
import java.util.List;


@Dao
public interface ProductDeo {

    @Insert
    public void insertProduct(Product productDeo);

    @Update
    public void upateProduct(Product productDeo);

    @Delete
    public void deleteProduct(Product productDeo);

    @Query("select * from Product ")
    public Product selectAll();

    @Query("select * from Product where stor_id like :storeid")
    public List<Product> selectAllProductForStore(int storeid);




    @Query("SELECT COUNT(id) FROM Product WHERE stor_id like :store_id")
    int getNumberOfRows(int store_id);


    @Query("SELECT COUNT(*) FROM Product WHERE product_id like :productid")
    int chieckItemExists(int productid);

}
