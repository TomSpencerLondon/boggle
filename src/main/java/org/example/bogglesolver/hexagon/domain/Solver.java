package org.example.bogglesolver.hexagon.domain;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class Solver {
    private boolean dfs(Board board, boolean[][] visited, String word, int i, int j, int idx) {
        if (!board.isValidCell(i, j) || visited[i][j] || !board.get(i, j).equalsIgnoreCase(word.substring(idx, idx + 1))) {
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

    public Set<String> findWords(Board board, Set<String> dictionary) {
        Set<String> foundWords = new HashSet<>();
        boolean[][] visited = new boolean[board.getRowCount()][board.getColCount()];

        for (String word : dictionary) {
            for (int i = 0; i < board.getRowCount(); i++) {
                for (int j = 0; j < board.getColCount(); j++) {
                    if (dfs(board, visited, word, i, j, 0)) {
                        foundWords.add(word.toUpperCase());
                    }
                }
            }
        }

        return foundWords;
    }
}
