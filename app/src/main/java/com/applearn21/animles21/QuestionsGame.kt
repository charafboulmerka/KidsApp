package com.applearn21.animles21

import android.R.attr.*
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.onehundredapp19.mygamefaq5.QuestionModel
import kotlinx.android.synthetic.main.activity_questions_game.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random.Default.nextInt

class QuestionsGame : AppCompatActivity() {
    private val limitOfQuestionsForEachCategory = 5
    private val MaxOfWrongAnswers = 3
    private var postion = 0
    private var categoryPosition = 0
    private lateinit var mUtils: Utils
    private var ListOfCategories = arrayListOf<String>("alphabet","numbers","animals","days","months","colors","fruits")
    private lateinit var ListOfQuestions: List<ForItem>
    private lateinit var ListOfAnswers : List<ForItem>
    private var ListOfImgs = arrayListOf<Int>(R.id.img_answer1,R.id.img_answer2,R.id.img_answer3,R.id.img_answer4)
    private  var ListOfCards = arrayListOf<Int>(R.id.card_answer1,R.id.card_answer2,R.id.card_answer3,R.id.card_answer4)
    private var viewAnswerId = 0

    private var numberOfCorrectAnswers = 0
    private  var numberOfWrongAnswers = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions_game)
        mUtils= Utils(this)
        mUtils.setHeader("لــعــبة الأســــئـــلــة")
        ListOfCategories.shuffle()
        setNewCategory()
        mUtils.setBannerAd()
        mUtils.setFullScreenAD()


    }


    fun setNewCategory(){
        ListOfQuestions=mUtils.getSelectedListByName(ListOfCategories[categoryPosition])
        ListOfAnswers=ListOfQuestions.toMutableList()
        Collections.shuffle(ListOfQuestions)
        Collections.shuffle(ListOfAnswers)
        setQuestion()

    }

    fun AnswerSelected(view: View){
        ViewsState(false)
        if (view.id.equals(viewAnswerId)){
            AnswerEffects(view.id,true)
        }else{
            AnswerEffects(view.id,false)
        }
    }

    fun setQuestion(){
        Collections.shuffle(ListOfAnswers)
        Collections.shuffle(ListOfImgs)
    val item = ListOfQuestions[postion]
        val currentCategory = ListOfCategories[categoryPosition]
        if (currentCategory.equals("days") || currentCategory.equals("months")){
            my_question.text = item.text2
            view_qst.visibility = View.GONE
        }else{
            view_qst.visibility = View.VISIBLE
            my_question.text = item.text1
        }

        for (i in 0 until ListOfImgs.size){
            if (i.equals(0)){
                viewAnswerId=ListOfImgs[i]
                mUtils.getImageView(ListOfImgs[i]).setBackgroundResource(mUtils.getDrawableID(item.mBackground!!))
            }
            else{
                var img_choice = ListOfAnswers[i].mBackground!!
                if (img_choice.equals(item.mBackground!!)){
                    img_choice = ListOfAnswers[6].mBackground!!
                }
                mUtils.getImageView(ListOfImgs[i]).setBackgroundResource(mUtils.getDrawableID(img_choice))
            }
        }

        ViewsState(true)
    }



    fun ViewsState(state:Boolean){
        img_answer1.isEnabled=state
        img_answer2.isEnabled=state
        img_answer3.isEnabled=state
        img_answer4.isEnabled=state

    }

    fun AnswerEffects(viewId:Int,answer:Boolean){
        val imgSelected = mUtils.getImageView(viewId)
        val cardSelected = findViewById<CardView>(getCardBySelectedImg(viewId))
        //IN CASE IT'S A WRONG ANSWER
        val CardShouldBeSelected = findViewById<CardView>(getCardBySelectedImg(viewAnswerId))
        val imgShouldBeSelected = mUtils.getImageView(viewAnswerId)
    if (answer){
        mUtils.playSelectedMp3(R.raw.correct_answer_effect)
        //correct answer from the begining
        cardSelected.setBackgroundColor(Color.GREEN)
        //setImgMargin(imgSelected,3)
        numberOfCorrectAnswers+=1
        if (numberOfCorrectAnswers>mUtils.getBestSccore()){
            mUtils.saveBestSccore(numberOfCorrectAnswers)
        }
    }else{
        numberOfWrongAnswers+=1

        //wrong answer
        mUtils.playSelectedMp3(R.raw.wrong_answer_effect)
        cardSelected.setBackgroundColor(Color.RED)
       // setImgMargin(imgSelected,3)
        //the correct answer
        CardShouldBeSelected.setBackgroundColor(Color.GREEN)

        if (numberOfWrongAnswers.equals(MaxOfWrongAnswers)){
            //GAME OVER

            GameOver()
            return
        }
    }
        Handler().postDelayed({
            ClearViews()
        },1300)
    }

    fun GameOver(){
       val dialog = mUtils.getCommonDialog(R.layout.game_over_screen)
        dialog.findViewById<TextView>(R.id.tv_best_score).setText(mUtils.getBestSccore().toString())
        dialog.findViewById<Button>(R.id.btn_play_again).setOnClickListener {
            startActivity(Intent(this,QuestionsGame::class.java))
            finish()
            dialog.dismiss()
            mUtils.setFullScreenAD()
        }
        dialog.findViewById<Button>(R.id.btn_close).setOnClickListener {
        startActivity(Intent(this,MainActivity2::class.java))
            finish()
            dialog.dismiss()
        }
        mUtils.showDialog(dialog,R.raw.lose_sound_effect)
    }



    fun ClearViews(){
        for (i in ListOfCards ){
            val mCard = findViewById<CardView>(i)
            mCard.setBackgroundColor(Color.WHITE)
        }
/*
        for (i in ListOfImgs ){
            val mImg = findViewById<ImageView>(i)
            setImgMargin(mImg,0)
        }

 */

        tv_correct_answers.setText(numberOfCorrectAnswers.toString())
        tv_wrong_answers.setText(numberOfWrongAnswers.toString())
        postion+=1
        if (postion==5){
            mUtils.setFullScreenAD()
        }
        if (postion==ListOfQuestions.size || (postion % limitOfQuestionsForEachCategory) == 0){
            if (categoryPosition==ListOfCategories.size){
                showYouWonDialog()
            }else{
                categoryPosition+=1
                postion=0
                setNewCategory()
            }
        }else{
            setQuestion()
        }

    }

    fun showYouWonDialog(){
        val dialog = mUtils.getCommonDialog(R.layout.you_won_screen)
        dialog.findViewById<Button>(R.id.btn_gamecards_won_play_again).setOnClickListener {
            startActivity(Intent(this,QuestionsGame::class.java))
            finish()
            dialog.dismiss()
        }
        dialog.findViewById<Button>(R.id.btn_gamecards_won_close).setOnClickListener {
            startActivity(Intent(this,MainActivity2::class.java))
            finish()
            dialog.dismiss()
        }
        Handler().postDelayed({mUtils.showDialog(dialog,R.raw.win_sound_effect)},600)
    }
    fun getCardBySelectedImg(id:Int):Int{
        var selectedCard = 0
        when(id){
            R.id.img_answer1->{selectedCard=R.id.card_answer1}
            R.id.img_answer2->{selectedCard=R.id.card_answer2}
            R.id.img_answer3->{selectedCard=R.id.card_answer3}
            R.id.img_answer4->{selectedCard=R.id.card_answer4}
        }
        return selectedCard
    }

    fun setImgMargin(imgSelected:ImageView,mrg:Int){
        val layoutParams = imgSelected.layoutParams as FrameLayout.LayoutParams
        layoutParams.setMargins(mrg, mrg, mrg, mrg)
        imgSelected.layoutParams = layoutParams
    }

}