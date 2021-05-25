package com.example.ejercicio_14.QuizTest

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class QuestionRepository(private val miDao: QuestionDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val listaQuestions: Flow<List<Question>> = miDao.getAlphabetizedQuestions()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(miQuestion: Question) {
        miDao.insert(miQuestion)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun borrar(miQuestion: Question) {
        miDao.borrar(miQuestion)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun modificar(miQuestion: Question) {
        miDao.modificar(miQuestion)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun buscarPorTematica(thematic:String): Flow<List<Question>> {
        return miDao.buscarPorTematica(thematic)
    }

}