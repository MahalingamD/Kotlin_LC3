package com.angler.lc3.data.dao

import android.arch.persistence.room.*
import com.angler.lc3.data.entity.StateMaster

@Dao
interface StateMasterDao {

    @Insert
    fun insert(vararg user: StateMaster)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllState(order: List<StateMaster>)


    @Update
    fun update(vararg user: StateMaster)

    @Delete
    fun delete(vararg user: StateMaster)

    @Query("DELETE FROM state")
    fun deleteAll()


    @Query("SELECT * FROM state where IsActive = :active ORDER BY StateId")
    fun getAllState(active: String): List<StateMaster>
}
