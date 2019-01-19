package com.coolightman.seaBattle.model;

public class Cell {

    private Figure cellChar;

    Cell() {
        this.cellChar = Figure.EMPTY;
    }

    public Figure getCellChar() {
        return cellChar;
    }

    public void setCellChar(Figure cellChar) {
        this.cellChar = cellChar;
    }
}
