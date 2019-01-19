package com.coolightman.seaBattle.model;

import java.util.ArrayList;

public class Board {

    private ArrayList<Cell> cellArrayList = new ArrayList<>();
    private static String[] columnCords = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

    public Board() {
        createBoard();
    }

    private void createBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = new Cell(i, j);
                cellArrayList.add(cell);
            }
        }
    }

    public void printBoard() {
        int k = 0;
        System.out.print("   ");
        for (String columnCord : columnCords) {
            System.out.print("  " + columnCord);
        }
        System.out.println();

        for (int i = 0; i < 10; i++) {
            System.out.print("(" + (i) + ")\t");
            for (int j = 0; j < 10; j++) {
                Character character = cellArrayList.get(k).getCellChar();
                System.out.print("|" + character + "|");
                k++;
            }
            System.out.println();
        }
    }

    public void printGameBoard() {
        int k = 0;
        System.out.print("   ");
        for (String columnCord : columnCords) {
            System.out.print("  " + columnCord);
        }
        System.out.println();

        for (int i = 0; i < 10; i++) {
            System.out.print("(" + (i) + ")\t");
            for (int j = 0; j < 10; j++) {
                Character character = cellArrayList.get(k).getCellChar();
                if (character.equals('#')) {
                    character = '_';
                }
                System.out.print("|" + character + "|");
                k++;
            }
            System.out.println();
        }
    }

    public ArrayList<Cell> getCellList() {
        return cellArrayList;
    }
}
