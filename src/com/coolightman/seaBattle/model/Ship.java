package com.coolightman.seaBattle.model;


import com.coolightman.seaBattle.helpers.ShipInstall;

import java.util.ArrayList;

public class Ship {
    private String fullShipName;
    private Board board;
    private ArrayList<Integer> shipCords;

    public Ship(EShip ship, Board board, int number) {
        this.board = board;
        findSetterForShip(ship);
        this.fullShipName = ship + "_" + number;
    }

    private void findSetterForShip(EShip shipName) {
        switch (shipName) {
            case LINKOR:
                int shipSize = 4;
                this.shipCords = ShipInstall.setBigShipCords(board, shipSize);
                break;

            case KREISER:
                shipSize = 3;
                this.shipCords = ShipInstall.setBigShipCords(board, shipSize);
                break;

            case ESMINEC:
                shipSize = 2;
                this.shipCords = ShipInstall.setBigShipCords(board, shipSize);
                break;

            case SHKONKA:
                shipSize = 1;
                this.shipCords = ShipInstall.setShkonkaCords(board);
                break;

            default:
                shipSize = 0;
        }
    }

    public String getFullShipName() {
        return fullShipName;
    }

    public ArrayList<Integer> getShipCords() {
        return shipCords;
    }
}



