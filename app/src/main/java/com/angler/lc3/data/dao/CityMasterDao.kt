package com.angler.lc3.data.dao

import android.arch.persistence.room.*
import com.angler.lc3.data.entity.CityMaster

@Dao
interface CityMasterDao {

    @Insert
    fun insert(vararg user: CityMaster)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCity(order: List<CityMaster>)

    @Update
    fun update(vararg user: CityMaster)

    @Delete
    fun delete(vararg user: CityMaster)

    @Query("DELETE FROM city")
    fun deleteAll()

    @Query("SELECT * FROM city where StateId= :aStateId and  IsActive = :active ORDER BY CityId")
    fun getAllCity(aStateId: String, active: String): List<CityMaster>
}
