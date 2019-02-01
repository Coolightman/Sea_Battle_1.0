package com.coolightman.seaBattle.helpers;

import com.coolightman.seaBattle.exceptions.SBGameBusyZoneException;

import java.util.ArrayList;

import static com.coolightman.seaBattle.helpers.Randomize.chooseRndValidCell;
import static com.coolightman.seaBattle.helpers.Randomize.directionRndChooser;
import static com.coolightman.seaBattle.helpers.SetterShipsCharsOnBoard.setBigShipCharsOnBoard;
import static com.coolightman.seaBattle.helpers.SetterShipsCharsOnBoard.setLittleShipCharOnBoard;
import static com.coolightman.seaBattle.helpers.ShipCreatorHelper.*;
import static com.coolightman.seaBattle.helpers.ZonesCreator.createZoneCellListForBigShips;
import static com.coolightman.seaBattle.helpers.ZonesCreator.createZoneCellListForLittleShip;


public class ShipCreator {
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
        ArrayList<int[]> shipsZoneCellList = createZoneCellListForBigShips(currentDirection, firstCellRndCord, SHIP_SIZE);

        try {
            validityShipCells(firstCellRndCord, currentDirection, SHIP_SIZE);
            checkZoneEmpty(shipsZoneCellList);
        } catch (SBGameBusyZoneException e) {
//          number of trying to set ship for random direction
            int NUMB_DIR_TRY = 6;
            currentDirectionTry++;
            if (currentDirectionTry <= NUMB_DIR_TRY) {
                tryToSetBigShipOnBoardForRndDir();
            } else throw new SBGameBusyZoneException();
        }
    }

    public static ArrayList<Integer> setLittleShipCords() {
        tryToSetLittleShipOnBoard();
        return setLittleShipCharOnBoard(firstCellRndCord);
    }

    private static void tryToSetLittleShipOnBoard() {
        firstCellRndCord = chooseRndValidCell();
        ArrayList<int[]> shipsZoneCellList = createZoneCellListForLittleShip(firstCellRndCord);

        try {
            checkZoneEmpty(shipsZoneCellList);
        } catch (SBGameBusyZoneException e) {
            tryToSetLittleShipOnBoard();
        }
    }
}
