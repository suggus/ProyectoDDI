package com.example.ejercicio_14

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ejercicio_14.Base.BaseViewHolder
import de.hdodenhof.circleimageview.CircleImageView
import java.lang.IllegalArgumentException

//Este Adapter no sabe que ViewHolder va a recibir (*). Se lo diremos luego cuando creemos el viewHolder
//Ventaja: Podemos crear varios viewholder dentro de este adaptador y crear distintos viewTypes

class Adaptador2(
    private val context: Activity,
    val peliculasList: List<Pelicula>?,
    val cancionesList: List<Cancion>?,
    val librosList: List<Libro>?,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private val typePelicula: Int = 1
    private val typeCancion: Int = 2
    private val typeLibro: Int = 3
    private var viewType: Boolean = false

    override fun getItemViewType(position: Int): Int {
        //return super.getItemViewType(position)
        var itemview: Int

        if (peliculasList.isNullOrEmpty() and (librosList.isNullOrEmpty())) {
            itemview = typeCancion
        } else if ((librosList.isNullOrEmpty()) and cancionesList.isNullOrEmpty()) {
            viewType = true
            itemview = typePelicula
        } else {
            itemview = typeLibro
        }
        return itemview
    }
/*
        if (TextUtils.isEmpty(peliculasList.get(position).titulo))
*/

    //El context es el contexto desde donde hacemos la instancia de este RecyclerView (MainActivity)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        if (viewType == typeCancion) {
            return CancionViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_contenedor2, parent, false)
            )

        } else if (viewType == typePelicula) {
            return PeliculaViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_contenedor2, parent, false)
            )
        } else {
            return LibroViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_contenedor2, parent, false)
            )

        }
    }

    inner class PeliculaViewHolder(itemView: View) : BaseViewHolder<Pelicula>(itemView) {

        override fun bind(item: Pelicula, position: Int) {
            Glide.with(context).load(item.imagen)
                .into(itemView.findViewById<CircleImageView>(R.id.imgCircleView))
            itemView.findViewById<TextView>(R.id.tvwTitulo).text = item.titulo
            itemView.findViewById<TextView>(R.id.tvwGenero).text = item.genero
            itemView.findViewById<TextView>(R.id.tvwEstreno).text = item.estreno

            //Gestionamos la opción onClick para cada elemento holder del RecyclerView
            itemView.findViewById<CircleImageView>(R.id.imgCircleView).setOnClickListener {
                itemClickListener.onImageClick(item.imagen)
            }
            itemView.findViewById<TextView>(R.id.tvwTitulo).setOnClickListener {
                itemClickListener.onItemClick(item.titulo)
            }
        }
    }

    inner class CancionViewHolder(itemView: View) : BaseViewHolder<Cancion>(itemView) {

        override fun bind(item: Cancion, position: Int) {
            Glide.with(context).load(item.imagen)
                .into(itemView.findViewById<CircleImageView>(R.id.imgCircleView))
            itemView.findViewById<TextView>(R.id.tvwTitulo).text = item.titulo
            itemView.findViewById<TextView>(R.id.tvwGenero).text = item.genero
            itemView.findViewById<TextView>(R.id.tvwEstreno).text = item.estreno

            //Gestionamos la opción onClick para cada elemento holder del RecyclerView
            itemView.findViewById<CircleImageView>(R.id.imgCircleView).setOnClickListener {
                itemClickListener.onImageClick(item.imagen)
            }
            itemView.findViewById<TextView>(R.id.tvwTitulo).setOnClickListener {
                itemClickListener.onItemClick(item.titulo)
            }
        }
    }

    inner class LibroViewHolder(itemView: View) : BaseViewHolder<Libro>(itemView) {

        var ide: Int = 0

        init {
            //posicion = 0

            //Gestionamos la opción onClick para cada elemento holder del RecyclerView
            itemView.setOnClickListener {
                //ide = itemView.findViewById<TextView>(R.id.tv_Id2).text.toString().toInt()
                val bundle = bundleOf("id" to this.ide)
                //Error:java.lang.ClassCastException: com.example.ejercicio_14.ThirdFragment cannot be cast to com.example.ejercicio_14.MainActivity
                (context as MainActivity).findNavController(R.id.nav_host_fragment)
                    .navigate(R.id.action_thirdFragment_to_editFragment2, bundle)
            }

        }

        override fun bind(item: Libro, position: Int) {
            //A ver como me las apaño con el idposicion 02/05/2021

            ide = item.id
            // java.lang.NullPointerException: itemView.findViewById<TextView>(R.id.tv_Id2) must not be null
            //itemView.findViewById<TextView>(R.id.tv_Id2).text = item.id
            Glide.with(context).load(item.imagen)
                .into(itemView.findViewById<CircleImageView>(R.id.imgCircleView))
            itemView.findViewById<TextView>(R.id.tvwTitulo).text = item.titulo
            itemView.findViewById<TextView>(R.id.tvwGenero).text = item.genero
            itemView.findViewById<TextView>(R.id.tvwEstreno).text = item.estreno


            //Gestionamos la opción onClick para cada elemento holder del RecyclerView
            itemView.findViewById<CircleImageView>(R.id.imgCircleView).setOnClickListener {
                itemClickListener.onImageClick(item.imagen)
            }
            itemView.findViewById<TextView>(R.id.tvwTitulo).setOnClickListener {
                itemClickListener.onItemClick(item.titulo)
            }
//            itemView.findViewById<View>(itemViewType).setOnClickListener{
//                itemClickListener.onViewClick(itemView)
//            }

        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is PeliculaViewHolder -> peliculasList?.get(position)?.let { holder.bind(it, position) }
            is CancionViewHolder -> cancionesList?.get(position)?.let { holder.bind(it, position) }
            is LibroViewHolder -> librosList?.get(position)?.let { holder.bind(it, position) }
                //holder.posicion=position

            else -> IllegalArgumentException("Se olvidó de pasar el viewHolder en el bind")
        }
    }

    override fun getItemCount(): Int {
/*
        val lista : List<Pelicula> = listOf()
        val lista2 : List<Cancion> = listOf()
        if (viewType == false){
            if (cancionesList != null) {
                lista = listOf()
            }
        }else{

        }

*/
        var tamanyo: Int = 0
        if (peliculasList != null) {
            tamanyo = peliculasList.size
        }
        if (cancionesList != null) {
            tamanyo = cancionesList.size
        }
        if (librosList != null) {
            tamanyo = librosList.size
        }

        return tamanyo
    }

    //Usaremos interfaces: El lugar donde implementamos este interfaz
    //recibe información para que haga algún tipo de tarea
    interface OnItemClickListener {
        fun onImageClick(imagen: String)
        fun onItemClick(titulo: String)
        ///fun onViewClick(view: View)
    }

}
