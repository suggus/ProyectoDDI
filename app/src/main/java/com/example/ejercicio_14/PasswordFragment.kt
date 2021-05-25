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
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController


/**
 * A simple [Fragment] subclass.
 */
class PasswordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_password, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

/*
        val datos: SharedPreferences = (activity as MainActivity).getSharedPreferences("datos", Context.MODE_PRIVATE)

        val user = datos.getString("user", "unknowed user").toString()
        view.findViewById<TextView>(R.id.tv_usuario).text = user

        view.findViewById<Button>(R.id.btt_modificarPassword).setOnClickListener {
            if (view.findViewById<EditText>(R.id.et_password).text.toString().isNotEmpty()) {
                var editor: SharedPreferences.Editor = datos.edit()
                editor.putString("password", view.findViewById<EditText>(R.id.et_password).text.toString())
                editor.apply()
                findNavController().navigate(R.id.action_passwordFragment_to_FirstFragment)
            } else {
                Toast.makeText(activity, "Password no modificado", Toast.LENGTH_LONG).show()
            }
        }
*/

        //Correción del Shared preferences
        val datos2: SharedPreferences = (activity as MainActivity).getSharedPreferences("datos2", Context.MODE_PRIVATE)

        val usuario = datos2.getString("usuario", "unknowed user").toString()
        view.findViewById<TextView>(R.id.tv_usuario).text = usuario

        view.findViewById<Button>(R.id.btt_modificarPassword).setOnClickListener {
            if (view.findViewById<EditText>(R.id.et_password).text.toString().isNotEmpty()) {
                var editor2: SharedPreferences.Editor = datos2.edit()
                editor2.putString("clave", view.findViewById<EditText>(R.id.et_password).text.toString())
                editor2.apply()
                findNavController().navigate(R.id.action_passwordFragment_to_FirstFragment)
            } else {
                Toast.makeText(activity, "Password no modificado", Toast.LENGTH_LONG).show()
            }
        }

    }
}