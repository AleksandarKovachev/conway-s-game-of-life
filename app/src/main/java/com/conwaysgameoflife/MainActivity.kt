package com.conwaysgameoflife

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var columns: Int? = 0
    private var rows: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val set = findViewById<Button>(R.id.set)
        set.setOnClickListener {
            val xEditText = findViewById<EditText>(R.id.x_edit_text)
            val yEditText = findViewById<EditText>(R.id.y_edit_text)

            if (xEditText.text.isBlank() || yEditText.text.isBlank()) {
                return@setOnClickListener
            }

            columns = xEditText.text.toString().toInt()
            rows = yEditText.text.toString().toInt()

            recyclerView = findViewById(R.id.recycler_view)
            recyclerView?.layoutManager = GridLayoutManager(this, columns ?: 0)
            recyclerView?.adapter = ConwayGameAdapter(columns ?: 0, rows ?: 0)
        }

        val nextGen = findViewById<Button>(R.id.next_gen)
        nextGen.setOnClickListener {
            nextGeneration()
        }

        val clear = findViewById<Button>(R.id.clear)
        clear.setOnClickListener {
            recyclerView?.adapter = ConwayGameAdapter(columns ?: 0, rows ?: 0)
        }
    }

    private fun getViewHolder(position: Int): ConwayGameAdapter.ViewHolder {
        return recyclerView?.getChildAt(position)?.let { view ->
            recyclerView?.getChildViewHolder(view)
        } as ConwayGameAdapter.ViewHolder
    }

    private fun getViewHolderByPosition(x: Int, y: Int): ConwayGameAdapter.ViewHolder {
        return getViewHolder(y * (columns ?: 0) + x)
    }

    private fun nbNeighboursOf(i: Int, j: Int): Int {
        var nb = 0
        for (k in i - 1..i + 1) {
            for (l in j - 1..j + 1) {
                if ((k != i || l != j) && k >= 0 && k < columns ?: 0 && l >= 0 && l < rows ?: 0) {
                    val cell = getViewHolderByPosition(k, l)
                    if (cell.alive) {
                        nb++
                    }
                }
            }
        }
        return nb
    }

    private fun nextGeneration() {
        val liveCells: MutableList<ConwayGameAdapter.ViewHolder> = mutableListOf()
        val deadCells: MutableList<ConwayGameAdapter.ViewHolder> = mutableListOf()

        for (i in 0 until (columns ?: 0)) {
            for (j in 0 until (rows ?: 0)) {
                val cell = getViewHolderByPosition(i, j)
                val nbNeighbours = nbNeighboursOf(i, j)

                // rule 1 & rule 3
                if (cell.alive && (nbNeighbours < 2 || nbNeighbours > 3)) {
                    deadCells.add(cell)
                }

                // rule 2 & rule 4
                if (cell.alive && (nbNeighbours == 3 || nbNeighbours == 2)
                    || !cell.alive && nbNeighbours == 3
                ) {
                    liveCells.add(cell)
                }
            }
        }

        for (cell in liveCells) {
            cell.reborn()
        }
        for (cell in deadCells) {
            cell.die()
        }
    }
}