package com.zywczas.connect3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    //0-empty, 1-yellow, 2-red

    var activePlayer = 1
    var gameState = arrayListOf(0, 0, 0, 0, 0, 0, 0, 0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showCoin (view: View){
        val dropper = view as ImageView
        dropper.translationY = -1500f
        if (activePlayer == 1){
            dropper.setImageResource(R.drawable.yellow)
            activePlayer = 2
        } else {
            dropper.setImageResource(R.drawable.red)
            activePlayer = 1
        }
        dropper.animate().translationYBy(1500f).rotation(1800f).duration = 500
    }
}
