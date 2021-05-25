package com.example.ejercicio_14

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "libro_table")
data class Libro(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "imagen") var imagen: String,
    @NonNull @ColumnInfo(name = "titulo") var titulo: String,
    @ColumnInfo(name = "genero") var genero: String,
    @ColumnInfo(name = "estreno") var estreno: String,
) {

}