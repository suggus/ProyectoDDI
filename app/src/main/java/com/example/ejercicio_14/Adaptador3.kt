package com.example.ejercicio_14

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ejercicio_14.QuizTest.Quiz
import com.example.ejercicio_14.QuizTest.QuizQuestionsActivity

class Adaptador3(
    private val context: MainActivity,
    private val myDataset: List<Quiz>,
    val actividad: Activity
) :
    RecyclerView.Adapter<Adaptador3.ViewHolder>() {

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val tvTitulo: TextView
        val tvGenero: TextView
        val tvEstreno: TextView
        val imgImagen: ImageView
        var posicion: Int
        lateinit var tematica: String


        init {
            tvTitulo = v.findViewById(R.id.fondoTitulo)
            tvGenero = v.findViewById(R.id.fondoGenero)
            tvEstreno = v.findViewById(R.id.fondoEstreno)
            imgImagen = v.findViewById(R.id.fondoImagen)
            posicion = 0

            ///Gestionamos la opción onClick para cada elemento holder del RecyclerView
/*
            v.setOnClickListener {
                val bundle = bundleOf("id" to this.posicion)
                (actividad as MainActivity).findNavController(R.id.nav_host_fragment)
                    .navigate(R.id.action_fourthFragment_to_editFragment, bundle)
            }
*/
            v.setOnClickListener{
                val bundle = bundleOf("tematica" to this.tematica)
                Log.i("Question bundle", bundle.toString())
                (actividad as MainActivity).findNavController(R.id.nav_host_fragment)
                    .navigate(R.id.action_fourthFragment_to_quizQuestionsFragment,bundle)
            }
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cardview_pelicula, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.tvNumero.text = "Este es el contenedor $position"
        val pelicula = myDataset[position]
        holder.tvTitulo.text = pelicula.titulo
        holder.tvGenero.text = pelicula.genero
        holder.tvEstreno.text = pelicula.estreno
        //holder.imgImagen.setImageResource(pelicula.imagen)
        Glide.with(context).load(pelicula.imagen).into(
            holder.imgImagen)
        holder.posicion = position
        holder.tematica = pelicula.estreno
    }

    override fun getItemCount(): Int {
        //return 10
        return myDataset.size
    }

}