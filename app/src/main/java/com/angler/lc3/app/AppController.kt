package com.angler.lc3.app


import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import android.content.ContextWrapper
import com.angler.lc3.data.AppDatabase
import com.angler.lc3.preference.TIPrefs


class AppController : Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this

        // Initialize the Shared Preferences class
        TIPrefs.Builder().setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true).build()

        Room.databaseBuilder(this, AppDatabase::class.java, "rotogro_db")
            .fallbackToDestructiveMigration()
            .build()


    }

    companion object {

        val TAG = AppController::class.java.simpleName
        //  private AppDatabase db;


        @get:Synchronized
        var instance: AppController? = null
            private set


        internal val FROM_1_TO_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //database.execSQL( "ALTER TABLE Repo ADD COLUMN createdAt TEXT" );
            }
        }
    }
}