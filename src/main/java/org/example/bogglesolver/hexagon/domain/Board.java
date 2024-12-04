package org.example.bogglesolver.hexagon.domain;
import java.util.List;

public class Board {
    private final List<List<String>> board;

    public Board(List<List<String>> board) {
        this.board = board;
    }

    public String get(int i, int j) {
        return board.get(i).get(j);
    }

    public void set(int i, int j, String c) {
        board.get(i).set(j, c);
    }

    public int getRowCount() {
        return board.size();
    }

    public int getColCount() {
        return board.getFirst().size();
    }

    public boolean isValidCell(int i, int j) {
        return i >= 0 && i < getRowCount() && j >= 0 && j < getColCount();
    }
}

