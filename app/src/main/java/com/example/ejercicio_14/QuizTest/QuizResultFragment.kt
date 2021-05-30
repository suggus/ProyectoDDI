package com.example.ejercicio_14.QuizTest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.ejercicio_14.R


/**
 * A simple [Fragment] subclass.
 * Use the [QuizResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizResultFragment : Fragment() {

    var answers: Int = 0
    var total: String = ""
    var rating: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        answers = arguments?.getInt("correct_answers") ?: 0
        total = arguments?.getString("total_questions") ?: "0 preguntas"
        rating = arguments?.getFloat("total") ?: 0F
        var rating2 = answers.toFloat() // rating * view.findViewById<RatingBar>(R.id.rb_Valoracion).numStars


        view.findViewById<TextView>(R.id.tv_score).text = "You score is $answers out of $total".toString()
        view.findViewById<RatingBar>(R.id.rb_Valoracion).rating = rating2

        view.findViewById<Button>(R.id.btn_finish).setOnClickListener {
            findNavController().navigate(R.id.action_quizResultFragment_to_fourthFragment)
        }

    }

}