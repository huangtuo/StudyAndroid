package com.study.ht.studyandroid.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.study.ht.studyandroid.R
import com.study.ht.studyandroid.base.BaseActivity

class FullScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_full_screen)
        super.onCreate(savedInstanceState)
        if(Build.VERSION.SDK_INT >= 21){
            var decorView: View = window.decorView
            /**
             SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE注意两个Flag必须要结合在一起使用，
            表示会让应用的主体内容占用系统状态栏的空间，
            最后再调用Window的setStatusBarColor()方法将状态栏设置成透明色就可以了。
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
             window.statusBarColor = Color.TRANSPARENT
             */
            /**
             * 隐藏状态栏
             *View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
             * window.navigationBarColor = Color.TRANSPARENT  设置透明 否则直接隐藏
             */
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN

        }

        var actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
    }
}
