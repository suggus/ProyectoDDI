package com.example.ejercicio_14

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//Room is a database layer on top of an SQLite database
//By default, to avoid poor UI performance, Room doesn't allow you to issue queries on the main thread.
//The database class for Room must be abstract and extend RoomDatabase
@Database(entities = arrayOf(Libro::class), version = 1, exportSchema = false)
public abstract class LibroRoomDatabase : RoomDatabase() {

    abstract fun miLibroDao(): LibroDao


    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var libroDao = database.miLibroDao()

                    // Delete all content here.
                    libroDao.deleteAll()

                    // Add sample libros.
                    var libro = Libro(
                        1,
                        "https://th.bing.com/th/id/OIP.I2b5msR3WS-oFLREDnBrewHaMq?w=182&h=311&c=7&o=5&dpr=1.25&pid=1.7",
                        "El Rey recibe",
                        "Policiaca",
                        "2018"
                    )
                    libroDao.insert(libro)
                    libro = Libro(
                        2,
                        "https://th.bing.com/th/id/OIP.fvtfiPqylsVT1F_XUfL87wHaMM?w=182&h=300&c=7&o=5&dpr=1.25&pid=1.7",
                        "El Hobbit",
                        "Ficción",
                        "1984"
                    )
                    libroDao.insert(libro)
                }
            }
        }
    }


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: LibroRoomDatabase? = null

        //getDatabase returns the singleton. It'll create the database the
        //first time it's accessed, using Room's database builder to create
        //a RoomDatabase object in the application context from the WordRoomDatabase
        //class and names it "libro_database".
        fun getDatabase(context: Context, scope: CoroutineScope): LibroRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LibroRoomDatabase::class.java,
                    "libro_database"
                ).addCallback(WordDatabaseCallback(scope)).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}