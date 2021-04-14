package com.applearn21.animles21

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class GamesList : AppCompatActivity() {
    private lateinit var mUtils: Utils
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_list)
        mUtils = Utils(this)
        mUtils.setHeader("قـــــائــــمة الألــــعــــاب")
        mUtils.setBannerAd()
    }

    fun GamesMenu(view:View){
        mUtils.playSelectedMp3(R.raw.select_effect)
        when(view.id){
        R.id.mCard_game_cards->{startActivity(Intent(this,SamecardsGame::class.java))}
        R.id.mCard_game_questions->{startActivity(Intent(this,QuestionsGame::class.java))}
        }
    }
}