package com.coolightman.seaBattle.helpers;
//created by Coolightman
//01.02.2019 14:04

import com.coolightman.seaBattle.model.Board;
import com.coolightman.seaBattle.model.Figure;

import java.util.ArrayList;

import static com.coolightman.seaBattle.helpers.ShipCreatorHelper.numberOfCellFinder;
import static com.coolightman.seaBattle.helpers.ShipCreatorHelper.shipCordCellsFinder;

class SetterShipsCharsOnBoard {

    static ArrayList<Integer> setBigShipCharsOnBoard(EDirection currentDirection, int[] firstCellRndCord, int SIZE) {
        int numberOfFirstCell = numberOfCellFinder(firstCellRndCord);
        int[] anotherCellsNumbers = shipCordCellsFinder(numberOfFirstCell, SIZE, currentDirection);
        ArrayList<Integer> shipCords = new ArrayList<>();

        for (int anotherCellNumber : anotherCellsNumbers) {
            Board.getCellList().get(anotherCellNumber).setCellChar(Figure.SHIPED);
            shipCords.add(anotherCellNumber);
        }
        return shipCords;
    }

    static ArrayList<Integer> setLittleShipCharOnBoard(int[] firstCellRndCord) {
        int numberOfCell = numberOfCellFinder(firstCellRndCord);
        ArrayList<Integer> shipCord = new ArrayList<>();

        Board.getCellList().get(numberOfCell).setCellChar(Figure.SHIPED);
        shipCord.add(numberOfCell);

        return shipCord;
    }
}
