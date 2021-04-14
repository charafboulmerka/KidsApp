package com.applearn21.animles21

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class mAdapter:RecyclerView.Adapter<mAdapter.mViewHolder> {
    var mCtx:Context?=null
    var mList= listOf<ForItem>()
    lateinit var mUtis:Utils
    var mPlayer: MediaPlayer?=null
    constructor(mCtx:Context,mList:List<ForItem>){
        this.mCtx=mCtx
        this.mList=mList
        this.mUtis= Utils(mCtx)
        //this.mPlayer=mPlayer
    }



 inner class mViewHolder(view:View):RecyclerView.ViewHolder(view),View.OnClickListener{
     var text_filed:TextView=view.findViewById(R.id.card_txt)
     var img_filed:ImageView=view.findViewById(R.id.card_img)
     var card_filed:CardView=view.findViewById(R.id.card_mcard)


     override fun onClick(v: View?) {

     }

 }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_design,parent,false)
        return  mViewHolder(view)
    }

    override fun getItemCount(): Int {
      return mList.size
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        var item = mList[position]
        holder.text_filed.setText(item.text1)
        holder.img_filed.setBackgroundResource(mUtis.getDrawableID(item.mBackground!!))
        holder.card_filed.setOnClickListener {
            playSelectedMp3(mUtis.getRawID(item.mMp3!!))
        }
    }

     fun stopPlaying(){
        if (mPlayer!=null){
            mPlayer!!.stop()
            mPlayer!!.release()
            mPlayer=null
        }
    }
    fun playSelectedMp3(mp3File:Int){

        try{
            stopPlaying()
            mPlayer = MediaPlayer.create(mCtx,mp3File)
            mPlayer!!.start()
        }catch (e:Exception){}

    }

}