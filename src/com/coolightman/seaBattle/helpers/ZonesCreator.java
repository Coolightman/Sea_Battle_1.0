package com.coolightman.seaBattle.helpers;
//created by Coolightman
//01.02.2019 14:00

import java.util.ArrayList;

class ZonesCreator {

    static ArrayList<Integer> createValidZoneCellList(ArrayList<int[]> shipsZoneCellList) {
        ArrayList<int[]> validShipsZoneCellList = new ArrayList<>();
        for (int[] shipsZoneCell : shipsZoneCellList) {
            if (shipsZoneCell[0] >= 0 && shipsZoneCell[0] <= 9 &&
                    shipsZoneCell[1] >= 0 && shipsZoneCell[1] <= 9) {
                validShipsZoneCellList.add(shipsZoneCell);
            }
        }
        ArrayList<Integer> cellNumbList = new ArrayList<>();
        for (int[] shipsZoneCell : validShipsZoneCellList) {
            cellNumbList.add(ShipCreatorHelper.numberOfCellFinder(shipsZoneCell));
        }
        return cellNumbList;
    }

    //    находим зону проверки незанятости для большого корабля
    static ArrayList<int[]> createZoneCellListForBigShips(EDirection currentDirection, int[] firstCellRndCord, int SIZE) {
        int x = firstCellRndCord[0];
        int y = firstCellRndCord[1];
        ArrayList<int[]> shipsZoneCellList = new ArrayList<>();
        switch (currentDirection) {
            case NORTH:
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < SIZE + 2; j++) {
                        int[] cell = {x - 1 + i, y + 1 - j};
                        shipsZoneCellList.add(cell);
                    }
                }
                break;

            case SOUTH:
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < SIZE + 2; j++) {
                        int[] cell = {x - 1 + i, y - 1 + j};
                        shipsZoneCellList.add(cell);
                    }
                }
                break;

            case WEST:
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < SIZE + 2; j++) {
                        int[] cell = {x + 1 - j, y - 1 + i};
                        shipsZoneCellList.add(cell);
                    }
                }
                break;

            case EAST:
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < SIZE + 2; j++) {
                        int[] cell = {x - 1 + j, y - 1 + i};
                        shipsZoneCellList.add(cell);
                    }
                }
                break;
        }
        return shipsZoneCellList;
    }

    static ArrayList<int[]> createZoneCellListForLittleShip(int[] firstCellRndCord) {
        int x = firstCellRndCord[0];
        int y = firstCellRndCord[1];
        ArrayList<int[]> shipsZoneCellList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int[] cell = {x - 1 + i, y - 1 + j};
                shipsZoneCellList.add(cell);
            }
        }
        return shipsZoneCellList;
    }
}
