package com.conwaysgameoflife

import android.graphics.Color

/**
 * Created by aleksandar.kovachev.
 */
class Cell(private val position: Int) {

    private var backgroundColor = Color.BLACK
    private var isAlive = false

    fun isAlive(): Boolean {
        return isAlive
    }

    fun setAlive(alive: Boolean) {
        isAlive = alive
    }

    fun die() {
        isAlive = false
        backgroundColor = Color.BLACK
    }

    fun reborn() {
        isAlive = true
        backgroundColor = Color.WHITE
    }

    fun getBackground(): Int {
        return backgroundColor
    }

    fun getPosition(): Int {
        return position
    }

}
