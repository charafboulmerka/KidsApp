package com.applearn21.animles21

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_show_data.*
import java.util.*

class ShowData : AppCompatActivity() {
    private var mPlayer: MediaPlayer?=null
     private lateinit var recyclerView: RecyclerView
     private lateinit var viewAdapter : RecyclerView.Adapter<*>
     private lateinit var ViewlayoutManager:RecyclerView.LayoutManager
    private lateinit var mUtils :Utils
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data)
        mUtils = Utils(this)

        mUtils.setHeader("")
        val data = intent.extras
        val selected_list = data!!.getString("mList")
        ViewlayoutManager= GridLayoutManager(this, 2)
        viewAdapter = mAdapter(this,mUtils.getSelectedListByName(selected_list!!))
        recyclerView = findViewById<RecyclerView>(R.id.mRec).apply {
            setHasFixedSize(true)
            ViewlayoutManager.isAutoMeasureEnabled = false
            layoutManager=ViewlayoutManager
            adapter=viewAdapter
        }


    }



    override fun onDestroy() {
        try {
            (viewAdapter as mAdapter).stopPlaying()
        }catch (e:Exception){}
        super.onDestroy()
    }


}