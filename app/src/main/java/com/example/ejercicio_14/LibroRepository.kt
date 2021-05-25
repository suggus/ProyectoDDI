package com.example.ejercicio_14

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

//The Repository implements the logic for deciding whether to fetch data from
//a network or use results cached in a local database.
// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class LibroRepository(private val miDao: LibroDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val listaLibros: Flow<List<Libro>> = miDao.getAlphabetizedLibros()
    //val listaLibros2: List<Libro> = libroDao.getAlphabetizedLibros2()
    //val listaLibros2: MutableList<Libro> = listaLibros as MutableList<Libro>

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(miLibro: Libro) {
        miDao.insert(miLibro)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun borrar(miLibro: Libro) {
        miDao.borrar(miLibro)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun modificar(miLibro: Libro) {
        miDao.modificar(miLibro)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun buscarPorId(id:Int):Flow<Libro> {
        return miDao.buscarPorId(id)
    }

}

























