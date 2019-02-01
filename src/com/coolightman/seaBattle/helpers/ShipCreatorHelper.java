package com.coolightman.seaBattle.helpers;

import com.coolightman.seaBattle.exceptions.SBGameBusyCell;
import com.coolightman.seaBattle.exceptions.SBGameBusyZoneException;
import com.coolightman.seaBattle.model.Board;
import com.coolightman.seaBattle.model.Figure;

import java.util.ArrayList;

import static com.coolightman.seaBattle.helpers.ZonesCreator.createValidZoneCellList;

public class ShipCreatorHelper {

    public static int numberOfCellFinder(int[] firstCellCord) {
        return firstCellCord[1] * 10 + firstCellCord[0];
    }

    static int[] shipCordCellsFinder(int firstCellNumber, int size, EDirection direction) {
        int[] anotherCellsNumbers = new int[size];
        switch (direction) {
            case NORTH:
                for (int i = 0; i < size; i++) {
                    anotherCellsNumbers[i] = (firstCellNumber - (10 * i));
                }
                break;

            case SOUTH:
                for (int i = 0; i < size; i++) {
                    anotherCellsNumbers[i] = (firstCellNumber + (10 * i));
                }
                break;

            case WEST:
                for (int i = 0; i < size; i++) {
                    anotherCellsNumbers[i] = (firstCellNumber - i);
                }
                break;

            case EAST:
                for (int i = 0; i < size; i++) {
                    anotherCellsNumbers[i] = (firstCellNumber + i);
                }
                break;
        }
        return anotherCellsNumbers;
    }

    static void checkCellsEmpty(int cellNumber) throws SBGameBusyCell {
        if (Board.getCellList().get(cellNumber).getCellChar().equals(Figure.SHIPED)) throw new SBGameBusyCell();
    }

    static void checkZoneEmpty(ArrayList<int[]> shipsZoneCellList) throws SBGameBusyZoneException {
        ArrayList<Integer> cellNumbList = createValidZoneCellList(shipsZoneCellList);

        for (int cellNumb : cellNumbList) {
            try {
                checkCellsEmpty(cellNumb);
            } catch (SBGameBusyCell sbGameBusyCell) {
                throw new SBGameBusyZoneException();
            }
        }
    }

    static void validityShipCells(int[] firstCellRndCord, EDirection currentDirection, int SIZE) throws SBGameBusyZoneException {
        int x = firstCellRndCord[0];
        int y = firstCellRndCord[1];
        switch (currentDirection) {
            case NORTH:
                for (int i = 0; i < SIZE; i++) {
                    int[] cell = {x, y - i};
                    int columnCord = cell[0];
                    int lineCord = cell[1];
                    if (columnCord < 0 || columnCord > 9 || lineCord < 0 || lineCord > 9) {
                        throw new SBGameBusyZoneException();
                    }
                }
                break;

            case SOUTH:
                for (int i = 0; i < SIZE; i++) {
                    int[] cell = {x, y + i};
                    int columnCord = cell[0];
                    int lineCord = cell[1];
                    if (columnCord < 0 || columnCord > 9 || lineCord < 0 || lineCord > 9) {
                        throw new SBGameBusyZoneException();
                    }
                }
                break;

            case WEST:
                for (int i = 0; i < SIZE; i++) {
                    int[] cell = {x - i, y};
                    int columnCord = cell[0];
                    int lineCord = cell[1];
                    if (columnCord < 0 || columnCord > 9 || lineCord < 0 || lineCord > 9) {
                        throw new SBGameBusyZoneException();
                    }
                }
                break;

            case EAST:
                for (int i = 0; i < SIZE; i++) {
                    int[] cell = {x + i, y};
                    int columnCord = cell[0];
                    int lineCord = cell[1];
                    if (columnCord < 0 || columnCord > 9 || lineCord < 0 || lineCord > 9) {
                        throw new SBGameBusyZoneException();
                    }
                }
                break;
        }
    }
}
