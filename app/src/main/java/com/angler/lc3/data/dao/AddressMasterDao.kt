package com.angler.lc3.data.dao

import android.arch.persistence.room.*
import com.angler.lc3.data.entity.AddressMaster

@Dao
interface AddressMasterDao {

    @Insert
    fun insert(vararg user: AddressMaster)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAddress(order: List<AddressMaster>)

    @Update
    fun update(vararg user: AddressMaster)

    @Delete
    fun delete(vararg user: AddressMaster)

    @Query("DELETE FROM address")
    fun deleteAll()

    @Query("SELECT * FROM address where StateId = :aStateId and CityId = :aCityId and IsActive = :active ORDER BY AddressId")
    fun getAllAddress(aStateId: String, aCityId: String, active: String): List<AddressMaster>
}
