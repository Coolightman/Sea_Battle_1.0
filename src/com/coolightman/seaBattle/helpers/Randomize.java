package com.coolightman.seaBattle.helpers;
//created by Coolightman
//31.01.2019 20:47

import com.coolightman.seaBattle.exceptions.SBGameBusyCell;

import static com.coolightman.seaBattle.helpers.ShipCreatorHelper.checkCellsEmpty;
import static com.coolightman.seaBattle.helpers.ShipCreatorHelper.numberOfCellFinder;

class Randomize {

    private static int[] randomCellCord = new int[2];

    static int[] chooseRndValidCell() {
        tryChooseRandomCell();
        return randomCellCord;
    }

    private static void tryChooseRandomCell() {
        randomCellCord[0] = (int) (Math.random() * 10);
        randomCellCord[1] = (int) (Math.random() * 10);

        int numberOfCell = numberOfCellFinder(randomCellCord);

        try {
            checkCellsEmpty(numberOfCell);
        } catch (SBGameBusyCell e) {
            tryChooseRandomCell();
        }
    }

    static EDirection directionRndChooser() {
        EDirection currentDirection;
        int directionNumb = (int) (Math.random() * 4 + 1);

        switch (directionNumb) {
            case 1:
                currentDirection = EDirection.NORTH;
                break;
            case 2:
                currentDirection = EDirection.SOUTH;
                break;
            case 3:
                currentDirection = EDirection.WEST;
                break;
            case 4:
                currentDirection = EDirection.EAST;
                break;
            default:
                currentDirection = null;
        }
        return currentDirection;
    }

}
