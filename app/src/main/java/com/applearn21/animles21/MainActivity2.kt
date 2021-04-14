package com.applearn21.animles21

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main2.*

 private lateinit var mUtils: Utils
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        mUtils= Utils(this)
        mUtils.setBannerAd()

    }

    fun CardClicked(view:View){
        var card = view.id
        when(card){
            R.id.card_alphabet->{startActivity(Intent(this,ShowData::class.java).putExtra("mList","alphabet"))}
            R.id.card_numbers->{startActivity(Intent(this,ShowData::class.java).putExtra("mList","numbers"))}
            R.id.card_animals->{startActivity(Intent(this,ShowData::class.java).putExtra("mList","animals"))}
            R.id.card_days->{startActivity(Intent(this,ShowData::class.java).putExtra("mList","days"))}
            R.id.card_months->{startActivity(Intent(this,ShowData::class.java).putExtra("mList","months"))}
            R.id.card_colors->{startActivity(Intent(this,ShowData::class.java).putExtra("mList","colors"))}
            R.id.card_fruits->{startActivity(Intent(this,ShowData::class.java).putExtra("mList","fruits"))}
            R.id.card_games->{startActivity(Intent(this,GamesList::class.java))}
        }
    }




}