package org.example.bogglesolver;

import org.example.bogglesolver.hexagon.domain.Board;
import org.example.bogglesolver.hexagon.domain.Solver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BoggleSolverTest {

    private Solver solver;
    private Board board;

    @BeforeEach
    void setUp() {
        solver = new Solver();
        board = new Board(List.of(
                List.of("G", "I", "Z"),
                List.of("U", "E", "K"),
                List.of("Q", "S", "E")
        ));
    }

    @Test
    void findWordsWithValidWords() {
        Set<String> dictionary = Set.of("GEEKS", "QUIZ", "SEE", "SEEK", "HELLO");
        Set<String> foundWords = solver.findWords(board, dictionary);

        assertThat(foundWords).containsExactlyInAnyOrder("GEEKS", "QUIZ", "SEE", "SEEK");
    }

    @Test
    void findWordsWithNoValidWords() {
        Set<String> dictionary = Set.of("HELLO", "WORLD", "JAVA");
        Set<String> foundWords = solver.findWords(board, dictionary);

        assertThat(foundWords).isEmpty();
    }

    @Test
    void findWordsWithEmptyDictionary() {
        Set<String> dictionary = Set.of();
        Set<String> foundWords = solver.findWords(board, dictionary);

        assertThat(foundWords).isEmpty();
    }


    @Test
    void findWordsCaseInsensitiveWords() {
        Set<String> dictionary = Set.of("quiz", "GeekS", "See", "HELLO");
        Set<String> foundWords = solver.findWords(board, dictionary);

        assertThat(foundWords).containsExactlyInAnyOrder("QUIZ", "SEE", "GEEKS");
    }

}
