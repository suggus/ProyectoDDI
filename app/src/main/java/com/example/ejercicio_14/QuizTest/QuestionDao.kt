package com.example.ejercicio_14.QuizTest

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    //When data changes, you usually want to take some action, such as displaying
    //the updated data in the UI; you have to observe the data.
    //To observe data changes you will use Flow from kotlinx-coroutines.
    //Later, we'll transform the Flow to LiveData in the ViewModel.
    @Query("SELECT * FROM question_table ORDER BY thematic ASC")
    fun getAlphabetizedQuestions(): Flow<List<Question>>

    @Query("SELECT * from question_table where thematic like :thematic")
    fun buscarPorTematica(thematic: String): Flow<List<Question>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(miQuestion: Question)

    @Query("DELETE FROM question_table")
    suspend fun deleteAll()

    @Delete
    suspend fun borrar(miQuestion: Question)

    @Update
    suspend fun modificar(miQuestion: Question)
}