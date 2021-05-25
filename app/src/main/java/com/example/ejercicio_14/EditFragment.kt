package com.example.ejercicio_14

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass.
 */
class EditFragment : Fragment() {

    var posicion: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Podremos modificar las opciones del menú
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bInsertar = view.findViewById<Button>(R.id.btt_insertar)
        val bBorrar = view.findViewById<Button>(R.id.btt_borrar)
        val bModificar = view.findViewById<Button>(R.id.btt_modificar)
        val etTitulo = view.findViewById<EditText>(R.id.et_nombre_pelicula)
        val etGenero = view.findViewById<EditText>(R.id.et_genero_pelicula)
        val etEstreno = view.findViewById<EditText>(R.id.et_estreno_pelicula)
        val tvId = view.findViewById<TextView>(R.id.tv_Id)
        posicion = arguments?.getInt("id") ?: -1
        var peli: Pelicula = Pelicula("", "", "", "")

        if (posicion == -1) {
            bBorrar.isEnabled = false
            bModificar.isEnabled = false
            bInsertar.isEnabled = true
            activity?.setTitle("Insertar películas")
        } else {
            bBorrar.isEnabled = true
            bModificar.isEnabled = true
            bInsertar.isEnabled = false
            activity?.setTitle("Modificar/Borrar películas")
            peli = (activity as MainActivity).miViewModel.dataset[posicion]
            tvId.text = String.format("ID: $posicion")
            etTitulo.setText(peli.titulo)
            etGenero.setText(peli.genero)
            etEstreno.setText(peli.estreno)
        }

        bInsertar.setOnClickListener() {
            if (etTitulo.text.isEmpty() || etGenero.text.isEmpty() || etEstreno.text.isEmpty()) {
                Toast.makeText(
                    (activity as MainActivity),
                    "Inserta todos los campos",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                (activity as MainActivity).miViewModel.insertar(
                    Pelicula(
                        "",
                        etTitulo.text.toString(),
                        etGenero.text.toString(),
                        etEstreno.text.toString()
                    )
                )
                findNavController().navigate(R.id.action_editFragment_to_SecondFragment)
            }
        }

        bBorrar.setOnClickListener() {
            (activity as MainActivity).miViewModel.borrar(posicion)
            findNavController().navigate(R.id.action_editFragment_to_SecondFragment)
        }

        bModificar.setOnClickListener() {
            if (peli.titulo == etTitulo.text.toString() && peli.genero == etGenero.text.toString() && peli.estreno == etEstreno.text.toString()) {
                Toast.makeText(
                    (activity as MainActivity),
                    "Modifica algún campo",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                (activity as MainActivity).miViewModel.modificar(
                    Pelicula(
                        "",
                        etTitulo.text.toString(),
                        etGenero.text.toString(),
                        etEstreno.text.toString()
                    ), posicion
                )
                findNavController().navigate(R.id.action_editFragment_to_SecondFragment)
            }
        }
    }

}