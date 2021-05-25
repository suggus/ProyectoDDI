package com.example.ejercicio_14

import androidx.room.*
import kotlinx.coroutines.flow.Flow

//In the DAO, you specify SQL queries and associate them with method calls.
//By default, all queries must be executed on a separate thread.
//Room has kotlin coroutines support. This allows your queries to be annotated with
//the suspend modifier and then called from a coroutine or from another suspension function
//The DAO must be an interface or abstract class

@Dao
interface LibroDao {

    //When data changes, you usually want to take some action, such as displaying
    //the updated data in the UI; you have to observe the data.
    //To observe data changes you will use Flow from kotlinx-coroutines.
    //Later, we'll transform the Flow to LiveData in the ViewModel.
    @Query("SELECT * FROM libro_table ORDER BY titulo ASC")
    fun getAlphabetizedLibros(): Flow<List<Libro>>

    @Query("SELECT * FROM libro_table ORDER BY titulo ASC")
    fun getAlphabetizedLibros2(): List<Libro>

    @Query("SELECT * from libro_table where id like :id")
    fun buscarPorId(id: Int): Flow<Libro>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(miLibro: Libro)

    @Query("DELETE FROM libro_table")
    suspend fun deleteAll()

    @Delete
    suspend fun borrar(miLibro: Libro)

    @Update
    suspend fun modificar(miLibro: Libro)
}