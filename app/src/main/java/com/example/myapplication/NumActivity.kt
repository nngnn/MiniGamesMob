package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class NumActivity : AppCompatActivity() {
    private lateinit var buttonCheck: Button
    private lateinit var buttonRun: Button
    private lateinit var editText6: EditText
    private lateinit var editText5: EditText
    private lateinit var editText4: EditText
    private lateinit var editText3: EditText
    private lateinit var editText2: EditText
    private lateinit var editText1: EditText
    private  var long = 1
    private  var scope = 0
    private  var quantity = 3
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)
        editText1 = findViewById(R.id.editText1)
        editText2 = findViewById(R.id.editText2)
        editText3 = findViewById(R.id.editText3)
        editText4 = findViewById(R.id.editText4)
        editText5 = findViewById(R.id.editText5)
        editText6 = findViewById(R.id.editText6)
        buttonRun = findViewById(R.id.buttonRun)
        buttonCheck = findViewById(R.id.buttonCheck)
        buttonCheck.isEnabled = false
        buttonCheck.visibility = View.INVISIBLE
        editText1.isEnabled = false
        editText2.isEnabled = false
        editText3.isEnabled = false
        editText4.isEnabled = false
        editText5.isEnabled = false
        editText6.isEnabled = false
        var editText: EditText
        var array:Array<Int> = generateRandomNumbers(quantity*long)
        var editTexts: List<EditText> = mutableListOf()
        buttonRun.setOnClickListener{
            array = generateRandomNumbers(quantity*long)
            editTexts = chooseEditTexts(quantity, listOf(
                editText1,editText2,editText3,editText4,editText5,editText6
            ))
            for (i in 0 until quantity step long) {
                for (j in i until i+long)
                {
                    editTexts[i/long].setText(editTexts[i/long].text.toString()+array[j])
                }
                editTexts[i/long].isEnabled = true
            }
            buttonRun.isEnabled = false
            buttonRun.visibility = View.INVISIBLE
            val timer = object : CountDownTimer(5000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    // Делаем что-то каждые 1000 миллисекунд (1 секунда)
                }

                override fun onFinish() {
                    buttonCheck.isEnabled = true
                    buttonCheck.visibility = View.VISIBLE
                    editText1.setText("")
                    editText2.setText("")
                    editText3.setText("")
                    editText4.setText("")
                    editText5.setText("")
                    editText6.setText("")
                }
            }

            timer.start()
        }
        buttonCheck.setOnClickListener {
            var temp = 0
            var ter = 0
                for (i in 0 until quantity step long) {
                    val text = editTexts[i%long].text.toString()
                    var text1 = ""
                    for (j in i until i+long)
                    {
                        text1 += array[j]
                    }
                    if (text == text1)
                        ter++
                    editTexts[i/long].isEnabled = false
                }
            if (ter == quantity)
            {
                showToast("Вы перешли на следующий этап")
                if (quantity != 6)
                    quantity++
                else
                    long++
                scope++
            }
            else
            {
                showToast("Вы проиграли!" +
                        "Ваш счет $scope")
                    scope = 0
                    quantity = 3
                    long = 1
            }



            buttonRun.isEnabled = true
            buttonRun.visibility = View.VISIBLE
            buttonCheck.isEnabled = false
            buttonCheck.visibility = View.INVISIBLE
            editText1.setText("")
            editText2.setText("")
            editText3.setText("")
            editText4.setText("")
            editText5.setText("")
            editText6.setText("")
            editText1.isEnabled = false
            editText2.isEnabled = false
            editText3.isEnabled = false
            editText4.isEnabled = false
            editText5.isEnabled = false
            editText6.isEnabled = false
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    fun generateRandomNumbers(length: Int): Array<Int> {
        val numbers = mutableListOf<Int>()
        for (i in 0 until length) {
            numbers.add((0..9).random())
        }
        return numbers.toTypedArray()
    }
    fun chooseEditTexts(l: Int, editTexts: List<EditText>): List<EditText> {
        val chosenEditTexts = mutableListOf<EditText>()
        val chosenIndexes = mutableSetOf<Int>()

        for (i in 0 until l) {
            var chosenIndex = -1

            do {
                chosenIndex = (0 until editTexts.size).random()
            } while (chosenIndexes.contains(chosenIndex))

            chosenIndexes.add(chosenIndex)
            chosenEditTexts.add(editTexts[chosenIndex])
        }

        return chosenEditTexts
    }
}