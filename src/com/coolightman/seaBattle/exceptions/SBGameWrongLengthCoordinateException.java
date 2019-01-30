package com.coolightman.seaBattle.exceptions;
//created by Coolightman
//30.01.2019 15:13

public class SBGameWrongLengthCoordinateException extends SBGameException {
    @Override
    public String getMessage() {
        return "Wrong inner value length! Try again!";
    }
}
