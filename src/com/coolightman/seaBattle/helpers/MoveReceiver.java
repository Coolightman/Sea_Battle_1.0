package com.coolightman.seaBattle.helpers;

//created by ${USER}
//${DATE} ${TIME}

import java.util.Scanner;

public class MoveReceiver {

    private static int[] playerMove = new int[2];

//        Algorithm:
//        1) receive inner value from command line
//        2) check value length
//        3) check move for validity
//        4) remake inner move to int[]

    public static int[] receivePlayerMove() {
        boolean moveIsValid = false;
        System.out.println("Do your move:\n>");

        do {
            String stringMove = receiveMoveFromCommandLine();
            if (checkMoveLength(stringMove)) {
                char columnLetter = stringMove.charAt(0);
                char lineNumber = stringMove.charAt(1);
                moveIsValid = checkMove(columnLetter, lineNumber);
                if (!moveIsValid) {
                    System.out.println("Wrong format! Do your move again:\n>");
                }
            }

        } while (!moveIsValid);

        return playerMove;
    }

    private static String receiveMoveFromCommandLine() {
        Scanner scanner = new Scanner(System.in);
        String playerStringMove = scanner.next();
        return playerStringMove.trim().toUpperCase();
    }

    private static boolean checkMoveLength(String stringMove) {
        if (stringMove.length() > 2) {
            System.out.println("Too much symbols! Do your move again:\n>");
            return false;
        } else if (stringMove.length() < 2) {
            System.out.println("Few symbols! Do your move again:\n>");
            return false;
        }
        return true;
    }

    private static boolean checkMove(char columnLetter, char lineNumber) {
        return !(checkColumnLetter(columnLetter) == -1 | checkLineNumber(lineNumber) == -1);
    }

    private static int checkColumnLetter(char columnLetter) {
        char[] columnsLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        for (int i = 0; i < 10; i++) {
            if (columnsLetters[i] == columnLetter) {
                playerMove[0] = i;
                return i;
            }
        }
        return -1;
    }

    private static int checkLineNumber(char lineNumber) {
        int line = Character.getNumericValue(lineNumber);
        for (int i = 0; i < 10; i++) {
            if (line == i) {
                playerMove[1] = i;
                return i;
            }
        }
        return -1;
    }


}