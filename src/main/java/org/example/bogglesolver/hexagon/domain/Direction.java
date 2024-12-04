package org.example.bogglesolver.hexagon.domain;

enum Direction {
    DOWN(1, 0),
    UP(-1, 0),
    RIGHT(0, 1),
    LEFT(0, -1),
    DOWN_RIGHT(1, 1),
    UP_RIGHT(-1, 1),
    DOWN_LEFT(1, -1),
    UP_LEFT(-1, -1);

    private final int rowOffset;
    private final int colOffset;

    Direction(int rowOffset, int colOffset) {
        this.rowOffset = rowOffset;
        this.colOffset = colOffset;
    }

    public int getRowOffset() {
        return rowOffset;
    }

    public int getColOffset() {
        return colOffset;
    }
}