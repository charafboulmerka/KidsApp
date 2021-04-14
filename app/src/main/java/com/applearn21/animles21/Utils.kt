package com.applearn21.animles21

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.onehundredapp19.mygamefaq5.QuestionModel
import kotlinx.android.synthetic.main.header.*
import kotlinx.android.synthetic.main.header2.*
//import com.google.android.gms.ads.*
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import java.util.*
import java.util.logging.Handler
import kotlin.collections.ArrayList

class Utils {
    lateinit var mAdView : AdView
    private var mInterstitialAd: InterstitialAd? = null

    var mCtx: Context?=null
    var mShared :SharedPreferences
    private var mPlayer:MediaPlayer?=null

    constructor(mCtx:Context){
        MobileAds.initialize(mCtx) {}
      this.mCtx=mCtx
      this. mShared = mCtx.getSharedPreferences("BEST",Context.MODE_PRIVATE)
    }



    fun getDrawableID(name:String):Int{
        return  mCtx!!.resources.getIdentifier(name, "drawable", mCtx!!.packageName)
    }

    fun getRawID(name:String):Int{
        return  mCtx!!.resources.getIdentifier(name, "raw", mCtx!!.packageName)
    }


    fun setFont(){
        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/myfont.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }




    fun setBannerAd(){
        mAdView = (mCtx as Activity).findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    fun setFullScreenAD(){
        val adRequest = AdRequest.Builder().build()
        val act = (mCtx as Activity)
        InterstitialAd.load(act,act.getString(R.string.admobFull), adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
                mInterstitialAd!!.show(act)
            }
        })
    }



    fun getSelectedListByName(name:String):List<ForItem>{
        var selectedList = listOf<ForItem>()
        when(name){
            "alphabet"-> {selectedList = Alphabet_List}
            "numbers"-> selectedList =  Numbers_List
            "animals"-> selectedList =  Animals_List
            "days"-> selectedList =  Days_List
            "months"-> selectedList =  Months_List
            "colors"-> selectedList =  Colors_List
            "fruits"-> selectedList =  Fruits_List
        }
        return selectedList
    }



   private var Alphabet_List = listOf(
        ForItem("ألـــف","","alphabet_pic_alf","alphabet_alf"),
        ForItem("بـــــاء","","alphabet_pic_ba","alphabet_ba"),
        ForItem("تــــاء","","alphabet_pic_ta","alphabet_ta"),
        ForItem("ثـــــــاء","","alphabet_pic_tha","alphabet_tha"),
        ForItem("جــــيــم","","alphabet_pic_jim","alphabet_jim"),
        ForItem("حـــــاء","","alphabet_pic_7a","alphabet_haa"),
        ForItem("خــــــاء","","alphabet_pic_kha","alphabet_kha"),
        ForItem("دال","","alphabet_pic_da","alphabet_dal"),
        ForItem("ذال","","alphabet_pic_dha","alphabet_thal"),
        ForItem("راء","","alphabet_pic_ra","alphabet_raa"),
        ForItem("زاي","","alphabet_pic_za","alphabet_zay"),
        ForItem("ســــيـــن","","alphabet_pic_sin","alphabet_sin"),
        ForItem("شـــيـــن","","alphabet_pic_chin","alphabet_chin"),
        ForItem("صـــــاد","","alphabet_pic_sad","alphabet_sad"),
        ForItem("ضــــاد","","alphabet_pic_dad","alphabet_dad"),
        ForItem("طــــاء","","alphabet_pic_taa","alphabet_taa"),
        ForItem("ظــــاء","","alphabet_pic_daa","alphabet_daa"),
        ForItem("عــــــيـــن","","alphabet_pic_3in","alphabet_aiin"),
        ForItem("غـــــيـــن","","alphabet_pic_ghin","alphabet_ghin"),
        ForItem("فـــــاء","","alphabet_pic_fa","alphabet_faa"),
        ForItem("قــــــاف","","alphabet_pic_9a","alphabet_9af"),
        ForItem("كــــــاف","","alphabet_pic_kaf","alphabet_kaf"),
        ForItem("لام","","alphabet_pic_lam","alphabet_lam"),
        ForItem("مــــيـــم","","alphabet_pic_ma","alphabet_mim"),
        ForItem("نـــــــون","","alphabet_pic_na","alphabet_noun"),
        ForItem("هـــــاء","","alphabet_pic_haa","alphabet_haa"),
        ForItem("واو","","alphabet_pic_waw","alphabet_waw"),
        ForItem("يـــــــاء","","alphabet_pic_ya","alphabet_yaa")
    )

    var Numbers_List = listOf(
        ForItem("صـــفــر","","numbers_pic_0","numbers_0"),
        ForItem("واحـــد","","numbers_pic_1","numbers_1"),
        ForItem("إثــنـــان","","numbers_pic_2","numbers_2"),
        ForItem("ثــلاثــة","","numbers_pic_3","numbers_3"),
        ForItem("أربــــعـة","","numbers_pic_4","numbers_4"),
        ForItem("خـــمـــســة","","numbers_pic_5","numbers_5"),
        ForItem("ســــتـــة","","numbers_pic_6","numbers_6"),
        ForItem("ســــبــعة","","numbers_pic_7","numbers_7"),
        ForItem("ثــمــانية","","numbers_pic_8","numbers_8"),
        ForItem("تـــســـعــة","","numbers_pic_9","numbers_9"),
        ForItem("عــــشـــرة","","numbers_pic_10","numbers_10"),
        ForItem("إحــدى عشـر","","numbers_pic_11","numbers_11"),
        ForItem("إثـــنــا عــشـر","","numbers_pic_12","numbers_12"),
        ForItem("ثـلاثـة عـشـر","","numbers_pic_13","numbers_13"),
        ForItem("أربــعــة عـشـر","","numbers_pic_14","numbers_14"),
        ForItem("خـمـسـة عـشـر","","numbers_pic_15","numbers_15"),
        ForItem("ســـتــة عــشــر","","numbers_pic_16","numbers_16"),
        ForItem("ســبــعــة عـشـر","","numbers_pic_17","numbers_17"),
        ForItem("ثــمـانية عـشـر","","numbers_pic_18","numbers_18"),
        ForItem("تـسـعـة عـشـر","","numbers_pic_19","numbers_19"),
        ForItem("عـشــرون","","numbers_pic_20","numbers_20")
    )

    private var Animals_List = listOf(
        ForItem("جــــمـــل","","animals_pic_camel","animals_camel"),
        ForItem("قــــــط","","animals_pic_cat","animals_cat"),
        ForItem("دجـــــاجة","","animals_pic_chicken","animals_chicken"),
        ForItem("ديـــــك","","animals_pic_cock","animals_cock"),
        ForItem("بــــقــــرة","","animals_pic_cow","animals_cow"),
        ForItem("تـــمـــســـاح","","animals_pic_crocodile","animals_crocodile"),
        ForItem("حـــمـــار","","animals_pic_donkey","animals_danky"),
        ForItem("كـــــلـــب","","animals_pic_dog","animals_dog"),
        ForItem("فـــــيــــل","","animals_pic_elephant","animals_elphanet"),
        ForItem("ســـمـــكة","","animals_pic_fish","animals_fish"),
        ForItem("غـــزالـــة","","animals_pic_guzel","animals_gazel"),
        ForItem("زرافــــة","","animals_pic_giraffe","animals_giraffe"),
        ForItem("حـــصـــان","","animals_pic_horse","animals_horse"),
        ForItem("أســـــد","","animals_pic_lion","animals_lion"),
        ForItem("قــــرد","","animals_pic_monkey","animals_monkey"),
        ForItem("أرنـــب","","animals_pic_rabbit","animals_rabbit"),
        ForItem("خـــروف","","animals_pic_sheep","animals_sheep"),
        ForItem("ســنــجــاب","","animals_pic_squirrel","animals_squirrle"),
        ForItem("نــــمــــر","","animals_pic_tiger","animals_tiger"),
        ForItem("دب","","animals_pic_bear","animals_bear"),
        ForItem("ضـفـــدع","","animals_pic_frog","animals_frog"),
        ForItem("كـــنـــغر","","animals_pic_kangaroo","animals_kangaroo"),
        ForItem("سـلـحـفـــاة","","animals_pic_turtle","animals_turtle"),
        ForItem("ذئــــــب","","animals_pic_wolf","animals_wolf")

    )

    private var Days_List = listOf(
        ForItem("الــســبــت","قبل يوم الأحد يكون يوم","days_pic_saturday","days_saturday"),
        ForItem("الأحــــد","اول يوم في الأسبوع هو","days_pic_sunday","days_sunday"),
        ForItem("الإثــنــين","يأتي بعد يوم الأحد يوم ","days_pic_monday","days_monday"),
        ForItem("الثـلاثـــاء","يأتي بعد يوم الإثنين يوم","days_pic_tuesday","days_tuesday"),
        ForItem("الأربـعاء","قبل يوم الخميس يكون يوم","days_pic_wednsday","days_wednsday"),
        ForItem("الـخـميس","اليوم الذي يحتوي حرف الخاء هو","days_pic_thuresday","days_thursday"),
        ForItem("الـجـمـعـة","يأتي بعد يوم الخميس يوم","days_pic_friday","days_friday")
    )

    private var Months_List = listOf(
        ForItem("كـانــون الــثـــاني","الشهر الأول في السنة هو","months_pic_janurary","months_january"),
        ForItem("شــبــاط","ثاني شهر في السنة هو","months_pic_february","months_february"),
        ForItem("آذار","يبدأ فصل الربيع في","months_pic_march","months_march"),
        ForItem("نــيـســان","الشهر الرابع في السنة هو","months_pic_april","months_april"),
        ForItem("أيــــار","الشهر الخامس في السنة هو","months_pic_may","months_may"),
        ForItem("حــزيــران","يبدأ فصل الصيف في","months_pic_june","months_june"),
        ForItem("تــمــوز ","بعد شهر حزيران يأتي شهر","months_pic_july","months_july"),
        ForItem("آب ","بعد شهر تموز يأتي شهر","months_pic_august","months_august"),
        ForItem("أيــلــول ","يبدأ فصل الخريف في الشهر","months_pic_september","months_september"),
        ForItem("تــشـرين الأول","قبل شهر تشرين الثاني يكون شهر","months_pic_october","months_october"),
        ForItem("تــشـرين الثاني","الشهر ما قبل الأخير في السنة هو","months_pic_november","months_november"),
        ForItem("كــانـون الأول","الشهر الأخير في السنة هو","months_pic_december","months_december")
    )

    private var Colors_List = listOf(
        ForItem("أســـود","","colors_pic_black","colors_black"),
        ForItem("أزرق","","colors_pic_blue","colors_blue"),
        ForItem("بـــنـــي","","colors_pic_brown","colors_brown"),
        ForItem("أخــــضــر","","colors_pic_green","colors_green"),
        ForItem("رمــــادي","","colors_pic_grey","colors_grey"),
        ForItem("برتـــقالـي","","colors_pic_orange","colors_orange"),
        ForItem("وردي","","colors_pic_pink","colors_pink"),
        ForItem("بنــفـسـجي","","colors_pic_purpple","colors_purpple"),
        ForItem("أحـمــــر","","colors_pic_red","colors_red"),
        ForItem("أبـــيــض","","colors_pic_white","colors_white"),
        ForItem("أصـــفــر","","colors_pic_yellow","colors_yellow")
    )


    private var Fruits_List = listOf(
        ForItem("عـــنــب","","fruits_pic_3nab","fruits_3nab"),
        ForItem("أنــانس","","fruits_pic_ananas","fruits_ananas"),
        ForItem("تفــــاح","","fruits_pic_appel","fruits_apple"),
        ForItem("مــــوز","","fruits_pic_banana","fruits_bannana"),
        ForItem("كــــرز","","fruits_pic_karez","fruits_karez"),
        ForItem("خــــوخ","","fruits_pic_khokh","fruits_khokh"),
        ForItem("لــيــمــون","","fruits_pic_leamon","fruits_leamon"),
        ForItem("مـــشــمــش","","fruits_pic_michmech","fruits_michmech"),
        ForItem("بـــرتــقــال","","fruits_pic_orange","fruits_orange"),
        ForItem("رمــــان","","fruits_pic_roman","fruits_roman"),
        ForItem("فـراولـــة","","fruits_pic_strawberry","fruits_strawberry"),
        ForItem("بـــطــيـخ","","fruits_pic_watermelon","fruits_watermellon"),
        ForItem("كــيــوي","","animals_pic_kiwi","fruits_kiwi"),
        ForItem("مـنـدريـــن","","animals_pic_mandarine","fruits_mandarin"),
        ForItem("مــــانجـو","","animals_pic_mango","fruits_mango"),
        ForItem("إيـجـــاص","","animals_pic_pear","fruits_ijaas"),
        ForItem("تــيــن","","animals_pic_tiin","fruits_tiin")
    )

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
    fun getBestSccore():Int{
       return mShared.getInt("BEST_SCORE",0)
    }

    fun saveBestSccore(new_record:Int){
        mShared.edit().putInt("BEST_SCORE",new_record).apply()
    }

    fun getCardsGameCategoryPos():Int{
        return mShared.getInt("CATEGORY",0)
    }

    fun saveCardsGameCategoryPos(my_level:Int){
        mShared.edit().putInt("CATEGORY",my_level).apply()
    }

    fun getImageView(id:Int): ImageView {
        return (mCtx as Activity).findViewById(id)
    }

    fun getCardView(id:Int): CardView {
        return (mCtx as Activity).findViewById(id)
    }

    fun getCommonDialog(resID: Int): Dialog {
        val dialog = Dialog(mCtx!!)
        dialog.requestWindowFeature(1)
        dialog.setCancelable(false)
        dialog.setContentView(resID)
        dialog.window!!.setBackgroundDrawableResource(R.drawable.dialog)
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
        return dialog
    }

    fun showDialog(dialog: Dialog?,mMp3:Int) {
        if (dialog != null && !(mCtx as Activity).isFinishing) {
            dialog.show()
            stopPlaying()
            playSelectedMp3(mMp3)
            // showToast(this,msg)
        }
    }

    fun setHeader(title:String){
        val act = (mCtx as Activity)
        act.header_title.text = title
        act.header_btn_back.setOnClickListener { act.finish() }
    }

    fun setHeader2(chances:Int){
        val act = (mCtx as Activity)
        act.header_btn_back2.setOnClickListener { act.finish() }
        act.tv_chances.text = chances.toString()
    }


}