package com.applearn21.animles21

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.activity_samecards_game.*
import java.text.FieldPosition
import java.util.*
import kotlin.collections.ArrayList

class SamecardsGame : AppCompatActivity() {
    var ListOfCategories = arrayListOf("alphabet","numbers","animals","colors","fruits","days","months")
    var ListOfImgViews = arrayListOf(R.id.game2_img_1,R.id.game2_img_2,R.id.game2_img_3,R.id.game2_img_4,R.id.game2_img_5,R.id.game2_img_6
        ,R.id.game2_img_7,R.id.game2_img_8,R.id.game2_img_9,R.id.game2_img_10,R.id.game2_img_11,R.id.game2_img_12)

    var ListOfCardViews = arrayListOf(R.id.game2_card_1,R.id.game2_card_2,R.id.game2_card_3,R.id.game2_card_4,R.id.game2_card_5,R.id.game2_card_6
        ,R.id.game2_card_7,R.id.game2_card_8,R.id.game2_card_9,R.id.game2_card_10,R.id.game2_card_11,R.id.game2_card_12)
    var ListOfCorrectAnswer = ArrayList<String>()
    private var MaxOfWrongAnswers = 3
    private var mAlpha = 1f
    private var categoryPosition = 0
    private lateinit var mUtils: Utils
    private lateinit var ListOfPictures: List<ForItem>

    var numberOfClicks = 0
    var img1Tag = "a"
    var img2Tag = "f"
    private  var numberOfWrongAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_samecards_game)
        mUtils= Utils(this)
        categoryPosition=mUtils.getCardsGameCategoryPos()
        setPictures()
        mUtils.setHeader2(MaxOfWrongAnswers-numberOfWrongAnswers)
        mUtils.setBannerAd()

    }

    fun setMaxWrongAnswersByLevel(){
        when(categoryPosition+1){
            1->{
              MaxOfWrongAnswers=20
              mAlpha=1f
            }
            2->{
                MaxOfWrongAnswers=17
                mAlpha=0.8f
            }
            3->{
                MaxOfWrongAnswers=14
                mAlpha=0.7f
            }
            4->{
                MaxOfWrongAnswers=12
                mAlpha=0.6f
            }
            5->{
                MaxOfWrongAnswers=10
                mAlpha=0.5f
            }
            6->{
                MaxOfWrongAnswers=8
                mAlpha=0.4f
            }
            7->{
                MaxOfWrongAnswers=6
                mAlpha=0.3f
            }
        }


    }

    fun setPictures(){
        setMaxWrongAnswersByLevel()
         numberOfClicks = 0
         img1Tag = "null1"
         img2Tag = "null2"
         numberOfWrongAnswers = 0
        Handler().postDelayed({mUtils.setHeader2(MaxOfWrongAnswers-numberOfWrongAnswers)},500)


        ListOfCorrectAnswer.clear()
        setViewsVisibility(View.VISIBLE)
        setViewsEnabelity(false)
        clearViewsColor()

        ListOfPictures=mUtils.getSelectedListByName(ListOfCategories[categoryPosition])
        Collections.shuffle(ListOfImgViews)
        Collections.shuffle(ListOfPictures)
        for (i in 0 until (ListOfImgViews.size/2)){
            val img_name = ListOfPictures[i].mBackground
            setBackgroundAndTag(i,img_name!!)
            setBackgroundAndTag(i+6,img_name)
        }

        Handler().postDelayed({
            setViewsVisibility(View.INVISIBLE)
            setViewsEnabelity(true)
        },1500)
    }

    fun GameTwoSelected(view: View){
        Log.e("CHARAF_CLICKED","00")
        mUtils.playSelectedMp3(R.raw.select_effect)
        var card = findViewById<CardView>(view.id)
        card.isEnabled = false
        card.setBackgroundColor(resources.getColor(R.color.Grey))
        val SelectedImg = mUtils.getImageView(getTheCorrectID(view.id))
        SelectedImg.visibility = View.VISIBLE
        SelectedImg.alpha = 1f
        numberOfClicks+=1


        when(numberOfClicks){
            1-> img1Tag=SelectedImg.tag as String
            2-> img2Tag=SelectedImg.tag as String
        }

        if (numberOfClicks == 2){
            setViewsEnabelity(false)
            if (img1Tag==img2Tag){
                numberOfClicks=0
                ListOfCorrectAnswer.add(img1Tag)
                ListOfCorrectAnswer.add(img2Tag)
                setViewsEnabelity(true)
                if (ListOfCorrectAnswer.size==ListOfImgViews.size){
                    categoryPosition+=1
                    mUtils.saveCardsGameCategoryPos(categoryPosition)
                    if (categoryPosition>ListOfCategories.size-1){
                        categoryPosition=0
                        showYouWonDialog()
                    }
                    else{
                    setNextLevelDialog()
                    }
                }
                mUtils.playSelectedMp3(R.raw.correct_answer_effect)
            }
            else{
                numberOfWrongAnswers+=1
                mUtils.setHeader2(MaxOfWrongAnswers-numberOfWrongAnswers)
                if (numberOfWrongAnswers%5==0){
                    mUtils.setFullScreenAD()
                }
                if (numberOfWrongAnswers==MaxOfWrongAnswers){
                    GameOverCards()
                    return
                }

                Handler().postDelayed({
                    setViewsVisibility(View.INVISIBLE)
                            setViewsEnabelity(true)
                    },1000)
                numberOfClicks=0
            }

        }

    }


    fun showYouWonDialog(){
        val dialog = mUtils.getCommonDialog(R.layout.you_won_screen)
        dialog.findViewById<Button>(R.id.btn_gamecards_won_play_again).setOnClickListener {
            startActivity(Intent(this,SamecardsGame::class.java))
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

    fun setNextLevelDialog(){
        val dialog = mUtils.getCommonDialog(R.layout.next_level_screen)
        dialog.findViewById<Button>(R.id.btn_cardsgame_nextlevel).setOnClickListener {
            setPictures()
            dialog.dismiss()
            mUtils.setFullScreenAD()
        }
        dialog.findViewById<Button>(R.id.btn_cardsgame_close).setOnClickListener {
            startActivity(Intent(this,MainActivity2::class.java))
            finish()
            dialog.dismiss()
        }
        //mUtils.stopPlaying()
        Handler().postDelayed({mUtils.showDialog(dialog,R.raw.next_level_effect)},600)

        //playMp3(R.raw.next_level_effect)

    }



    fun GameOverCards(){
        val dialog = mUtils.getCommonDialog(R.layout.game_over_screen)
        dialog.findViewById<ImageView>(R.id.imageView_gameOver).setBackgroundResource(R.drawable.game_over_cards)
        dialog.findViewById<TextView>(R.id.tv_best_score).visibility=View.GONE
        dialog.findViewById<Button>(R.id.btn_play_again).setOnClickListener {
            startActivity(Intent(this,SamecardsGame::class.java))
            finish()
            dialog.dismiss()
        }
        dialog.findViewById<Button>(R.id.btn_close).setOnClickListener {
            startActivity(Intent(this,MainActivity2::class.java))
            finish()
            dialog.dismiss()
        }
        Handler().postDelayed({mUtils.showDialog(dialog,R.raw.lose_sound_effect)},600)

    }

    fun getTheCorrectID(id:Int):Int{
        var img_id = 0
        when(id){
            R.id.game2_card_1 -> img_id=R.id.game2_img_1
            R.id.game2_card_2 -> img_id=R.id.game2_img_2
            R.id.game2_card_3 -> img_id=R.id.game2_img_3
            R.id.game2_card_4 -> img_id=R.id.game2_img_4
            R.id.game2_card_5 -> img_id=R.id.game2_img_5
            R.id.game2_card_6 -> img_id=R.id.game2_img_6
            R.id.game2_card_7 -> img_id=R.id.game2_img_7
            R.id.game2_card_8 -> img_id=R.id.game2_img_8
            R.id.game2_card_9 -> img_id=R.id.game2_img_9
            R.id.game2_card_10 -> img_id=R.id.game2_img_10
            R.id.game2_card_11 -> img_id=R.id.game2_img_11
            R.id.game2_card_12 -> img_id=R.id.game2_img_12

            R.id.game2_img_1 -> img_id=R.id.game2_card_1
            R.id.game2_img_2 -> img_id=R.id.game2_card_2
            R.id.game2_img_3 -> img_id=R.id.game2_card_3
            R.id.game2_img_4 -> img_id=R.id.game2_card_4
            R.id.game2_img_5 -> img_id=R.id.game2_card_5
            R.id.game2_img_6 -> img_id=R.id.game2_card_6
            R.id.game2_img_7 -> img_id=R.id.game2_card_7
            R.id.game2_img_8 -> img_id=R.id.game2_card_8
            R.id.game2_img_9 -> img_id=R.id.game2_card_9
            R.id.game2_img_10 -> img_id=R.id.game2_card_10
            R.id.game2_img_11 -> img_id=R.id.game2_card_11
            R.id.game2_img_12 -> img_id=R.id.game2_card_12


        }
        return img_id
    }



    fun setBackgroundAndTag(position:Int,BackgroundPicName:String){
        var mImg = mUtils.getImageView(ListOfImgViews[position])
        mImg.setBackgroundResource(mUtils.getDrawableID(BackgroundPicName))
        mImg.tag = BackgroundPicName
    }



    fun setViewsVisibility(state:Int){
        for (i in ListOfImgViews){
            if (!ListOfCorrectAnswer.contains(mUtils.getImageView(i).tag)){
                mUtils.getImageView(i).visibility = state
            }
            if (img1Tag=="null1"){
                mUtils.getImageView(i).alpha = mAlpha
            }else{
                mUtils.getImageView(i).alpha = 1f
            }
        }
    }

    fun setViewsEnabelity(state:Boolean){
        for (i in ListOfImgViews){
            if (!ListOfCorrectAnswer.contains(mUtils.getImageView(i).tag)){
                mUtils.getImageView(i).isEnabled = state
                mUtils.getCardView(getTheCorrectID(i)).isEnabled = state
                if(state)
                mUtils.getCardView(getTheCorrectID(i)).setBackgroundColor(resources.getColor(R.color.White))
            }

        }
    }




    fun clearViewsColor(){
        for(i in ListOfCardViews){
            mUtils.getCardView(i).setBackgroundColor(resources.getColor(R.color.White))
        }
    }
}