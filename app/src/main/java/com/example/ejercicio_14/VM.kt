package com.example.ejercicio_14

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VM : ViewModel () {
    var dataset: MutableList<Tarea> = mutableListOf()
    //val peliculaSeleccionada: MutableLiveData<Pelicula> = MutableLiveData()

    init {
        dataset = cargarPeliculas()
    }

    private fun cargarPeliculas(): MutableList<Tarea> {
        val lista = mutableListOf<Tarea>()
        lista.add(Tarea("Comprar el periódico"))
        lista.add(Tarea("Terminar el proyecto de Unity"))
        lista.add(Tarea("Pedir cita IRPF"))
        lista.add(Tarea("Reservar vuelo a Menorca"))
        lista.add(Tarea("Cita con el médico"))

        return lista
    }

    fun insertar(peli:Tarea){
        dataset.add(peli)
    }

    fun borrar(posicion:Int){
        dataset.removeAt(posicion)
        //val peliEliminada=dataset.removeAt(posicion).titulo
        //Toast.makeText(this,"Quieres eliminar $peliEliminada ?",Toast.LENGTH_LONG)
    }

    fun modificar(peli:Tarea, posicion: Int){
        dataset[posicion].tarea=peli.tarea
    }

}