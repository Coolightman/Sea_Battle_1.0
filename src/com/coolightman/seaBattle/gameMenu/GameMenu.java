package com.coolightman.seaBattle.gameMenu;

import com.coolightman.seaBattle.engine.GameEngine;

import java.util.Scanner;

public class GameMenu {
    public static void start() {
        boolean runMenuAgain = true;
        do {
            showMainMenu();
            String menuInnerVal = mainMenuInner();
            menuInnerVal = menuInnerVal.trim();

            if (menuInnerVal.length() > 1) {
                System.out.println("Too long number format. Try again:");
            } else {
                switch (menuInnerVal) {
                    case "1":
                        gameStarter();
                        runMenuAgain = false;
                        gameEnding();
                        break;

                    case "2":
                        gameSettings();
                        break;

                    case "3":
                        gameEnding();
                        runMenuAgain = false;
                        break;

                    default:
                        System.out.print(
                                "Wrong number. Choose another menu item:\n" +
                                        ">");
                }
            }
        } while (runMenuAgain);
    }

    private static void showMainMenu() {
        System.out.println(
                "Menu:\n" +
                        "1 - Start Game\n" +
                        "2 - Settings\n" +
                        "3 - Exit\n" +
                        ">");
    }

    private static String mainMenuInner() {
        Scanner menuScanner = new Scanner(System.in);
        return menuScanner.next();
    }

    private static void gameStarter() {
        boolean wannaGameRepeat;

        do {
            GameEngine.start();
            wannaGameRepeat = repeatGame();
        }
        while (wannaGameRepeat);
    }

    private static void gameSettings() {
        GameEngine.settings();
    }

    private static void gameEnding() {
        System.out.println(
                "Game End!\n" +
                        "Goodbye =)");
    }

    private static boolean repeatGame() {
        System.out.println(
                "Do you wan't repeat Game?\n" +
                        "1-Yes\n" +
                        "2-No. Exit.\n" +
                        ">");

        boolean wannaGameRepeat = true;
        boolean runRepeatMenuAgain = true;
        String menuInnerValue = mainMenuInner();

        do {
            switch (menuInnerValue) {
                case "1":
                    runRepeatMenuAgain = false;
                    break;

                case "2":
                    runRepeatMenuAgain = false;
                    wannaGameRepeat = false;
                    break;

                default:
                    System.out.print(
                            "Choose another menu item!\n" +
                                    ">");
            }
        } while (runRepeatMenuAgain);

        return wannaGameRepeat;
    }
}
