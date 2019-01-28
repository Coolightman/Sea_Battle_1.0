package com.coolightman.seaBattle.gameMenu;

import com.coolightman.seaBattle.engine.GameEngine;
import com.coolightman.seaBattle.exceptions.SBGameWrongMenuNumberException;

import java.io.IOException;
import java.util.Scanner;

public class GameMenu {

    public static void start() {
        mainMenu();
    }

    private static void mainMenu() {
        showMainMenu();
        int userMenuVal = menuInner();
        switch (userMenuVal) {
            case 1:
                GameEngine.start();
                repeatMenu();
                gameEnding();
                break;

            case 2:
                gameSettings();
                break;

            case 3:
                gameEnding();
                break;

            default:
                System.out.println("Wrong menu number. Try again:");
                mainMenu();
        }
    }

    private static void repeatMenu() {
        showRepeatMenu();
        int userMenuVal = repeatMenuInner();
        switch (userMenuVal) {
            case 1:
                mainMenu();
                break;

            case 2:
                break;

            default:
                System.out.println("Wrong menu number. Try again:");
                repeatMenu();
        }
    }

    private static void showMainMenu() {
        System.out.println(
                "Menu:\n" +
                        "1 - Start Game\n" +
                        "2 - Settings\n" +
                        "3 - Exit\n" +
                        ">");
    }

    private static void showRepeatMenu() {
        System.out.println(
                "Do you wan't repeat Game?\n" +
                        "1-Yes\n" +
                        "2-No. Exit.\n" +
                        ">");
    }

    private static int menuInner() {
        Scanner menuScanner = new Scanner(System.in);
        int inMenuVal = -1;
        try {
            inMenuVal = menuScanner.nextInt();
        } catch (Exception e) {
            System.out.println("Inner Value has wrong format! Try again:");
            mainMenu();
        }
        return inMenuVal;
    }

    private static int repeatMenuInner() {
        Scanner menuScanner = new Scanner(System.in);
        int inMenuVal = -1;
        try {
            inMenuVal = menuScanner.nextInt();
        } catch (Exception e) {
            System.out.println("Inner Value has wrong format! Try again:");
            repeatMenu();
        }
        return inMenuVal;
    }

    private static void gameSettings() {
        GameEngine.settings();
    }

    private static void gameEnding() {
        System.out.println(
                "Game End!\n" +
                        "Goodbye =)");
    }
}
