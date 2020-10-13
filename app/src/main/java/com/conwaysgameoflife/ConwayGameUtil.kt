package com.conwaysgameoflife

/**
 * Created by aleksandar.kovachev.
 */
fun getCellByPosition(cells: List<Cell>, columns: Int, x: Int, y: Int): Cell {
    return cells[y * columns + x]
}

fun nbNeighboursOf(cells: List<Cell>, columns: Int, rows: Int, i: Int, j: Int): Int {
    var nb = 0
    for (k in i - 1..i + 1) {
        for (l in j - 1..j + 1) {
            if ((k != i || l != j) && k >= 0 && k < columns && l >= 0 && l < rows) {
                val cell = getCellByPosition(cells, columns, k, l)
                if (cell.isAlive()) {
                    nb++
                }
            }
        }
    }
    return nb
}

fun nextGeneration(cells: List<Cell>, columns: Int, rows: Int) {
    val liveCells: MutableList<Cell> = mutableListOf()
    val deadCells: MutableList<Cell> = mutableListOf()

    for (i in 0 until columns) {
        for (j in 0 until rows) {
            val cell = getCellByPosition(cells, columns, i, j)
            val nbNeighbours = nbNeighboursOf(cells, columns, rows, i, j)

            // rule 1 & rule 3
            if (cell.isAlive() && (nbNeighbours < 2 || nbNeighbours > 3)) {
                deadCells.add(cell)
            }

            // rule 2 & rule 4
            if (cell.isAlive() && (nbNeighbours == 3 || nbNeighbours == 2)
                || !cell.isAlive() && nbNeighbours == 3
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