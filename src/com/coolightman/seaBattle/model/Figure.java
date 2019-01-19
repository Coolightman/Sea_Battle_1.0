package com.coolightman.seaBattle.model;

public enum  Figure {
    X {
        public String toString() {
            return "X";
        }
    },

    O {
        public String toString() {
            return "O";
        }
    },

    EMPTY {
        public String toString() {
            return "_";
        }
    },

    MISS {
        public String toString() {
            return "*";
        }
    },

    SHIPED {
        public String toString(){return "#";}
    }
}
