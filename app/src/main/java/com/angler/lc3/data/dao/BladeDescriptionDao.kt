package com.angler.lc3.data.dao

import android.arch.persistence.room.*
import com.angler.lc3.data.entity.BladeDescription

@Dao
interface BladeDescriptionDao {

    @Insert
    fun insert(vararg aProduct: BladeDescription)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllProduct(aProduct: List<BladeDescription>)

    @Update
    fun update(vararg aProduct: BladeDescription)

    @Delete
    fun delete(vararg aProduct: BladeDescription)

    @Query("DELETE FROM bladedescription")
    fun deleteAll()


    @Query("SELECT * FROM bladedescription where  IsActive = :aActive")
    fun getAllActiveProducts(aActive: String): List<BladeDescription>


    @Query("SELECT * FROM bladedescription where BladeTypeId = :aBladeType and  IsActive = :aActive ORDER BY BladeDescId")
    fun getAllBladeDescriptions(aBladeType: String, aActive: String): List<BladeDescription>
}
