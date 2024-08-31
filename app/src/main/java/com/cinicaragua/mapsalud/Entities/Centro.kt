package com.cinicaragua.mapsalud.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Centro(

    @PrimaryKey
    var id : Int?,

    @ColumnInfo(name = "nombre_centrosalud")
    var nombreCentrosalud : String,

    @ColumnInfo(name = "numero_telefonocentro")
    var numeroTelefonocentro : Int,

    @ColumnInfo(name = "distrito")
    var distrito : Int,

    @ColumnInfo(name = "zona" )
    var Zona : String,

    @ColumnInfo(name = "direccion")
    var Direccion : String,

    @ColumnInfo(name = "municipio")
    var municipio : String,

    @ColumnInfo(name = "latitud")
    var latitud : Double,

    @ColumnInfo(name = "longitud")
    var longitud : Double

)
