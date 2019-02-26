package com.angler.lc3.data.dao

import android.arch.persistence.room.*
import com.angler.lc3.data.entity.LanguageMaster

@Dao
interface LanguageDao {

    @Insert
    fun insert(vararg user: LanguageMaster)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllLanguage(order: List<LanguageMaster>)


    @Update
    fun update(vararg user: LanguageMaster)

    @Delete
    fun delete(vararg user: LanguageMaster)

    @Query("DELETE FROM language")
    fun deleteAll()

    // @Query("SELECT * FROM language")

    @Query("SELECT * FROM language where IsActive = :active ORDER BY LanguageId")
    fun getAllLanguages(active: Int): List<LanguageMaster>
}
