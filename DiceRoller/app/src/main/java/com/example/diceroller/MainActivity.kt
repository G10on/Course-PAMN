package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var diceImage1 : ImageView
    private lateinit var diceImage2 : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diceImage1 = findViewById(R.id.dice_image1)
        diceImage2 = findViewById(R.id.dice_image2)

        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener { rollDice() }

        val clearButton: Button = findViewById(R.id.clear_button)
        clearButton.setOnClickListener { clear() }

        /*val resetButton: Button = findViewById(R.id.reset_button)
        resetButton.setOnClickListener { reset() }

        val countButton: Button = findViewById(R.id.count_button)
        countButton.setOnClickListener { countUp() }*/
    }

    private fun rollDice() {
        diceImage1.setImageResource(getRandDice())
        diceImage2.setImageResource(getRandDice())
    }

    private fun getRandDice() : Int {
        val resultImg = when ((1..6).random()) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        return resultImg
    }

    private fun clear() {
        diceImage1.setImageResource(R.drawable.empty_dice)
        diceImage2.setImageResource(R.drawable.empty_dice)
    }

    /*
    private fun countUp() {
        val resultText: TextView = findViewById(R.id.dice_image1)

        if (resultText.text == "Hello World") {
            resultText.text = "1"
        } else {
            var count = resultText.text.toString().toInt()
            if (count < 6) {
                count++
                resultText.text = count.toString()
            }
        }
    }

    private fun reset() {
        val resultText: TextView = findViewById(R.id.result_text)
        resultText.text = "0"
    }

     */
}