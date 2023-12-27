@file:Suppress("DEPRECATION")

package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.Toast

class MainActivity5 : AppCompatActivity() {

    private lateinit var cardsGrid: GridView
    private lateinit var adapter: CardAdapter
    private var flippedPosition: Int = -1
    private var isBusy = false
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        cardsGrid = findViewById(R.id.cardsGrid)
        adapter = CardAdapter(this)
        cardsGrid.adapter = adapter

        cardsGrid.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            if (!isBusy && !adapter.isCardFlipped(position)) {
                if (flippedPosition == -1) {
                    flippedPosition = position

                    adapter.flipCard(position, true)
                } else {
                    adapter.flipCard(position, true)
                    if (adapter.getItem(position) == adapter.getItem(flippedPosition)) {
                        // Пара найдена

                        score++
                        if (score == 12) {
                            // Все пары найдены, игра окончена

                            Toast.makeText(this, "Поздравляем, вы выиграли!", Toast.LENGTH_SHORT).show()
                            resetGame()
                        }
                    }
                    flippedPosition = -1

                }
            }
        }
    }

    private fun resetGame() {
        // Сбросить счет и перевернуть все карты

        score = 0

        adapter.resetCards()
    }
}

class CardAdapter(private val context: Context) : BaseAdapter() {
    private var flippedPosition: Int = -1
    private val cards = intArrayOf(
        R.drawable.card1, R.drawable.card2, R.drawable.card3, R.drawable.card4,
        R.drawable.card5, R.drawable.card6, R.drawable.card7, R.drawable.card8,
        R.drawable.card9,R.drawable.card10,R.drawable.card11,R.drawable.card12,
        R.drawable.card1, R.drawable.card2, R.drawable.card3, R.drawable.card4,
        R.drawable.card5, R.drawable.card6, R.drawable.card7, R.drawable.card8,
        R.drawable.card9,R.drawable.card10,R.drawable.card11,R.drawable.card12

    )

    private val flipped = ArrayList<Boolean>()

    init {
        for (i in 0 until cards.size) {
            flipped.add(false)
        }
        cards.shuffle()
    }

    override fun getCount(): Int {
        return cards.size

    }

    override fun getItem(position: Int): Int {
        return cards[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView: ImageView

        if (convertView == null) {
            imageView = ImageView(context)
            imageView.layoutParams = AbsListView.LayoutParams(200, 200)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP

        } else {
            imageView = convertView as ImageView

        }
        if (flipped[position]) {
            imageView.setImageResource(cards[position])
        } else {
            imageView.setImageResource(R.drawable.card_back)
        }
        return imageView

    }


    fun flipCard(position: Int, faceUp: Boolean) {
            if (!flipped[position]) {
                flipped[position] = faceUp

                notifyDataSetChanged()
                if (faceUp)
                    if (flippedPosition != -1 && flippedPosition != position) {
                        if (getItem(position) != getItem(flippedPosition)) {
                            val tempPosition = flippedPosition

                            Handler().postDelayed({
                                flipped[position] = false

                                flipped[tempPosition] = false

                                notifyDataSetChanged()
                            }, 1000)
                        }
                        flippedPosition = -1

                    } else {
                        flippedPosition = position

                    }
            }

    }

    fun resetCards() {
        for (i in 0 until flipped.size) {
            flipped[i] = false

        }
        cards.shuffle()
        notifyDataSetChanged()
    }

    fun isCardFlipped(position: Int): Boolean {
        return flipped[position]
    }
}
