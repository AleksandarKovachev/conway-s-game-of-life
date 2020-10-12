package com.conwaysgameoflife

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by aleksandar.kovachev.
 */
class ConwayGameAdapter(private val x: Int, private val y: Int) :
    RecyclerView.Adapter<ConwayGameAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var alive = false

        fun die() {
            alive = false
            itemView.setBackgroundColor(Color.BLACK)
        }

        fun reborn() {
            alive = true
            itemView.setBackgroundColor(Color.WHITE)
        }

        fun bind() {
            itemView.setOnClickListener {
                if (alive) {
                    die()
                } else {
                    reborn()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return x * y
    }

}