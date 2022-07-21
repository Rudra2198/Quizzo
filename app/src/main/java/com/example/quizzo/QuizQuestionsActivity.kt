package com.example.quizzo

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.AdaptiveIconDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition : Int = 1
    private var mQuestionsList:ArrayList<Question>?=null
    private var mSelectedOptionPosition : Int = 0
    private var mUserName : String? = null
    private var mCorrectAnswers : Int = 0

    private var progressBar: ProgressBar?=null
    private var tvProgress: TextView?=null
    private var tvQuestion:TextView?=null
    private var ivImage: ImageView?=null

    private var tvOptionOne:TextView?=null
    private var tvOptionTwo:TextView?=null
    private var tvOptionThree:TextView?=null
    private var tvOptionFour:TextView?=null
    private var btnSubmit : Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_image)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        mQuestionsList = Constants.getQuestions()

        setQuestion()

    }

    private fun setQuestion() {

        defaultOptionsView()
        val question: Question = mQuestionsList!![mCurrentPosition  - 1]
        ivImage?.setImageResource(question.image)
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition /${progressBar?.max}"
        tvQuestion?.text = question.question
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour

        if(mCurrentPosition == mQuestionsList!!.size){
            btnSubmit?.text = "FINISH"
        }else{
            btnSubmit?.text = "SUBMIT"
        }
    }

    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        tvOptionOne?.let {
            options.add(0, it)
        }
        tvOptionTwo?.let {
            options.add(1, it)
        }
        tvOptionThree?.let {
            options.add(2, it)
        }
        tvOptionFour?.let {
            options.add(3, it)
        }

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this@QuizQuestionsActivity,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOptionView(tv:TextView, selectedOptionNum: Int){
        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }


    override fun onClick(view: View?) {
         when(view?.id){

              R.id.tv_option_one -> {
                  tvOptionOne?.let{
                      selectedOptionView(it,1)
                  }
              }

             R.id.tv_option_two -> {
                 tvOptionTwo?.let{
                     selectedOptionView(it,2)
                 }
             }

             R.id.tv_option_three -> {
                 tvOptionThree?.let{
                     selectedOptionView(it,3)
                 }
             }

             R.id.tv_option_four -> {
                 tvOptionFour?.let{
                     selectedOptionView(it,4)
                 }
             }

             R.id.btn_submit ->{

                 if(mSelectedOptionPosition == 0){
                     mCurrentPosition++

                     when{
                         mCurrentPosition <= mQuestionsList!!.size ->{
                            setQuestion()
                         }
                         else ->{
                             val intent = Intent(this,ResultActivity::class.java)
                             intent.putExtra(Constants.USER_NAME,mUserName)
                             intent.putExtra(Constants.CORRECT_ANS,mCorrectAnswers)
                             intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList?.size)
                             startActivity(intent)
                             finish()
                         }
                     }
                 }else{
                     val question = mQuestionsList?.get(mCurrentPosition-1)
                     if(question!!.correctAnswer != mSelectedOptionPosition ){
                         answerView(mSelectedOptionPosition,R.drawable.wrong_option_border_bg)
                     }else{
                         mCorrectAnswers++
                     }
                     answerView(question.correctAnswer, R.drawable.correct_option_border_bg )

                     if(mCurrentPosition == mQuestionsList!!.size){
                         btnSubmit?.text = "FINISH"
                     }else{
                         btnSubmit?.text = "GO TO NEXT QUESTION"
                     }

                     mSelectedOptionPosition = 0
                 }

             }
         }
    }

    private fun answerView(answer : Int, drawableView : Int){
        when(answer){
            1 ->{
                tvOptionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

            2 ->{
                tvOptionTwo?.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }

            3 ->{
                tvOptionThree?.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }

            4 ->{
                tvOptionFour?.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }
        }
    }
}