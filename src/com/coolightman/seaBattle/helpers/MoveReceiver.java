package com.coolightman.seaBattle.helpers;

import com.coolightman.seaBattle.exceptions.SBGameWrongCoordinateException;
import com.coolightman.seaBattle.exceptions.SBGameWrongLengthCoordinateException;

import java.util.Scanner;

public class MoveReceiver {

    private static int[] move = new int[2];

    public static int[] receivePlayerMove() {
        receiveMove();
        return move;
    }

    private static void receiveMove() {
        System.out.println("Do your move:\n>");
        String stringMove = receiveCommandLineMove();

        try {
            checkMoveLength(stringMove);
            checkMoveValid(stringMove);
        } catch (SBGameWrongLengthCoordinateException e) {
            System.out.println(e.getMessage());
            receiveMove();
        } catch (SBGameWrongCoordinateException e) {
            System.out.println(e.getMessage());
            receiveMove();
        }
    }

    private static String receiveCommandLineMove() {
        Scanner scanner = new Scanner(System.in);
        String stringMove = scanner.next();
        return stringMove.trim().toUpperCase();
    }

    private static void checkMoveLength(String stringMove) throws SBGameWrongLengthCoordinateException {
        if (stringMove.length() > 2) {
            throw new SBGameWrongLengthCoordinateException();
        } else if (stringMove.length() < 2) {
            throw new SBGameWrongLengthCoordinateException();
        }
    }

    private static void checkMoveValid(String stringMove) throws SBGameWrongCoordinateException {
        char columnLetter = stringMove.charAt(0);
        char lineNumber = stringMove.charAt(1);
        if (checkColumnLetter(columnLetter) == -1 | checkLineNumber(lineNumber) == -1)
            throw new SBGameWrongCoordinateException();
    }

    private static int checkColumnLetter(char columnLetter) {
        char[] columnsLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        for (int i = 0; i < 10; i++) {
            if (columnsLetters[i] == columnLetter) {
                move[0] = i;
                return i;
            }
        }
        return -1;
    }

    private static int checkLineNumber(char lineNumber) {
        int line = Character.getNumericValue(lineNumber);
        for (int i = 0; i < 10; i++) {
            if (line == i) {
                move[1] = i;
                return i;
            }
        }
        return -1;
    }
}