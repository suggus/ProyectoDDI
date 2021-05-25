package com.example.ejercicio_14

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass.
 */
class FourthFragment : Fragment() {

    private lateinit var miRecyclerView: RecyclerView
    private lateinit var dataset: List<Pelicula>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataset = GetPeliculas()
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_fourth, container, false).apply{}
        miRecyclerView=rootView.findViewById(R.id.myRecyclerView3)
        miRecyclerView.layoutManager = GridLayoutManager(activity,3)
        //recyclerView.adapter = Adaptador()
        miRecyclerView.adapter = Adaptador3(activity as MainActivity,dataset, activity as MainActivity)

        rootView.findViewById<FloatingActionButton>(R.id.fab4).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_editFragment)
        }

        return rootView
    }

}