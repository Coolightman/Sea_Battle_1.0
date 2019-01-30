package com.coolightman.seaBattle.exceptions;
//created by Coolightman
//29.01.2019 10:40

public class SBGameWrongCoordinateException extends SBGameException {
    @Override
    public String getMessage() {
        return "Wrong coordinate. Try again!";
    }
}
