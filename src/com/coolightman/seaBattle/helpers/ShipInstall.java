package com.coolightman.seaBattle.helpers;

import java.util.ArrayList;

import static com.coolightman.seaBattle.helpers.ShipInstallHelper.*;


public class ShipInstall {
    public static ArrayList<Integer> setBigShipCords(int shipSize) {

//        число попыток выбора рандомного направления
        int NUMB_DIR_TRY = 8;

        boolean clearPlace = false;
        EDirection currentDirection = null;
        int[] firstCellRndCord;

        do {
            firstCellRndCord = ShipInstallHelper.chooseRndValidNumb();
            int x = firstCellRndCord[0];
            int y = firstCellRndCord[1];

            for (int i = 0; i < NUMB_DIR_TRY; i++) {
                currentDirection = ShipInstallHelper.directionRndChooser();
                ArrayList<int[]> shipsZoneCellList;
                shipsZoneCellList = chooseZoneCellListByDirection(currentDirection, x, y, shipSize);

                if (validityShipCells(x, y, currentDirection, shipSize)) {
                    clearPlace = checkZoneForCellsEmpty(shipsZoneCellList);
                }

                if (clearPlace) break;
            }

        } while (!clearPlace);

        return setBigShipCharsOnBoard(currentDirection, firstCellRndCord, shipSize);
    }

    public static ArrayList<Integer> setShkonkaCords() {
        boolean clearPlace;
        int[] firstCellRndCord;
        do {
            firstCellRndCord = ShipInstallHelper.chooseRndValidNumb();
            int x = firstCellRndCord[0];
            int y = firstCellRndCord[1];

//            Создаем коллекцию и добавляем в нее все комбинации пустых ячеек рядом с корнем
            ArrayList<int[]> shipsZoneCellList = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int[] cell = {x - 1 + i, y - 1 + j};
                    shipsZoneCellList.add(cell);
                }
            }
            clearPlace = checkZoneForCellsEmpty(shipsZoneCellList);

        } while (!clearPlace);

        return setShkonkaCharOnBoard(firstCellRndCord);
    }
}
