package org.example.bogglesolver.adapter.web.solve;

import org.example.bogglesolver.hexagon.domain.Board;

import java.util.List;

public class Grid {
    private String cell1;
    private String cell2;
    private String cell3;
    private String cell4;
    private String cell5;
    private String cell6;
    private String cell7;
    private String cell8;
    private String cell9;
    private String cell10;
    private String cell11;
    private String cell12;
    private String cell13;
    private String cell14;
    private String cell15;
    private String cell16;

    // Getters and Setters for each cell
    public String getCell1() { return cell1; }
    public void setCell1(String cell1) { this.cell1 = cell1; }
    public String getCell2() { return cell2; }
    public void setCell2(String cell2) { this.cell2 = cell2; }
    public String getCell3() { return cell3; }
    public void setCell3(String cell3) { this.cell3 = cell3; }
    public String getCell4() { return cell4; }
    public void setCell4(String cell4) { this.cell4 = cell4; }
    public String getCell5() { return cell5; }
    public void setCell5(String cell5) { this.cell5 = cell5; }
    public String getCell6() { return cell6; }
    public void setCell6(String cell6) { this.cell6 = cell6; }
    public String getCell7() { return cell7; }
    public void setCell7(String cell7) { this.cell7 = cell7; }
    public String getCell8() { return cell8; }
    public void setCell8(String cell8) { this.cell8 = cell8; }
    public String getCell9() { return cell9; }
    public void setCell9(String cell9) { this.cell9 = cell9; }
    public String getCell10() { return cell10; }
    public void setCell10(String cell10) { this.cell10 = cell10; }
    public String getCell11() { return cell11; }
    public void setCell11(String cell11) { this.cell11 = cell11; }
    public String getCell12() { return cell12; }
    public void setCell12(String cell12) { this.cell12 = cell12; }
    public String getCell13() { return cell13; }
    public void setCell13(String cell13) { this.cell13 = cell13; }
    public String getCell14() { return cell14; }
    public void setCell14(String cell14) { this.cell14 = cell14; }
    public String getCell15() { return cell15; }
    public void setCell15(String cell15) { this.cell15 = cell15; }
    public String getCell16() { return cell16; }
    public void setCell16(String cell16) { this.cell16 = cell16; }

    // Convert to BoggleBoard
    public Board toBoggleBoard() {
        List<List<String>> boardData = List.of(
                List.of(cell1, cell2, cell3, cell4),
                List.of(cell5, cell6, cell7, cell8),
                List.of(cell9, cell10, cell11, cell12),
                List.of(cell13, cell14, cell15, cell16)
        );
        return new Board(boardData);
    }
}

