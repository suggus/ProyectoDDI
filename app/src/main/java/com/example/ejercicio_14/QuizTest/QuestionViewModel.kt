package com.example.ejercicio_14.QuizTest

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class QuestionViewModel (private val miRepositorio: QuestionRepository) : ViewModel()  {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val listaQuestions: LiveData<List<Question>> = miRepositorio.listaQuestions.asLiveData()
    lateinit var miQuestion: LiveData<Question>
    lateinit var listaThematicQuestions: LiveData<List<Question>>


    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(miQuestion: Question) = viewModelScope.launch {
        miRepositorio.insert(miQuestion)
    }

    fun modificar(miQuestion: Question) = viewModelScope.launch {
        miRepositorio.modificar(miQuestion)
    }

    fun borrar(miQuestion: Question) = viewModelScope.launch {
        miRepositorio.borrar(miQuestion)
    }

    //Esta función me devuelve un Flow que guardo en la variable que he creado antes
    fun buscarPorTematica(thematic: String) = viewModelScope.launch {
        listaThematicQuestions = miRepositorio.buscarPorTematica(thematic).asLiveData()
    }


    class QuestionViewModelFactory(private val myRepository: QuestionRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(QuestionViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return QuestionViewModel(myRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


}