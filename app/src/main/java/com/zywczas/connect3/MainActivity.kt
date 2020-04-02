package com.zywczas.connect3

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException
import androidx.core.view.isVisible as isVisible

class MainActivity : AppCompatActivity() {

    //0-empty, 1-yellow player, 2-red player

    var activePlayer = 1
    var activePlayerString = "Yellow"
    var gameState = arrayListOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
    val winningPositions = arrayOf(arrayOf(0,1,2), arrayOf(3,4,5), arrayOf(6,7,8),
                    arrayOf(0,3,6), arrayOf(1,4,7), arrayOf(2,5,8), arrayOf(0,4,8), arrayOf(2,4,6))
    var gameActive = true
    var yellowScore = 0
    var redScore = 0
    var tieCounter = 9


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        yellowScoreTextView.setBackgroundColor(Color.YELLOW)
        redScoreTextView.setBackgroundColor(Color.RED)
    }

    fun showCoin (view: View){
        val clickedField = view as ImageView
        try {
            val clickedTag = view.getTag().toString().toInt()
            tieCounter -=1

            if (gameState[clickedTag] == 0 && gameActive) {
                clickedField.translationY = -1500f
                if (activePlayer == 1){
                    clickedField.setImageResource(R.drawable.yellow)
                    gameState[clickedTag] = 1
                    activePlayer = 2
                } else {
                    clickedField.setImageResource(R.drawable.red)
                    gameState[clickedTag] = 2
                    activePlayer = 1
                }
                clickedField.animate().translationYBy(1500f).rotation(1800f).duration = 500
                checkIfWon()

                //if tie
                if (tieCounter == 0 && gameActive) {
                    gameActive = false
                    winnerTextView.text = "Tie!"
                    winnerTextView.isVisible = true
                    playAgainBtn.isVisible = true
                }
            }
        } catch (e: NumberFormatException) {
            Toast.makeText(this,"Incorrect tag, please close the app", Toast.LENGTH_LONG).show()
        }
    }

    fun checkIfWon(){
        for (winningPosition in winningPositions) {
            if (gameState[winningPosition[0]] != 0 && gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                gameState[winningPosition[1]] == gameState[winningPosition[2]]) {

                gameActive = false
                winnerTextView.isVisible = true
                playAgainBtn.isVisible = true

                val winner = gameState[winningPosition[0]]
                if (winner == 1) {
                    winnerTextView.text = "Yellow has won!"
                    yellowScore += 1
                    yellowScoreTextView.text = "Yellow: $yellowScore"
                } else {
                    winnerTextView.text = "Red has won!"
                    redScore += 1
                    redScoreTextView.text = "Red: $redScore"
                }
            }
        }
    }

    fun playAgain(view: View){

        coin0ImageView.setImageDrawable(null)
        coin1ImageView.setImageDrawable(null)
        coin2ImageView.setImageDrawable(null)
        coin3ImageView.setImageDrawable(null)
        coin4ImageView.setImageDrawable(null)
        coin5ImageView.setImageDrawable(null)
        coin6ImageView.setImageDrawable(null)
        coin7ImageView.setImageDrawable(null)
        coin8ImageView.setImageDrawable(null)

        winnerTextView.isVisible = false
        playAgainBtn.isVisible = false
        gameActive = true
        tieCounter = 9

        for (i in 0..8){
            gameState[i] = 0
        }
    }

    fun resetScore (view: View) {
        yellowScore = 0
        yellowScoreTextView.text = "Yellow: $yellowScore"
        redScore = 0
        redScoreTextView.text = "Red: $redScore"
    }

}
