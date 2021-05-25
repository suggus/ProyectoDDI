package com.example.ejercicio_14

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.media.MediaRouter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var miRecyclerView: RecyclerView
    //1.La siguiente variable la llevo a la clase VM
    //private lateinit var dataset: List<Pelicula>
    private var colorDrawableBackground: ColorDrawable = ColorDrawable(Color.parseColor("#FF0000"))
    private lateinit var deleteIcon: Drawable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //2.dataset = PeliculaList()
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_second, container, false).apply { }
        miRecyclerView = rootView.findViewById(R.id.myRecyclerView)
        miRecyclerView.layoutManager = LinearLayoutManager(activity)
        //3.miRecyclerView.adapter = Adaptador(dataset)
        miRecyclerView.adapter = Adaptador((activity as MainActivity).miViewModel.dataset,activity as MainActivity)

        deleteIcon = ContextCompat.getDrawable(activity as MainActivity,R.drawable.baseline_delete_sweep_white_18dp)!!

        /*
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
         Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
        }
        */

        rootView.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_editFragment)
        }

        //Aquí quiero ver cómo funciona el swipe (SyntaxByte)
        //No he podido instanciar la siguiente variable pq es una clase abstracta
        //Usando esta sintaxis (object), es posible
        val itemTHC = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                //TODO("Not yet implemented")
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val viewAdapter = miRecyclerView.adapter
                (viewAdapter as Adaptador).removeItem(viewHolder)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
                val iconMarginVertical = (viewHolder.itemView.height - deleteIcon.intrinsicHeight) / 2

                if (dX > 0) {
                    colorDrawableBackground.setBounds(itemView.left, itemView.top, dX.toInt(), itemView.bottom)
                    deleteIcon.setBounds(itemView.left + iconMarginVertical, itemView.top + iconMarginVertical,
                        itemView.left + iconMarginVertical + deleteIcon.intrinsicWidth, itemView.bottom - iconMarginVertical)
                } else {
                    colorDrawableBackground.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
                    deleteIcon.setBounds(itemView.right - iconMarginVertical - deleteIcon.intrinsicWidth, itemView.top + iconMarginVertical,
                        itemView.right - iconMarginVertical, itemView.bottom - iconMarginVertical)
                    deleteIcon.level = 0
                }

                colorDrawableBackground.draw(c)

                c.save()

                if (dX > 0)
                    c.clipRect(itemView.left, itemView.top, dX.toInt(), itemView.bottom)
                else
                    c.clipRect(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)

                deleteIcon.draw(c)

                c.restore()

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }
        val itemTH = ItemTouchHelper(itemTHC)
        itemTH.attachToRecyclerView(miRecyclerView)

        return rootView
    }
}

//3. Esta función tb la llevo a la clase VM
/*
private fun PeliculaList(): List<Pelicula> {
    val lista = mutableListOf<Pelicula>()
    lista.add(Pelicula("", "Rambo I", "Acción", "1996"))
    lista.add(Pelicula("", "Rambo II", "Acción", "1996"))
    lista.add(Pelicula("", "Rambo III", "Acción", "1996"))
    lista.add(Pelicula("", "Rambo IV", "Acción", "1996"))
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
}*/
