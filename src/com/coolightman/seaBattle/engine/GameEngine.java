package com.coolightman.seaBattle.engine;

import com.coolightman.seaBattle.helpers.ReceiveMoveHelper;
import com.coolightman.seaBattle.helpers.ShipInstallHelper;
import com.coolightman.seaBattle.model.Board;
import com.coolightman.seaBattle.model.EShip;
import com.coolightman.seaBattle.model.Ship;

import java.util.ArrayList;

public class GameEngine {
    private static ArrayList<Ship> shipArrayList = new ArrayList<>();
    private static Board board = new Board();

    public static void start() {
        createModelsForGame();
        runGameProcess();
    }

    private static void createModelsForGame() {
        arrangeShipsOnBoard(board);

//        выводит поле со всеми кораблями
//        board.printBoard();
        board.printGameBoard();
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
                        if (!board.getCellList().get(moveCellNumber).getCellChar().equals('X') &&
                                !board.getCellList().get(moveCellNumber).getCellChar().equals('O')) {
                            board.getCellList().get(moveCellNumber).setCellChar('O');
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
                if (!board.getCellList().get(moveCellNumber).getCellChar().equals('X') && !board.getCellList().get(moveCellNumber).getCellChar().equals('O')) {
                    board.getCellList().get(moveCellNumber).setCellChar('*');
                }
                System.out.println("Miss!");
            }

            if (hitCounter == 20) {
                allShipsDead = true;
            }

            board.printGameBoard();

            movesCounter++;

        } while (!allShipsDead);

        System.out.println("You Win by " + movesCounter + " shots. Congratulations! =)");
    }

    private static boolean checkShipDead(int shipNumber) {
        boolean shipDead = false;
        ArrayList<Integer> shipCords = shipArrayList.get(shipNumber).getShipCords();
        int damagedParts = 0;
        for (int shipCord : shipCords) {
            if (board.getCellList().get(shipCord).getCellChar().equals('O')) {
                damagedParts++;
            }
        }
        if (damagedParts == shipCords.size()) {
            for (int shipCord : shipCords) {
                board.getCellList().get(shipCord).setCellChar('X');
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

    private static void arrangeShipsOnBoard(Board board) {

        shipArrayList.add(new Ship(EShip.LINKOR, board, 1));

        for (int i = 0; i < 2; i++) {
            shipArrayList.add(new Ship(EShip.KREISER, board, (i + 1)));
        }

        for (int i = 0; i < 3; i++) {
            shipArrayList.add(new Ship(EShip.ESMINEC, board, (i + 1)));
        }

        for (int i = 0; i < 4; i++) {
            shipArrayList.add(new Ship(EShip.SHKONKA, board, (i + 1)));
        }
    }
}
