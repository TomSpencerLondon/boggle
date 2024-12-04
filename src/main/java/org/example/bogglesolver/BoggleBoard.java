package org.example.bogglesolver;
import java.util.List;

public class BoggleBoard {
    private final List<List<Character>> board;

    public BoggleBoard(List<List<Character>> board) {
        this.board = board;
    }

    public Character get(int i, int j) {
        return board.get(i).get(j);
    }

    public void set(int i, int j, Character c) {
        board.get(i).set(j, c);
    }

    public int getRowCount() {
        return board.size();
    }

    public int getColCount() {
        return board.get(0).size();
    }

    public boolean isValidCell(int i, int j) {
        return i >= 0 && i < getRowCount() && j >= 0 && j < getColCount();
    }
}

