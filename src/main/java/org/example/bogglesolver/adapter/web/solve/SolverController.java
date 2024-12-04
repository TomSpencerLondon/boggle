package org.example.bogglesolver.adapter.web.solve;

import org.example.bogglesolver.hexagon.domain.Board;
import org.example.bogglesolver.hexagon.domain.Solver;
import org.example.bogglesolver.hexagon.application.CsvLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class SolverController {

    @Autowired
    private CsvLoader csvLoader;

    @Autowired
    private Solver solver;

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
            Board board = grid.toBoggleBoard();

            Set<String> dictionary = csvLoader.getDictionary().keySet();
            Set<String> foundWords = solver.findWords(board, dictionary);

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
}

