package com.example.ejercicio_14

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VM : ViewModel () {
    var dataset: MutableList<Pelicula> = mutableListOf()
    //val peliculaSeleccionada: MutableLiveData<Pelicula> = MutableLiveData()

    init {
        dataset = cargarPeliculas()
    }

    private fun cargarPeliculas(): MutableList<Pelicula> {
        val lista = mutableListOf<Pelicula>()
        lista.add(Pelicula("", "Rambo I", "Acción", "1997"))
        lista.add(Pelicula("", "Rambo II", "Acción", "1997"))
        lista.add(Pelicula("", "Rambo III", "Acción", "1997"))
        lista.add(Pelicula("", "Rambo IV", "Acción", "1997"))
        lista.add(Pelicula("", "Rambo V", "Acción", "1996"))
        lista.add(Pelicula("", "Rambo VI", "Acción", "1996"))
        lista.add(Pelicula("", "Rambo VII", "Acción", "1996"))
        lista.add(Pelicula("", "Rambo VIII", "Acción", "1996"))
        lista.add(Pelicula("", "Rambo IX", "Acción", "1996"))
        lista.add(Pelicula("", "Rambo X", "Acción", "1996"))
        lista.add(Pelicula("", "Rambo XI", "Acción", "1996"))
        lista.add(Pelicula("", "Rambo XII", "Acción", "1996"))
        lista.add(Pelicula("", "Rambo XIII", "Acción", "1996"))
        lista.add(Pelicula("", "Rambo XIV", "Acción", "1996"))
        lista.add(Pelicula("", "Rambo XV", "Acción", "1996"))

        return lista
    }

    fun insertar(peli:Pelicula){
        dataset.add(peli)
    }

    fun borrar(posicion:Int){
        dataset.removeAt(posicion)
        //val peliEliminada=dataset.removeAt(posicion).titulo
        //Toast.makeText(this,"Quieres eliminar $peliEliminada ?",Toast.LENGTH_LONG)
    }

    fun modificar(peli:Pelicula, posicion: Int){
        dataset[posicion].titulo=peli.titulo
        dataset[posicion].genero=peli.genero
        dataset[posicion].estreno=peli.estreno

    }

}