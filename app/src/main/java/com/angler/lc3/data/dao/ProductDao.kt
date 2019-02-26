package com.angler.lc3.data.dao

import android.arch.persistence.room.*
import com.angler.lc3.data.entity.Product

@Dao
interface ProductDao {

    @Insert
    fun insert(vararg aProduct: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllProduct(aProduct: List<Product>)

    @Update
    fun update(vararg aProduct: Product)

    @Delete
    fun delete(vararg aProduct: Product)

    @Query("DELETE FROM product")
    fun deleteAll()

    @Query("SELECT * FROM product where  LanguageId = :aLanguage and IsActive = :aActive ORDER BY ProductId")
    fun getAllsProducts(aLanguage: String, aActive: String): List<Product>


    @Query("SELECT * FROM product where  IsActive = :aActive")
    fun getAllProducts(aActive: String): List<Product>
}
