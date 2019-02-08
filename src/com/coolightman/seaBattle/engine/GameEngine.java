package com.coolightman.seaBattle.engine;


import com.coolightman.seaBattle.model.Board;
import com.coolightman.seaBattle.model.EShip;
import com.coolightman.seaBattle.model.Figure;
import com.coolightman.seaBattle.model.Ship;

import java.util.ArrayList;

import static com.coolightman.seaBattle.helpers.MoveReceiver.receivePlayerMove;
import static com.coolightman.seaBattle.model.Board.*;

public class GameEngine {
    private static ArrayList<Ship> shipArrayList = new ArrayList<>();
    private static int linkorAmt = 1;
    private static int kreiserAmt = 2;
    private static int esminecAmt = 3;
    private static int shkonkaAmt = 4;
    private static int sumOfShips = linkorAmt + kreiserAmt + esminecAmt + shkonkaAmt;
    private static int sumOfDecks = linkorAmt * 4 + kreiserAmt * 3 + esminecAmt * 2 + shkonkaAmt;
    private static int moveCellNumber;
    private static int hitCounter;
    private static int movesCounter;
    private static int shipNumber;
    private static boolean wasHit;

    public static void start() {
        createModelsForGame();
        runGameProcess();
    }

    private static void createModelsForGame() {
        createBoard();
        arrangeShipsOnBoard();
        printBoardWithShips();
//        printGameBoard();
    }

    private static void runGameProcess() {
        do {
            wasHit = false; //flag for check miss
            doMove();
            printBoardWithShips();
//            printGameBoard();
            movesCounter++;
        } while (!checkWin(hitCounter));
        System.out.println("You Win by " + movesCounter + " shots. Congratulations! =)");
    }

    private static void doMove() {
        moveCellNumber = receivePlayerMove();
        checkHit();
    }

    private static void checkHit() {
        for (shipNumber = 0; shipNumber < sumOfShips; shipNumber++) {
            ArrayList<Integer> shipCords = receiveShipCords(shipNumber);
            checkShipCordsHit(shipCords);
        }
        checkMiss();
    }

    private static void checkShipCordsHit(ArrayList<Integer> shipCords) {
        for (int shipCord : shipCords) {
            checkShipCordHit(shipCord);
        }
    }

    private static void checkShipCordHit(int shipCord) {
        if (shipCord == moveCellNumber) {
            tryHitShipCord();
        }
    }

    private static void tryHitShipCord() {
        Figure shipCordFigure = getCellFigure(moveCellNumber);
        if (shipCordFigure.equals(Figure.SHIPED)) {
            hitCurrentShip();
        }
    }

    private static void hitCurrentShip() {
        setCellFigure(moveCellNumber, Figure.O);
        wasHit = true;
        checkShipStatus();
    }

    private static void checkShipStatus() {
        if (allDecksHited()) {
            setShipStatusDead();
        } else {
            System.out.println("Injured!");
        }
    }

    private static boolean allDecksHited() {
        int numberHitedDecks = countHitedDecks();
        return numberHitedDecks == hitedShipSize();
    }

    private static void setShipStatusDead() {
        ArrayList<Integer> deadShipCords = receiveShipCords(shipNumber);
        for (int shipCord : deadShipCords) {
            setCellFigure(shipCord, Figure.X);
        }
        System.out.println("Ship died!");
    }

    private static void checkMiss() {
        if (cellIsEmpty()) {
            setCellFigure(moveCellNumber, Figure.MISS);
            System.out.println("Miss!");
        } else if (!wasHit && getCellFigure(moveCellNumber).equals(Figure.O)) {
            System.out.println("Injured");
        } else if (!wasHit && getCellFigure(moveCellNumber).equals(Figure.X)) {
            System.out.println("Miss!");
        }
    }

    private static boolean cellIsEmpty() {
        return !wasHit && !getCellFigure(moveCellNumber).equals(Figure.O)
                && !getCellFigure(moveCellNumber).equals(Figure.X);
    }

    private static int countHitedDecks() {
        ArrayList<Integer> hitedShipCords = receiveShipCords(shipNumber);
        int hitAmt = 0;
        for (int shipCord : hitedShipCords) {
            if (getCellFigure(shipCord).equals(Figure.O)) {
                hitAmt++;
            }
        }
        return hitAmt;
    }

    private static int hitedShipSize() {
        ArrayList<Integer> hitedShipCords = receiveShipCords(shipNumber);
        return hitedShipCords.size();
    }

    private static ArrayList<Integer> receiveShipCords(int shipNumber) {
        return shipArrayList.get(shipNumber).getShipCords();
    }

    private static Figure getCellFigure(int moveCellNumber) {
        return Board.getCellList().get(moveCellNumber).getCellChar();
    }

    private static void setCellFigure(int cellNUmber, Figure figure) {
        Board.getCellList().get(cellNUmber).setCellChar(figure);
    }

    private static boolean checkWin(int hitCounter) {
        if (wasHit) {
            hitCounter++;
        }
        return hitCounter == sumOfDecks;
    }

    public static void settings() {
        System.out.println("game settings");
//        TODO game settings
    }

    private static void arrangeShipsOnBoard() {
        for (int i = 0; i < linkorAmt; i++) {
            shipArrayList.add(new Ship(EShip.LINKOR, (i + 1)));
        }

        for (int i = 0; i < kreiserAmt; i++) {
            shipArrayList.add(new Ship(EShip.KREISER, (i + 1)));
        }

        for (int i = 0; i < esminecAmt; i++) {
            shipArrayList.add(new Ship(EShip.ESMINEC, (i + 1)));
        }

        for (int i = 0; i < shkonkaAmt; i++) {
            shipArrayList.add(new Ship(EShip.SHKONKA, (i + 1)));
        }
    }
}
