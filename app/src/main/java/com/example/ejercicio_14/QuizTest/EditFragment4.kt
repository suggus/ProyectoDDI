package com.example.ejercicio_14.QuizTest

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
import com.example.ejercicio_14.Libro
import com.example.ejercicio_14.MainActivity
import com.example.ejercicio_14.R

/**
 * A simple [Fragment] subclass.
 */
class EditFragment4 : Fragment() {

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
        return inflater.inflate(R.layout.fragment_edit4, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bInsertar = view.findViewById<Button>(R.id.btt_insertar4)
        val bBorrar = view.findViewById<Button>(R.id.btt_borrar4)
        val bModificar = view.findViewById<Button>(R.id.btt_modificar4)
        val etPregunta = view.findViewById<EditText>(R.id.et_question)
        val etOpcion1 = view.findViewById<EditText>(R.id.et_option1)
        val etOpcion2 = view.findViewById<EditText>(R.id.et_option2)
        val etOpcion3 = view.findViewById<EditText>(R.id.et_option3)
        val etOpcion4 = view.findViewById<EditText>(R.id.et_option4)
        val tvId = view.findViewById<TextView>(R.id.tv_Id4)
/*

        posicion = arguments?.getInt("id") ?: -1
        var liburua: Libro = Libro(0, "", "", "","")

        if (posicion == -1) {
            bBorrar.isEnabled = false
            bModificar.isEnabled = false
            bInsertar.isEnabled = true
            activity?.setTitle("Insertar Pregunta")
        } else {
            bBorrar.isEnabled = true
            bModificar.isEnabled = true
            bInsertar.isEnabled = false
            activity?.setTitle("Modificar/Borrar libros")
            (activity as MainActivity).miViewModel2.buscarPorId(posicion)
            //var liburua : Libro
            (activity as MainActivity).miViewModel2.miLibro.observe(activity as MainActivity) {
                it?.let {
                    //liburua = it
                    tvId.text = posicion.toString()
                    etImagen.setText(it.imagen)
                    etTitulo.setText(it.titulo)
                    etGenero.setText(it.genero)
                    etEstreno.setText(it.estreno)
                }
            }
        }

        bInsertar.setOnClickListener() {
            if (etTitulo.text.isEmpty() || etGenero.text.isEmpty() || etEstreno.text.isEmpty()) {
                Toast.makeText(
                    (activity as MainActivity),
                    "Inserta todos los campos",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                (activity as MainActivity).miViewModel2.insert(
                    Libro(
                        0,
                        etImagen.text.toString(),
                        etTitulo.text.toString(),
                        etGenero.text.toString(),
                        etEstreno.text.toString()
                    )
                )
                findNavController().navigate(R.id.action_editFragment2_to_thirdFragment)
            }
        }

        bBorrar.setOnClickListener() {
            (activity as MainActivity).miViewModel2.borrar(                    Libro(
                tvId.text.toString().toInt(),
                etImagen.text.toString(),
                etTitulo.text.toString(),
                etGenero.text.toString(),
                etEstreno.text.toString()
            )
            )
            findNavController().navigate(R.id.action_editFragment2_to_thirdFragment)
        }

        bModificar.setOnClickListener() {
            if (liburua.titulo == etTitulo.text.toString() && liburua.genero == etGenero.text.toString() && liburua.estreno == etEstreno.text.toString()) {
                Toast.makeText(
                    (activity as MainActivity),
                    "Modifica algún campo",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                (activity as MainActivity).miViewModel2.modificar(
                    Libro(
                        //java.lang.NumberFormatException: For input string: "ID: 3"
                        tvId.text.toString().toInt(),
                        etImagen.text.toString(),
                        etTitulo.text.toString(),
                        etGenero.text.toString(),
                        etEstreno.text.toString()
                    )
                )
                findNavController().navigate(R.id.action_editFragment2_to_thirdFragment)
            }
        }

 */
    }
}