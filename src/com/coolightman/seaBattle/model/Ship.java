package com.coolightman.seaBattle.model;


import com.coolightman.seaBattle.helpers.ShipCreator;

import java.util.ArrayList;

public class Ship {
    private String fullShipName;
    private ArrayList<Integer> shipCords;

    public Ship(EShip ship, int number) {
        findSetterForShip(ship);
        this.fullShipName = ship + "_" + number;
    }

    private void findSetterForShip(EShip shipName) {
        switch (shipName) {
            case LINKOR:
                int shipSize = 4;
                this.shipCords = ShipCreator.setBigShipCords(shipSize);
                break;

            case KREISER:
                shipSize = 3;
                this.shipCords = ShipCreator.setBigShipCords(shipSize);
                break;

            case ESMINEC:
                shipSize = 2;
                this.shipCords = ShipCreator.setBigShipCords(shipSize);
                break;

            case SHKONKA:
                this.shipCords = ShipCreator.setLittleShipCords();
                break;
        }
    }

    public String getFullShipName() {
        return fullShipName;
    }

    public ArrayList<Integer> getShipCords() {
        return shipCords;
    }
}



