package com.example.myapplication

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

@Suppress("DEPRECATION")
class CupActivity : AppCompatActivity() {
    private lateinit var cup1Button: ImageButton
    private lateinit var cup2Button: ImageButton
    private lateinit var cup3Button: ImageButton

    private lateinit var shuffleAnimation: Animation
    private var temp1: Int = 5
    private var correctCup: Int = 1
    private val positions = mutableListOf<Float>()
    private var duration = 1000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        cup1Button = findViewById(R.id.cup1ImageButton)
        cup2Button = findViewById(R.id.cup2ImageButton)
        cup3Button = findViewById(R.id.cup3ImageButton)


        shuffleAnimation = AnimationUtils.loadAnimation(this, R.anim.shuffle)
    }

    fun onCupClicked(view: View) {
        val selectedCup = view as ImageButton

        // Проверить, выбрана ли правильная чашка
        if (selectedCup == getCupButton(correctCup)) {
            showToast("Вы победили!")
            shuffleCups()
            temp1++
            duration-=100
        } else {
            showToast("Вы проиграли. Попробуйте еще раз.")
            shuffleCups()
            temp1 = 5
            duration = 1000L
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun getCupButton(cupNumber: Int): ImageButton {
        return when (cupNumber) {
            1 -> cup1Button
            2 -> cup2Button
            else -> cup3Button
        }
    }

    private fun resetCups() {
        cup1Button.setImageResource(R.drawable.closed_cup)
        cup2Button.setImageResource(R.drawable.closed_cup)
        cup3Button.setImageResource(R.drawable.closed_cup)
        val textView:TextView = findViewById(R.id.textView4)
        textView.setText("")
    }

    fun shuffleCups() {
        resetCups()
        correctCup = (1..3).random()
        val correctButton = getCupButton(correctCup)
        // Меняем картинку на open_cup
        correctButton.setImageResource(R.drawable.open_cup)

        // Запускаем анимацию shuffle
        val duration = 1000L
        val tem = 100.0F
//val animation = AnimationUtils.loadAnimation(this, R.anim.shuffle)
//correctButton.startAnimation(animation)
        val anim1 = ObjectAnimator.ofFloat(correctButton, "y", correctButton.y+tem)
            .setDuration(duration)
        val anim2 = ObjectAnimator.ofFloat(correctButton, "y", correctButton.y)
            .setDuration(duration)
        val animSet = AnimatorSet()
        animSet.playSequentially(anim1, anim2)
        animSet.start()
        // Ждем завершения анимации
        animSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
            }

            @SuppressLint("SuspiciousIndentation")
            override fun onAnimationEnd(animation: Animator) {
                // Меняем картинку на closed_cup
                correctButton.setImageResource(R.drawable.closed_cup)
            positions.add(cup1Button.x)
            positions.add(cup2Button.x)
            positions.add(cup3Button.x)
            cup1Button.isEnabled = false
            cup2Button.isEnabled = false
            cup3Button.isEnabled = false

                animateImageButtons(temp1,duration)

            }

            override fun onAnimationRepeat(animation: Animator) {
            }
        })

    }
    fun generateRandomArray(): IntArray {
        val array = IntArray(3)
        val set = mutableSetOf<Int>()

        while (set.size < 3) {
            val randomNum = Random.nextInt(0, 3)
            set.add(randomNum)
        }
        val iterator = set.iterator()
        for (i in array.indices) {
            if (iterator.hasNext()) {
                array[i] = iterator.next()
            }
        }
        return array
    }

    public fun animateImageButtons(int: Int, duration:Long) {


        var randomPositions = mutableListOf<Float>()

        var array = generateRandomArray()
        while (array[0] == 0 && array[1] == 1 && array[2] == 2)
        {
            array = generateRandomArray()
        }

        randomPositions.add(positions[array[0]])
        randomPositions.add(positions[array[1]])
        randomPositions.add(positions[array[2]])
        var iy = 0L
        for (i in 0 until int) {
            val animSet = AnimatorSet()
            var duration = duration// Длительность анимации в миллисекундах
            var arrayy = generateRandomArray()
            while (array[0] == arrayy[0] && array[1] == arrayy[1] && array[2] == arrayy[2])
            {
                arrayy = generateRandomArray()
            }
            // Вычисляем смещение для анимаций (относительно их текущих позиций)
            val target1 = randomPositions[arrayy[0]]

            val target2 = randomPositions[arrayy[1]]

            val target3 = randomPositions[arrayy[2]]

            // Создание анимаций перемещения для каждого ImageButton
            val anim1 = ObjectAnimator.ofFloat(cup1Button, "x", target1)
                .setDuration(duration)

            val anim2 = ObjectAnimator.ofFloat(cup2Button, "x", target2)
                .setDuration(duration)

            val anim3 = ObjectAnimator.ofFloat(cup3Button, "x", target3)
                .setDuration(duration)
            iy = i * duration
            animSet.playTogether(anim1, anim2, anim3)
            animSet.startDelay = iy
            animSet.start()
            array = arrayy
        }
        val timer = object : CountDownTimer(iy, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Делаем что-то каждые 1000 миллисекунд (1 секунда)
            }

            override fun onFinish() {
                cup1Button.isEnabled = true
                cup2Button.isEnabled = true
                cup3Button.isEnabled = true
                val textView:TextView = findViewById(R.id.textView4)
                textView.setText("Выберайте наперсток")
            }
        }

        timer.start()
    }
}