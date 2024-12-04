package org.example.bogglesolver;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class BoggleSolver {

    private enum Direction {
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

    private boolean dfs(BoggleBoard board, boolean[][] visited, String word, int i, int j, int idx) {
        if (!board.isValidCell(i, j) || visited[i][j] || board.get(i, j) != word.charAt(idx)) {
            return false;
        }
        if (idx == word.length() - 1) {
            return true;
        }

        visited[i][j] = true;

        boolean found = Arrays.stream(Direction.values())
                .anyMatch(dir -> dfs(board, visited, word, i + dir.getRowOffset(), j + dir.getColOffset(), idx + 1));

        visited[i][j] = false; // Backtrack
        return found;
    }

    public Set<String> findWords(BoggleBoard board, Set<String> dictionary) {
        Set<String> foundWords = new HashSet<>();
        boolean[][] visited = new boolean[board.getRowCount()][board.getColCount()];

        for (String word : dictionary) {
            for (int i = 0; i < board.getRowCount(); i++) {
                for (int j = 0; j < board.getColCount(); j++) {
                    if (dfs(board, visited, word, i, j, 0)) {
                        foundWords.add(word);
                    }
                }
            }
        }

        return foundWords;
    }
}
