package com.coolightman.seaBattle.helpers;

//created by ${USER}
//${DATE} ${TIME}

import com.coolightman.seaBattle.exceptions.SBGameWrongCoordinateException;
import com.coolightman.seaBattle.exceptions.SBGameWrongLengthCoordinateException;

import java.util.Scanner;

public class MoveReceiver {

    private static int[] playerMove = new int[2];

//        Algorithm:
//        1) receive inner value from command line
//        2) check value length
//        3) check move for validity
//        4) remake inner move to int[]

    public static int[] receivePlayerMove() {
        receiveMove();
        return playerMove;
    }

    private static void receiveMove() {
        try {
            System.out.println("Do your move:\n>");
            String stringMove = receiveMoveFromCommandLine();
            checkMoveLength(stringMove);
            char columnLetter = stringMove.charAt(0);
            char lineNumber = stringMove.charAt(1);
            checkMove(columnLetter, lineNumber);
        } catch (SBGameWrongLengthCoordinateException e) {
            System.out.println(e.getMessage());
            receiveMove();
        } catch (SBGameWrongCoordinateException e){
            System.out.println(e.getMessage());
            receiveMove();
        }
    }

    private static String receiveMoveFromCommandLine() {
        Scanner scanner = new Scanner(System.in);
        String playerStringMove = scanner.next();
        return playerStringMove.trim().toUpperCase();
    }

    private static void checkMoveLength(String stringMove) throws SBGameWrongLengthCoordinateException {
        if (stringMove.length() > 2) {
            throw new SBGameWrongLengthCoordinateException();
        } else if (stringMove.length() < 2) {
            throw new SBGameWrongLengthCoordinateException();
        }
    }

    private static void checkMove(char columnLetter, char lineNumber) throws SBGameWrongCoordinateException {
        if (checkColumnLetter(columnLetter) == -1 | checkLineNumber(lineNumber) == -1)
            throw new SBGameWrongCoordinateException();
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