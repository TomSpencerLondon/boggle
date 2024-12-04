package org.example.bogglesolver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BoggleSolverTest {

    private BoggleSolver solver;
    private BoggleBoard board;

    @BeforeEach
    void setUp() {
        solver = new BoggleSolver();
        board = new BoggleBoard(List.of(
                List.of('G', 'I', 'Z'),
                List.of('U', 'E', 'K'),
                List.of('Q', 'S', 'E')
        ));
    }

    @Test
    void findWordsWithValidWords() {
        Set<String> dictionary = Set.of("GEEKS", "QUIZ", "SEE", "SEEK", "HELLO");
        Set<String> foundWords = solver.findWords(board, dictionary);

        assertThat(foundWords).containsExactlyInAnyOrder("GEEKS", "QUIZ", "SEE", "SEEK");
    }
}
