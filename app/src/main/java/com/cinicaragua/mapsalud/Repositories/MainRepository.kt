package com.cinicaragua.mapsalud.Repositories

import com.cinicaragua.mapsalud.AppDatabases.AppDatabase
import com.cinicaragua.mapsalud.Entities.Centro
import kotlinx.coroutines.flow.Flow

class MainRepository(private val db : AppDatabase) {

    fun selectAllCentros() : Flow<List<Centro>>{
        return db.CentroDAO().selectAllCentros()
    }

    fun SelectCentro(id : Int) : Flow<Centro>{
        return  db.CentroDAO().selectCentro(id)
    }
}