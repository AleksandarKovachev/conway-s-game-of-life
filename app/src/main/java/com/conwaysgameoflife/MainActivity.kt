package com.conwaysgameoflife

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var adapter: ConwayGameAdapter? = null
    private var cells: List<Cell>? = null
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

            initCells()

            recyclerView = findViewById(R.id.recycler_view)
            recyclerView?.layoutManager = GridLayoutManager(this, columns ?: 0)
            recyclerView?.adapter = adapter
        }

        val nextGen = findViewById<Button>(R.id.next_gen)
        nextGen.setOnClickListener {
            nextGeneration(cells!!, columns!!, rows!!)
            adapter?.notifyDataSetChanged()
        }

        val clear = findViewById<Button>(R.id.clear)
        clear.setOnClickListener {
            initCells()
            recyclerView?.adapter = adapter
        }
    }

    private fun initCells() {
        cells = List(size = (columns!! * rows!!), init = { i -> Cell(i) })
        adapter = ConwayGameAdapter(cells!!)
    }

}