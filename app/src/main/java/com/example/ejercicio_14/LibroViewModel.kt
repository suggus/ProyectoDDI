package com.example.ejercicio_14

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class LibroViewModel (private val miRepositorio: LibroRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val listaLibros: LiveData<List<Libro>> = miRepositorio.listaLibros.asLiveData()
    lateinit var miLibro:LiveData<Libro>

/*
    val listaDeLibros: MutableList<Libro> = miRepositorio.listaLibros2

    init {
        listaDeLibros.add(
            Libro(
                1,
                "https://th.bing.com/th/id/OIP.I2b5msR3WS-oFLREDnBrewHaMq?w=182&h=311&c=7&o=5&dpr=1.25&pid=1.7",
                "El Rey recibe",
                "Policiaca",
                "2018"
            )
        )
        listaDeLibros.add(
            Libro(
                2,
                "https://th.bing.com/th/id/OIP.fvtfiPqylsVT1F_XUfL87wHaMM?w=182&h=300&c=7&o=5&dpr=1.25&pid=1.7",
                "El Hobbit",
                "Ficción",
                "1984"
            )
        )



    }
*/

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(miLibro: Libro) = viewModelScope.launch {
        miRepositorio.insert(miLibro)
    }

    fun modificar(miLibro: Libro) = viewModelScope.launch {
        miRepositorio.modificar(miLibro)
    }

    fun borrar(miLibro: Libro) = viewModelScope.launch {
        miRepositorio.borrar(miLibro)
    }

    //Esta función me devuelve un Flow que guardo en la variable que he creado antes
    fun buscarPorId(id: Int) = viewModelScope.launch {
        miLibro = miRepositorio.buscarPorId(id).asLiveData()
    }


    class LibroViewModelFactory(private val myRepository: LibroRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LibroViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LibroViewModel(myRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}


