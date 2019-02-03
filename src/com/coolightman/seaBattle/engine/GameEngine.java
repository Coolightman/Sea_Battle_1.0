package com.coolightman.seaBattle.engine;


import com.coolightman.seaBattle.exceptions.SBGameIsNotEndException;
import com.coolightman.seaBattle.model.Board;
import com.coolightman.seaBattle.model.EShip;
import com.coolightman.seaBattle.model.Figure;
import com.coolightman.seaBattle.model.Ship;

import java.util.ArrayList;

import static com.coolightman.seaBattle.helpers.MoveReceiver.receivePlayerMove;
import static com.coolightman.seaBattle.helpers.ShipCreatorHelper.numberOfCellFinder;

public class GameEngine {
    private static ArrayList<Ship> shipArrayList = new ArrayList<>();
    private static int linkorNumb = 1;
    private static int kreiserNumb = 2;
    private static int esminecNumb = 3;
    private static int shkonkaNumb = 4;
    private static int sumOfShips = linkorNumb + kreiserNumb + esminecNumb + shkonkaNumb;
    private static int moveCellNumber;
    private static boolean haveHit;
    private static int hitCounter;
    private static int movesCounter;


    public static void start() {
        createModelsForGame();
        runGameProcess();
    }

    private static void createModelsForGame() {
        Board.createBoard();
        arrangeShipsOnBoard();
        Board.printBoardWithShips();
        Board.printGameBoard();
    }

    private static void runGameProcess() {
        try {
            tryNextMove();
            System.out.println("You Win by " + movesCounter + " shots. Congratulations! =)");
        } catch (SBGameIsNotEndException e) {
            runGameProcess();
        }
    }

    private static void tryNextMove() throws SBGameIsNotEndException {
        int[] playerMove = receivePlayerMove();
        moveCellNumber = numberOfCellFinder(playerMove);
        haveHit = false;

        for (int i = 0; i < sumOfShips; i++) {
            ArrayList<Integer> shipCords = shipArrayList.get(i).getShipCords();
            for (int shipCord : shipCords) {
                if (shipCord == moveCellNumber) {
                    if (!getCellFigure(moveCellNumber).equals(Figure.X) && !getCellFigure(moveCellNumber).equals(Figure.O)) {
                        setCellFigure(Figure.O);
                        boolean shipDead = checkShipDead(i);
                        haveHit = true;
                        hitCounter++;
                        if (!shipDead) {
                            System.out.println("Injured");
                        }
                        break;
                    }
                }
            }
            if (haveHit) break;
        }

        checkMiss();
        movesCounter++;
        Board.printGameBoard();
        if (!checkWin(hitCounter)) throw new SBGameIsNotEndException();
    }

    private static void setCellFigure(Figure figure) {
        Board.getCellList().get(moveCellNumber).setCellChar(figure);
    }

    private static boolean checkWin(int hitCounter) {
        return hitCounter == 20;
    }

    private static void checkMiss() {
        if (!haveHit) {
            System.out.println("Miss!");
            if (!getCellFigure(moveCellNumber).equals(Figure.X) && !getCellFigure(moveCellNumber).equals(Figure.O)) {
                setCellFigure(Figure.MISS);
            }
        }
    }

    private static Figure getCellFigure(int moveCellNumber) {
        return Board.getCellList().get(moveCellNumber).getCellChar();
    }

    private static boolean checkShipDead(int shipNumber) {
        ArrayList<Integer> shipCords = shipArrayList.get(shipNumber).getShipCords();
        int damagedParts = 0;

        for (int shipCord : shipCords) {
            if (Board.getCellList().get(shipCord).getCellChar().equals(Figure.O)) {
                damagedParts++;
            }
        }

        if (damagedParts == shipCords.size()) {
            for (int shipCord : shipCords) {
                Board.getCellList().get(shipCord).setCellChar(Figure.X);
            }
            System.out.println("Ship " + shipArrayList.get(shipNumber).getFullShipName() + " is dead =(");
            return true;
        }
        return false;
    }

    public static void settings() {
        System.out.println("game settings");
//        TODO game settings
    }

    private static void arrangeShipsOnBoard() {

        int linkorNumb = 1;
        for (int i = 0; i < linkorNumb; i++) {
            shipArrayList.add(new Ship(EShip.LINKOR, (i + 1)));
        }
        int kreiserNumb = 2;
        for (int i = 0; i < kreiserNumb; i++) {
            shipArrayList.add(new Ship(EShip.KREISER, (i + 1)));
        }

        int esminecNumb = 3;
        for (int i = 0; i < esminecNumb; i++) {
            shipArrayList.add(new Ship(EShip.ESMINEC, (i + 1)));
        }

        int shkonkaNumb = 4;
        for (int i = 0; i < shkonkaNumb; i++) {
            shipArrayList.add(new Ship(EShip.SHKONKA, (i + 1)));
        }
    }
}
