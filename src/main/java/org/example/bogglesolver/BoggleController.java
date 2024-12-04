package org.example.bogglesolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class BoggleController {

    @Autowired
    private CsvLoader csvLoader;

    @Autowired
    private BoggleSolver boggleSolver;

    @GetMapping("/")
    public String showBoggleBoard(Model model) {
        model.addAttribute("grid", new Grid()); // Ensure the grid object is initialized for the form
        return "boggle"; // Return the Thymeleaf template
    }

    // Catch-all handler for any other GET request
    @GetMapping("/**")
    public String redirectToHome() {
        return "redirect:/";
    }

    @PostMapping("/boggle")
    public String solveBoggle(Grid grid, Model model) {
        try {
            BoggleBoard board = grid.toBoggleBoard();

            Set<String> dictionary = csvLoader.getDictionary().keySet();
            Set<String> foundWords = boggleSolver.findWords(board, dictionary);

            Map<String, String> results = new HashMap<>();
            for (String word : foundWords) {
                results.put(word, csvLoader.getDefinition(word));
            }

            model.addAttribute("results", results);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "boggle";
    }


    private static BoggleBoard getBoggleBoard(String[] grid) {
        if (grid == null || grid.length != 16) {
            throw new IllegalArgumentException("The Boggle board must contain exactly 16 letters.");
        }

        List<List<Character>> boardData = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<Character> row = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                row.add(grid[i * 4 + j].toUpperCase().charAt(0));
            }
            boardData.add(row);
        }
        BoggleBoard board = new BoggleBoard(boardData);
        return board;
    }


}

