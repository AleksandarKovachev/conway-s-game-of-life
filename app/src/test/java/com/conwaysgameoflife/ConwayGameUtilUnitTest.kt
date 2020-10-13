package com.conwaysgameoflife

import org.junit.Assert
import org.junit.Test

class ConwayGameUtilUnitTest {

    @Test
    fun nbNeighboursOf_fiveRowsAndFiveColumns_expectedTwoAliveCells() {
        val columns = 5
        val rows = 5
        val x = 2
        val y = 3
        val cells: List<Cell> = List(size = (columns * rows), init = { i -> Cell(i) })
        cells[16].reborn()
        cells[21].reborn()

        val neighbours = nbNeighboursOf(cells, columns, rows, x, y)

        Assert.assertEquals(2, neighbours)
    }

    @Test
    fun nbNeighboursOf_fiveRowsAndFiveColumns_expectedSixAliveCells() {
        val columns = 5
        val rows = 5
        val x = 3
        val y = 3
        val cells: List<Cell> = List(size = (columns * rows), init = { i -> Cell(i) })
        cells[12].reborn()
        cells[13].reborn()
        cells[14].reborn()
        cells[17].reborn()
        cells[19].reborn()
        cells[24].reborn()

        val neighbours = nbNeighboursOf(cells, columns, rows, x, y)

        Assert.assertEquals(6, neighbours)
    }

    @Test
    fun getCellByPosition_fiveRowsAndFiveColumns_expectedAlive() {
        val columns = 5
        val rows = 5
        val x = 3
        val cells: List<Cell> = List(size = (columns * rows), init = { i -> Cell(i) })
        cells[20].reborn()

        val cell = getCellByPosition(cells, columns, rows, x)

        Assert.assertTrue(cell.isAlive())
    }

    @Test
    fun getCellByPosition_fiveRowsAndFiveColumns_expectedDead() {
        val columns = 5
        val rows = 5
        val x = 3
        val cells: List<Cell> = List(size = (columns * rows), init = { i -> Cell(i) })
        cells[21].reborn()

        val cell = getCellByPosition(cells, columns, rows, x)

        Assert.assertFalse(cell.isAlive())
    }

    @Test
    fun nextGeneration_fiveRowsAndFiveColumns_expectedFourAliveCells() {
        val columns = 5
        val rows = 5
        val cells: List<Cell> = List(size = (columns * rows), init = { i -> Cell(i) })
        cells[11].reborn()
        cells[12].reborn()
        cells[17].reborn()

        nextGeneration(cells, columns, rows)

        Assert.assertEquals(4, cells.count { cell -> cell.isAlive() })
        Assert.assertTrue(cells[16].isAlive())
    }

    @Test
    fun nextGeneration_fiveRowsAndFiveColumns_expectedZeroAliveCells() {
        val columns = 5
        val rows = 5
        val cells: List<Cell> = List(size = (columns * rows), init = { i -> Cell(i) })
        cells[12].reborn()
        cells[17].reborn()

        nextGeneration(cells, columns, rows)

        Assert.assertEquals(0, cells.count { cell -> cell.isAlive() })
    }

}