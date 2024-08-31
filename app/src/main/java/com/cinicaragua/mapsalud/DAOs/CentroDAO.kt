package com.cinicaragua.mapsalud.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cinicaragua.mapsalud.Entities.Centro
import kotlinx.coroutines.flow.Flow

@Dao
interface CentroDAO {

    @Insert
    fun insertCentro(centro : Centro)

    @Update
    fun updateCentro(centro : Centro)

    @Delete
    fun deleteCentro(centro : Centro)

    @Query("SELECT * From centro")
    fun selectAllCentros() : Flow<List<Centro>>

    @Query("SELECT * FROM centro WHERE id = :id")
    fun selectCentro(id : Int) : Flow<Centro>
}