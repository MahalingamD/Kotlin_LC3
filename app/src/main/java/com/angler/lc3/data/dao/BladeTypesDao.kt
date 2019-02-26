package com.angler.lc3.data.dao

import android.arch.persistence.room.*
import com.angler.lc3.data.entity.BladeTypes

@Dao
interface BladeTypesDao {

    @Insert
    fun insert(vararg aProduct: BladeTypes)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllProduct(aProduct: List<BladeTypes>)

    @Update
    fun update(vararg aProduct: BladeTypes)

    @Delete
    fun delete(vararg aProduct: BladeTypes)

    @Query("DELETE FROM bladetypes")
    fun deleteAll()


    @Query("SELECT * FROM bladetypes where ProductId= :aProducdId and IsActive = :aActive ORDER BY BladeTypeId")
    fun getAllProducts(aProducdId: String, aActive: String): List<BladeTypes>


    @Query("SELECT * FROM bladetypes where IsActive = :aActive")
    fun getAllActiveProducts(aActive: String): List<BladeTypes>
}
