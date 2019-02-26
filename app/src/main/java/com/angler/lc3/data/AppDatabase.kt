package com.angler.lc3.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.angler.lc3.data.dao.*
import com.angler.lc3.data.entity.*

@Database(
    entities = [LanguageMaster::class, Product::class, BladeTypes::class, BladeDescription::class, StateMaster::class, CityMaster::class, AddressMaster::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "rotogro_db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE as AppDatabase
        }
    }


    abstract fun languageModel(): LanguageDao

    abstract fun ProductModel(): ProductDao

    abstract fun bladeTypesDao(): BladeTypesDao

    abstract fun bladeDescriptionDao(): BladeDescriptionDao

    abstract fun stateMasterDao(): StateMasterDao

    abstract fun cityMasterDao(): CityMasterDao

    abstract fun addressMasterDao(): AddressMasterDao

}