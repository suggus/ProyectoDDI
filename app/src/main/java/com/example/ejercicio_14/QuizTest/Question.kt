package com.example.ejercicio_14.QuizTest

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_table")
data class Question(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "question") var question: String,
    @ColumnInfo(name = "thematic") var thematic: String,
    @ColumnInfo(name = "optionOne") var optionOne: String,
    @ColumnInfo(name = "optionTwo") var optionTwo: String,
    @ColumnInfo(name = "optionThree") var optionThree: String,
    @ColumnInfo(name = "optionFour") var optionFour: String,
    @ColumnInfo(name = "correctAnswer") var correctAnswer: Int

) {

}