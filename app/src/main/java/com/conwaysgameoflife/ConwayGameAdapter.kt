package com.conwaysgameoflife

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by aleksandar.kovachev.
 */
class ConwayGameAdapter(private val cells: List<Cell>) :
    RecyclerView.Adapter<ConwayGameAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cell: Cell) {
            itemView.setOnClickListener {
                if (cell.isAlive()) {
                    cell.die()
                } else {
                    cell.reborn()
                }
                itemView.setBackgroundColor(cell.getBackground())
            }
            itemView.setBackgroundColor(cell.getBackground())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cells[position])
    }

    override fun getItemCount(): Int {
        return cells.size
    }

}