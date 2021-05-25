package com.example.ejercicio_14

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class Adaptador(private val myDataSet: MutableList<Pelicula>, val actividad: Activity) :
    RecyclerView.Adapter<Adaptador.ViewHolder>() {

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val tvTitulo: TextView
        val tvGenero: TextView
        val tvEstreno: TextView
        var posicion: Int

        init {
            tvTitulo = v.findViewById(R.id.tvTitulo)
            tvGenero = v.findViewById(R.id.tvGenero)
            tvEstreno = v.findViewById(R.id.tvEstreno)
            posicion = 0

            //Gestionamos la opción onClick para cada elemento holder del RecyclerView
            v.setOnClickListener {
                val bundle = bundleOf("id" to this.posicion)
                (actividad as MainActivity).findNavController(R.id.nav_host_fragment)
                    .navigate(R.id.action_SecondFragment_to_editFragment, bundle)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_contenedor, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pelicula = myDataSet[position]
        holder.tvTitulo.text = pelicula.titulo
        holder.tvGenero.text = pelicula.genero
        holder.tvEstreno.text = pelicula.estreno
        holder.posicion = position
    }

    //SyntaxByte
    fun removeItem(holder: RecyclerView.ViewHolder) {
        val removedPosition = holder.adapterPosition
        val removedItem = myDataSet[holder.adapterPosition]

        myDataSet.removeAt(holder.adapterPosition)
        notifyItemRemoved(holder.adapterPosition)

        Snackbar.make(holder.itemView,"${removedItem.titulo} deleted", Snackbar.LENGTH_LONG).setAction("UNDO"){
            myDataSet.add(removedPosition, removedItem)
            notifyItemInserted(removedPosition)
        }.show()
    }


    override fun getItemCount(): Int {
        return myDataSet.size
    }

}