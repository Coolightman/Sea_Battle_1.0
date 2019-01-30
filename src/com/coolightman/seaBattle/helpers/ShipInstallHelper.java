package com.coolightman.seaBattle.helpers;

import com.coolightman.seaBattle.exceptions.SBGameBusyZoneException;
import com.coolightman.seaBattle.model.Board;
import com.coolightman.seaBattle.model.Figure;

import java.util.ArrayList;

public class ShipInstallHelper {
    //    выбор рандомных координат первой ячейки
    static int[] chooseRndValidCell() {
        int[] rndNumb = new int[2];
        rndNumb[0] = (int) (Math.random() * 10);
        rndNumb[1] = (int) (Math.random() * 10);

//        TODO check for cell empty
        return rndNumb;
    }

    //      выбор рандомного направления для установки больших кораблей
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

    //      установка чаров больших кораблей на доску
    static ArrayList<Integer> setBigShipCharsOnBoard(EDirection currentDirection, int[] firstCellRndCord, int SIZE) {
        int numberOfFirstCell = numberOfCellFinder(firstCellRndCord);
        int[] anotherCellsNumbers = numbersOfAnotherCellsFinder(numberOfFirstCell, SIZE, currentDirection);
        ArrayList<Integer> shipCords = new ArrayList<>();

        for (int anotherCellNumber : anotherCellsNumbers) {
            Board.getCellList().get(anotherCellNumber).setCellChar(Figure.SHIPED);
            shipCords.add(anotherCellNumber);
        }
        return shipCords;
    }

    //       установка чара однопалубного корабля
    static ArrayList<Integer> setShkonkaCharOnBoard(int[] firstCellRndCord) {
        int numberOfCell = numberOfCellFinder(firstCellRndCord);
        ArrayList<Integer> shipCord = new ArrayList<>();
        shipCord.add(numberOfCell);
        Board.getCellList().get(numberOfCell).setCellChar(Figure.SHIPED);
        return shipCord;
    }

    //    поиск номера ячейки по координате
    public static int numberOfCellFinder(int[] firstCellCord) {
        return firstCellCord[1] * 10 + firstCellCord[0];
    }

    //    поиск всего ряда ячеек для корабля в зав-сти от первой ячейки и направления
    private static int[] numbersOfAnotherCellsFinder(int firstCellNumber, int size, EDirection direction) {
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

    private static boolean checkCellsEmpty(int cellNumber) {
        boolean empty = false;

        if (!Board.getCellList().get(cellNumber).getCellChar().equals(Figure.SHIPED)) {
            empty = true;
        }

        return empty;
    }

    static void checkZoneForCellsEmpty(ArrayList<int[]> shipsZoneCellList) throws SBGameBusyZoneException {

//              создаем коллекцию с не выходящими за границы вариантами окружающих ячеек (нах-ся в пределах поля)
        ArrayList<int[]> validShipsZoneCellList = new ArrayList<>();

        for (int[] shipsZoneCell : shipsZoneCellList) {
            if (shipsZoneCell[0] >= 0 && shipsZoneCell[0] <= 9 &&
                    shipsZoneCell[1] >= 0 && shipsZoneCell[1] <= 9) {
                validShipsZoneCellList.add(shipsZoneCell);
            }
        }

//            создаем коллекцию с порядковыми номерами проверяемых ячеек
        ArrayList<Integer> cellNumbList = new ArrayList<>();
        for (int[] shipsZoneCell : validShipsZoneCellList) {
            cellNumbList.add(ShipInstallHelper.numberOfCellFinder(shipsZoneCell));
        }

//             проверяем все ячейки на занятость
        int goodCount = 0;
        for (int cellNumb : cellNumbList) {
            if (!checkCellsEmpty(cellNumb)) {
                goodCount++;
            }
        }

        if (goodCount != 0) {
            throw new SBGameBusyZoneException();
        }
    }

    //    находим зону проверки незанятости для большого корабля
    static ArrayList<int[]> createZoneCellList(EDirection currentDirection, int[] firstCellRndCord, int SIZE) {
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

    //    проверяем что бы достраиваемые ячейки корабля не выходили за границы поля
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
