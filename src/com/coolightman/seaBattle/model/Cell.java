package com.coolightman.seaBattle.model;

public class Cell {
    private int columnCord;
    private int lineCord;
    private int[] cord = new int[2];
    private Character cellChar;

    Cell(int columnCord, int lineCord) {
        this.columnCord = columnCord;
        this.lineCord = lineCord;
        cord[0] = columnCord;
        cord[1] = lineCord;
        this.cellChar = '_';
    }

    public int getColumnCord() {
        return columnCord;
    }

    public int getLineCord() {
        return lineCord;
    }

    public int[] getCord() {
        return cord;
    }

    public Character getCellChar() {
        return cellChar;
    }

    public void setCellChar(Character cellChar) {
        this.cellChar = cellChar;
    }
}
