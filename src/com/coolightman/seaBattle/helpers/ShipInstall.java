package com.coolightman.seaBattle.helpers;

import com.coolightman.seaBattle.exceptions.SBGameBusyZoneException;

import java.util.ArrayList;

import static com.coolightman.seaBattle.helpers.Randomiser.chooseRndValidCell;
import static com.coolightman.seaBattle.helpers.Randomiser.directionRndChooser;
import static com.coolightman.seaBattle.helpers.ShipInstallHelper.*;


public class ShipInstall {
    private static int[] firstCellRndCord;
    private static EDirection currentDirection = null;
    private static int SHIP_SIZE;
    private static int currentDirectionTry = 0;

    public static ArrayList<Integer> setBigShipCords(int shipSize) {
        SHIP_SIZE = shipSize;
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
//          number of trying to set on random direction
            int NUMB_DIR_TRY = 6;
            currentDirectionTry++;
            if (currentDirectionTry <= NUMB_DIR_TRY) {
                tryToSetBigShipOnBoardForRndDir();
            } else throw new SBGameBusyZoneException();
        }
    }

    public static ArrayList<Integer> setShkonkaCords() {
        tryToSetShkonkaOnBoard();
        return setShkonkaCharOnBoard(firstCellRndCord);
    }

    private static void tryToSetShkonkaOnBoard() {
        firstCellRndCord = chooseRndValidCell();
        ArrayList<int[]> shipsZoneCellList = ShipInstallHelper.createArrayOfNeighborCellsZone(firstCellRndCord);

        try {
            checkZoneForCellsEmpty(shipsZoneCellList);
        } catch (SBGameBusyZoneException e) {
            tryToSetShkonkaOnBoard();
        }
    }
}
