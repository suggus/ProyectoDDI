package com.example.ejercicio_14.QuizTest

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.ejercicio_14.Libro
import com.example.ejercicio_14.MainActivity
import com.example.ejercicio_14.R

class QuizQuestionsFragment : Fragment(), View.OnClickListener {

    var tematica: String = ""
    private var mCurrentPosition: Int = 1
    private lateinit var mQuestionList: List<Question>
    private var mSelectedOptionPosition: Int = 0

    //For the ResultActivity
    private var mCorrectAnswers: Int = 0

    lateinit var pregunta: TextView
    lateinit var barraProgreso: ProgressBar
    lateinit var barraProgresoTexto: TextView
    lateinit var opcion1: TextView
    lateinit var opcion2: TextView
    lateinit var opcion3: TextView
    lateinit var opcion4: TextView
    lateinit var submit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pregunta = view.findViewById(R.id.tv_question)
        barraProgreso = view.findViewById(R.id.progressBar)
        barraProgresoTexto = view.findViewById(R.id.tv_progress)
        opcion1 = view.findViewById(R.id.tv_option_one)
        opcion2 = view.findViewById(R.id.tv_option_two)
        opcion3 = view.findViewById(R.id.tv_option_three)
        opcion4 = view.findViewById(R.id.tv_option_four)
        submit = view.findViewById(R.id.btn_submit)

        tematica = arguments?.getString("tematica") ?: "uuu"
        Log.i("Questions Thematic", tematica)
        var question: Libro = Libro(0, "", "", "", "")

        if (tematica == "uuu") {
//            bBorrar.isEnabled = false
//            bModificar.isEnabled = false
//            bInsertar.isEnabled = true
            activity?.setTitle("Insertar Quiz")
            Toast.makeText(activity, "No has seleccionado ninguna temática", Toast.LENGTH_SHORT)
                .show()

        } else {
//            bBorrar.isEnabled = true
//            bModificar.isEnabled = true
//            bInsertar.isEnabled = false
            activity?.setTitle("Modificar/Borrar Quiz")
            (activity as MainActivity).miViewModel3.buscarPorTematica(tematica)
            //var liburua : Libro
            (activity as MainActivity).miViewModel3.listaThematicQuestions.observe(activity as MainActivity) {
                it?.let {
                    mQuestionList = it
                    Log.i("Questions Size", it.size.toString())
/*
                    val currentPosition = 1
                    val question = it[currentPosition -1]

                    view.findViewById<ProgressBar>(R.id.progressBar).progress = currentPosition
                    view.findViewById<TextView>(R.id.tv_progress).setText("$currentPosition" + "/" + it.size.toString())

                    view.findViewById<TextView>(R.id.tv_question).text = question.question
                    view.findViewById<TextView>(R.id.tv_option_one).text = question.optionOne
                    view.findViewById<TextView>(R.id.tv_option_two).text = question.optionTwo
                    view.findViewById<TextView>(R.id.tv_option_three).text = question.optionThree
                    view.findViewById<TextView>(R.id.tv_option_four).text = question.optionFour
*/
                    setQuestion()
                }

            }

        }

        //All of our buttons should have an OnClickListener
        opcion1.setOnClickListener(this)
        opcion2.setOnClickListener(this)
        opcion3.setOnClickListener(this)
        opcion4.setOnClickListener(this)
        submit.setOnClickListener(this)

    }

    private fun setQuestion() {

        if (mQuestionList.isEmpty()) {
            Toast.makeText(activity, "Cuestionario en reformas", Toast.LENGTH_SHORT).show()
        } else {
            val question = mQuestionList[mCurrentPosition - 1]

            //All the options are back to the default appearance before we select one of them
            defaultOptionsView()

            if (mCurrentPosition == mQuestionList.size) {
                submit.text = "FINISH"
            } else {
                submit.text = "SUBMIT"
            }

            barraProgreso.progress = mCurrentPosition
            barraProgresoTexto.setText("$mCurrentPosition" + "/" + mQuestionList.size.toString())

            pregunta.text = question.question
            opcion1.text = question.optionOne
            opcion2.text = question.optionTwo
            opcion3.text = question.optionThree
            opcion4.text = question.optionFour
        }
    }

    //Defining the background for the selected option, each time we click it
    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, opcion1)
        options.add(1, opcion2)
        options.add(2, opcion3)
        options.add(3, opcion4)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.default_option_border_bg)
        }
    }

    //Selected option should have another color for the text and background
    //We prepare a function which takes care of that (selectedOptionView)
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> {
                selectedOptionView(opcion1, 1)
            }
            R.id.tv_option_two -> {
                selectedOptionView(opcion2, 2)
            }
            R.id.tv_option_three -> {
                selectedOptionView(opcion3, 3)
            }
            R.id.tv_option_four -> {
                selectedOptionView(opcion4, 4)
            }
            //First we create the function answerView to know the answer
            //When press submit the correct answer gets a color and the incorrect answer gets a color as well
            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionList.size -> {
                            setQuestion()
                        }
                        else -> {
                            Toast.makeText(
                                activity,
                                "You have successfully completed the Quiz", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    //The user has selected an option
                    //Check the correct answer that is inside of this question
                } else {
                    val question = mQuestionList[mCurrentPosition - 1]
                    if (question.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswers++
                    }
                    //the correct answer is set to green in any case
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    //When we are at the last question...
                    if (mCurrentPosition == mQuestionList.size) {
                        submit.text = "FINISH"
                    } else {
                        submit.text = "Go to Next Question"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.selected_option_border_bg)

    }

    //This function takes care of assigning the right color to our options
    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                opcion1.background = ContextCompat.getDrawable(requireContext(), drawableView)
            }
            2 -> {
                opcion2.background = ContextCompat.getDrawable(requireContext(), drawableView)
            }
            3 -> {
                opcion3.background = ContextCompat.getDrawable(requireContext(), drawableView)
            }
            4 -> {
                opcion4.background = ContextCompat.getDrawable(requireContext(), drawableView)
            }
        }
    }


}