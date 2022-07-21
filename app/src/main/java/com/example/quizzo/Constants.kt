package com.example.quizzo

object Constants {

    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS : String = "total_questions"
    const val CORRECT_ANS : String = "correct_answers"


    fun getQuestions():ArrayList<Question>{

        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1,"What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Argentina","Spain",
            "Egypt","Seychelles", 1

        )
        questionsList.add(que1)

        val que2 = Question(
            2,"What country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "America","Australia",
            "New zealand","Uzbekistan", 2

        )
        questionsList.add(que2)

        val que3 = Question(
            3,"What country does this flag belong to?",
            R.drawable.ic_flag_of_belgium,
            "Germany","Belgium",
            "Armenia","France", 2

        )
        questionsList.add(que3)

        val que4 = Question(
            4,"What country does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "Japan","India",
            "Pakistan","Brazil", 4

        )
        questionsList.add(que4)

        val que5 = Question(
            5,"What country does this flag belong to?",
            R.drawable.ic_flag_of_denmark,
            "Denmark","Poland",
            "Brazil","UK", 1

        )
        questionsList.add(que5)

        val que6 = Question(
            6,"What country does this flag belong to?",
            R.drawable.ic_flag_of_fiji,
            "America","Vietnam",
            "Fiji","UAE", 3

        )
        questionsList.add(que6)

        val que7 = Question(
            7,"What country does this flag belong to?",
            R.drawable.ic_flag_of_germany,
            "Greece","Australia",
            "Germany","Austria", 3

        )
        questionsList.add(que7)

        val que8 = Question(
            8,"What country does this flag belong to?",
            R.drawable.ic_flag_of_india,
            "India","China",
            "Kuwait","Australia", 1

        )
        questionsList.add(que8)

        val que9 = Question(
            9,"What country does this flag belong to?",
            R.drawable.ic_flag_of_new_zealand,
            "Russia","New Zealand",
            "Armenia","Chile", 2

        )
        questionsList.add(que9)

        return questionsList
    }
}