package com.example.ejercicio_14

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

/*
    var user: String? = "gustavo"
    var password: String? = "aaa"
*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Intento recoger datos del SharedPreference
/*
        val datos: SharedPreferences =
            (activity as MainActivity).getSharedPreferences("datos", Context.MODE_PRIVATE)
        user = datos.getString("user", user)
        password = datos.getString("password", password)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            if (view.findViewById<EditText>(R.id.et_usuario).text.toString() == user &&
                view.findViewById<EditText>(R.id.et_contrasenya).text.toString() == password
            ) {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            } else {
                Toast.makeText(activity, "Usuario no registrado", Toast.LENGTH_LONG).show()
            }
        }

        view.findViewById<Button>(R.id.btt_insertar).setOnClickListener {
            if (view.findViewById<EditText>(R.id.et_usuario).text.toString() == user) {
                findNavController().navigate(R.id.action_FirstFragment_to_passwordFragment)
            } else {
                Toast.makeText(activity, "Nombre usuario incorrecto ", Toast.LENGTH_LONG).show()
            }
        }
*/

        //Intento corregir datos del SharedPreference
        val datos2: SharedPreferences =
            (activity as MainActivity).getSharedPreferences("datos2", Context.MODE_PRIVATE)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            //datos2.getString("clave", null)
            if (datos2.getString("usuario", null) != null) {
                if (view.findViewById<EditText>(R.id.et_usuario).text.toString().isNotEmpty()
                    && view.findViewById<EditText>(R.id.et_contrasenya).text.toString().isNotEmpty()
                    && datos2.getString(
                        "usuario",
                        "erroneous user"
                    ) == view.findViewById<EditText>(R.id.et_usuario).text.toString()
                    && datos2.getString(
                        "clave",
                        "erroneous password"
                    ) == view.findViewById<EditText>(R.id.et_contrasenya).text.toString()
                ) {
                    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                } else {
                    Toast.makeText(activity, "Usuario y clave erróneas\n Inténtalo de nuevo", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (view.findViewById<EditText>(R.id.et_usuario).text.toString().isNotEmpty()
                    && view.findViewById<EditText>(R.id.et_contrasenya).text.toString().isNotEmpty()
                ) {
                    var editor2: SharedPreferences.Editor = datos2.edit()
                    editor2.putString(
                        "usuario",
                        view.findViewById<EditText>(R.id.et_usuario).text.toString()
                    )
                    editor2.putString(
                        "clave",
                        view.findViewById<EditText>(R.id.et_contrasenya).text.toString()
                    )
                    editor2.apply()
                    Toast.makeText(activity, "Usuario creado", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(activity, "Registrate antes de comenzar", Toast.LENGTH_SHORT).show()
                }
            }
        }

        view.findViewById<Button>(R.id.btt_insertar).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_passwordFragment)
        }

        view.findViewById<Button>(R.id.button_menu).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_viewPagerFragment)
        }

        view.findViewById<Button>(R.id.button_third).setOnClickListener {
            val bundle = bundleOf("tematica" to "pelicula")
            findNavController().navigate(R.id.action_FirstFragment_to_thirdFragment, bundle)
        }

        view.findViewById<Button>(R.id.button_fifth).setOnClickListener {
            val bundle = bundleOf("tematica" to "cancion")
            findNavController().navigate(R.id.action_FirstFragment_to_thirdFragment, bundle)
        }

        view.findViewById<Button>(R.id.button_sixth).setOnClickListener {
            val bundle = bundleOf("tematica" to "libro")
            findNavController().navigate(R.id.action_FirstFragment_to_thirdFragment, bundle)
        }

        view.findViewById<Button>(R.id.button_fourth).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_fourthFragment)
        }
    }


/*
    override fun onResume() {
        super.onResume()
        val datos: SharedPreferences =
            (activity as MainActivity).getSharedPreferences("datos", Context.MODE_PRIVATE)
        user = datos.getString("user", user).toString()
        password = datos.getString("password", "abcdef").toString()
    }

    override fun onPause() {
        super.onPause()
        val datos: SharedPreferences =
            (activity as MainActivity).getSharedPreferences("datos", Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = datos.edit()
        editor.putString("user", user)
        editor.putString("password", password)
        editor.apply()
    }
*/

}