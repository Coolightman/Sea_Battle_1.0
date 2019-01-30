package com.coolightman.seaBattle.helpers;

import com.coolightman.seaBattle.exceptions.SBGameBusyZoneException;

import java.util.ArrayList;

import static com.coolightman.seaBattle.helpers.ShipInstallHelper.*;


public class ShipInstall {
    private static int[] firstCellRndCord;
    private static EDirection currentDirection = null;
    private static int SHIP_SIZE;
    private static int currentDirectionTry = 0;

    public static ArrayList<Integer> setBigShipCords(int shipSize) {
        SHIP_SIZE = shipSize;

//        Algorithm:
//        1) Choose random first cell to ship
//        2) Choose random direction to ship install
//        2.1) Check for ship parts is not out of board
//        3) Create array of neighbor-cells zone
//        4) Check zone for cells empty

        tryToSetBigShipOnBoard();
        return setBigShipCharsOnBoard(currentDirection, firstCellRndCord, SHIP_SIZE);
    }

    private static void tryToSetBigShipOnBoard() {
        firstCellRndCord = chooseRndValidCell();

        try {
            tryToSetBigShipOnBoardForRndDir();
        } catch (SBGameBusyZoneException e) {
            tryToSetBigShipOnBoard();
        }
    }

    private static void tryToSetBigShipOnBoardForRndDir() throws SBGameBusyZoneException {
        currentDirection = directionRndChooser();
        ArrayList<int[]> shipsZoneCellList = createZoneCellList(currentDirection, firstCellRndCord, SHIP_SIZE);

        try {
            validityShipCells(firstCellRndCord, currentDirection, SHIP_SIZE);
            checkZoneForCellsEmpty(shipsZoneCellList);
        } catch (SBGameBusyZoneException e) {
//              число попыток выбора рандомного направления
            int NUMB_DIR_TRY = 6;
            currentDirectionTry++;
            if (currentDirectionTry <= NUMB_DIR_TRY) {
                tryToSetBigShipOnBoardForRndDir();
            } else throw new SBGameBusyZoneException();
        }
    }

    public static ArrayList<Integer> setShkonkaCords() {

//        Algorithm:
//        1) Choose random cell to ship
//        2) Create array of neighbor-cells zone
//        3) Check zone for cells empty

        tryToSetShkonkaOnBoard();
        return setShkonkaCharOnBoard(firstCellRndCord);
    }

    private static void tryToSetShkonkaOnBoard() {
        firstCellRndCord = chooseRndValidCell();
        ArrayList<int[]> shipsZoneCellList = createArrayOfNeighborCellsZone(firstCellRndCord);

        try {
            checkZoneForCellsEmpty(shipsZoneCellList);
        } catch (SBGameBusyZoneException e) {
            tryToSetShkonkaOnBoard();
        }
    }

    private static ArrayList<int[]> createArrayOfNeighborCellsZone(int[] firstCellRndCord) {
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
