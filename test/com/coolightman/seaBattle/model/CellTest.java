package com.coolightman.seaBattle.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {

    @Test
    public void getCellChar() {
        Figure expectFigure = Figure.EMPTY;

        Cell cell = new Cell();
        Figure actualFigure = cell.getCellChar();
        assertEquals(expectFigure, actualFigure);
    }

    @Test
    public void setCellChar() {
        Figure expectFigure = Figure.X;

        Cell cell = new Cell();
        cell.setCellChar(Figure.X);
        Figure actualFigure = cell.getCellChar();
        assertEquals(expectFigure, actualFigure);
    }
}