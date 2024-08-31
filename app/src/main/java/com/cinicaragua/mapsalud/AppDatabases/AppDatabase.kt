package com.cinicaragua.mapsalud.AppDatabases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cinicaragua.mapsalud.DAOs.CentroDAO
import com.cinicaragua.mapsalud.Entities.Centro

@Database(entities = [Centro::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun CentroDAO() : CentroDAO

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase ? = null

        fun getDatabase(context : Context) : AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "AppDatabase"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }

}