package com.example.ejercicio_14

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.example.ejercicio_14.QuizTest.QuestionRepository
import com.example.ejercicio_14.QuizTest.QuestionRoomDatabase
import com.example.ejercicio_14.QuizTest.QuestionViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MainActivity : AppCompatActivity() {

    // No need to cancel this scope as it'll be torn down with the process
    val mainActivityScope = CoroutineScope(SupervisorJob())

    val miViewModel: VM by viewModels()

    // Using by lazy so the database and the repository are only created
    // when they're needed rather than when the application starts
    private val database by lazy {LibroRoomDatabase.getDatabase(this, mainActivityScope)}
    private val database3 by lazy { QuestionRoomDatabase.getDatabase(this, mainActivityScope)}
    private val miRepositorio by lazy { LibroRepository(database.miLibroDao()) }
    private val miRepositorio3 by lazy { QuestionRepository(database3.miQuestionDao()) }
    val miViewModel2: LibroViewModel by viewModels {LibroViewModel.LibroViewModelFactory(miRepositorio)}
    val miViewModel3: QuestionViewModel by viewModels {QuestionViewModel.QuestionViewModelFactory(miRepositorio3)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    //Añadimos el menú al toolbar y ...
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    //...controlamos los clicks de estos menús
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}