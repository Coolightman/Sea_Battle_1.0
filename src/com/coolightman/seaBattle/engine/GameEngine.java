package com.coolightman.seaBattle.engine;

import com.coolightman.seaBattle.helpers.ReceiveMoveHelper;
import com.coolightman.seaBattle.helpers.ShipInstallHelper;
import com.coolightman.seaBattle.model.Board;
import com.coolightman.seaBattle.model.EShip;
import com.coolightman.seaBattle.model.Figure;
import com.coolightman.seaBattle.model.Ship;

import java.util.ArrayList;

public class GameEngine {
    private static ArrayList<Ship> shipArrayList = new ArrayList<>();

    public static void start() {
        createModelsForGame();
        runGameProcess();
    }

    private static void createModelsForGame() {
        Board.createBoard();
        arrangeShipsOnBoard();

//        выводит поле со всеми кораблями
        Board.printBoardWithShips();
        Board.printGameBoard();
    }

    private static void runGameProcess() {
        boolean allShipsDead = false;
        int movesCounter = 0;
        int hitCounter = 0;

        do {

            int[] playerMove = ReceiveMoveHelper.receivePlayerMove();
            int moveCellNumber = ShipInstallHelper.numberOfCellFinder(playerMove);
            boolean haveHit = false;

            for (int i = 0; i < 10; i++) {
                ArrayList<Integer> shipCords = shipArrayList.get(i).getShipCords();
                for (int shipCord : shipCords) {
                    if (shipCord == moveCellNumber) {
                        if (!Board.getCellList().get(moveCellNumber).getCellChar().equals(Figure.X) &&
                                !Board.getCellList().get(moveCellNumber).getCellChar().equals(Figure.O)) {
                            Board.getCellList().get(moveCellNumber).setCellChar(Figure.O);
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

            if (!haveHit) {
                if (!Board.getCellList().get(moveCellNumber).getCellChar().equals(Figure.X) && !Board.getCellList().get(moveCellNumber).getCellChar().equals(Figure.O)) {
                    Board.getCellList().get(moveCellNumber).setCellChar(Figure.MISS);
                }
                System.out.println("Miss!");
            }

            if (hitCounter == 20) {
                allShipsDead = true;
            }

            Board.printGameBoard();

            movesCounter++;

        } while (!allShipsDead);

        System.out.println("You Win by " + movesCounter + " shots. Congratulations! =)");
    }

    private static boolean checkShipDead(int shipNumber) {
        boolean shipDead = false;
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
            shipDead = true;
            System.out.println("Ship " + shipArrayList.get(shipNumber).getFullShipName() + " is dead =(");
        }
        return shipDead;
    }

    public static void settings() {
        System.out.println("game settings");
//        TODO game settings
    }

    private static void arrangeShipsOnBoard() {

        shipArrayList.add(new Ship(EShip.LINKOR, 1));

        for (int i = 0; i < 2; i++) {
            shipArrayList.add(new Ship(EShip.KREISER, (i + 1)));
        }

        for (int i = 0; i < 3; i++) {
            shipArrayList.add(new Ship(EShip.ESMINEC, (i + 1)));
        }

        for (int i = 0; i < 4; i++) {
            shipArrayList.add(new Ship(EShip.SHKONKA, (i + 1)));
        }
    }
}
