package com.coolightman.seaBattle.helpers;

import java.util.Scanner;

public class ReceiveMoveHelper {
    public static int[] receivePlayerMove() {
        boolean moveReceivedGood = false;
        Character[] columnsLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        Character[] lineNumbChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        int[] playerMove = new int[2];
        System.out.println("Do your move:\n>");
        do {
            Scanner scanner = new Scanner(System.in);
            String playerStringMove = scanner.next();
            playerStringMove = playerStringMove.trim();
            playerStringMove = playerStringMove.toUpperCase();

            if (playerStringMove.length() > 2) {
                System.out.println("Too much symbols! Do your move again:\n>");
            } else if (playerStringMove.length() < 2) {
                System.out.println("Few symbols! Do your move again:\n>");
            } else {

                Character letter = playerStringMove.charAt(0);
                Character lineNumbChar = playerStringMove.charAt(1);

                for (int i = 0; i < 10; i++) {
                    if (letter.equals(columnsLetters[i])) {
                        playerMove[0] = i;
                        for (int j = 0; j < 10; j++) {
                            if (lineNumbChar.equals(lineNumbChars[j])) {
                                playerMove[1] = j;
                                moveReceivedGood = true;
                            }
                        }
                    }
                }
                if (!moveReceivedGood) {
                    System.out.println("Wrong format! Do your move again:\n>");
                }
            }
        } while (!moveReceivedGood);

        return playerMove;
    }
}