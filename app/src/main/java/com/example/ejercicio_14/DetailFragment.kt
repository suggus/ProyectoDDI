package com.example.ejercicio_14

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    lateinit var navController: NavController
    var imagen: String? = null //lateinit var imagen: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imagen = requireArguments().getString("imagen")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this).load(imagen).into(view.findViewById<PhotoView>(R.id.photo_view)
        )
    }

}