package com.example.ejercicio_14.QuizTest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Question::class), version = 1, exportSchema = false)
public abstract class QuestionRoomDatabase : RoomDatabase() {

    abstract fun miQuestionDao(): QuestionDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var questionDao = database.miQuestionDao()

                    // Delete all content here.
                    questionDao.deleteAll()

                    // Add sample questions.
                    var question = Question(
                        1,
                        "Cómo podemos recuperar datos de otra actividad?",
                        "1999",
                        "Menu options",
                        "ResultActivity",
                        "Inflate bundle",
                        "StartActivity",
                        2
                    )
                    questionDao.insert(question)

                    question = Question(
                        2,
                        "Cómo modificamos datos preference?",
                        "1999",
                        "Modify data",
                        "Update preferences",
                        "Preferences.Editor",
                        "Set bundle.get",
                        3
                    )
                    questionDao.insert(question)

                    question = Question(
                        3,
                        "Cómo recuperar datos del bundle?",
                        "1999",
                        "getExtra",
                        "getData",
                        "bundle.getString",
                        "return extra",
                        1
                    )
                    questionDao.insert(question)

                    question = Question(
                        3,
                        "Cuanto es cuanto",
                        "1956",
                        "Oeste",
                        "2018",
                        "33 ",
                        " 56",
                        2
                    )
                    questionDao.insert(question)

                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: QuestionRoomDatabase? = null

        //getDatabase returns the singleton. It'll create the database the
        //first time it's accessed, using Room's database builder to create
        //a RoomDatabase object in the application context from the WordRoomDatabase
        //class and names it "libro_database".
        fun getDatabase(context: Context, scope: CoroutineScope): QuestionRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuestionRoomDatabase::class.java,
                    "question_database"
                ).addCallback(WordDatabaseCallback(scope)).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}