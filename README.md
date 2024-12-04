# boggle

![image](https://github.com/user-attachments/assets/a92f73e8-d20b-489f-a036-8d54435da4a5)

### How Depth First Search (DFS) Works in the `BoggleSolver` Algorithm

The `dfs` (depth-first search) method in the `BoggleSolver` explores the Boggle board to find if a given word exists. Here's a step-by-step explanation of how it works:

---

### **Key Concepts**

1. **Recursive Exploration**:
    - The DFS algorithm explores all possible paths in the grid recursively to match the given word.
    - It starts from a specific cell and attempts to match the first character of the word.
    - If successful, it moves to neighboring cells to match subsequent characters.

2. **Backtracking**:
    - To avoid revisiting cells during the current path, the algorithm uses a `visited` array to track visited cells.
    - After finishing exploration along a path, it "unvisits" cells to allow them to be used in other paths.

3. **Direction Handling**:
    - The algorithm uses an `enum Direction` to represent all possible moves from a cell: up, down, left, right, and diagonals.

---

### **Step-by-Step Explanation of `dfs`**

```java
private boolean dfs(BoggleBoard board, boolean[][] visited, String word, int i, int j, int idx) {
```

1. **Base Case Validation**:
   ```java
   if (!board.isValidCell(i, j) || visited[i][j] || board.get(i, j) != word.charAt(idx)) {
       return false;
   }
   ```
    - **Out-of-Bounds Check**: `!board.isValidCell(i, j)` ensures that the cell `(i, j)` is within the grid.
    - **Already Visited**: `visited[i][j]` prevents revisiting the same cell in the current path.
    - **Character Mismatch**: `board.get(i, j) != word.charAt(idx)` stops the search if the current cell's character doesn't match the expected character in the word.

2. **Word Match Found**:
   ```java
   if (idx == word.length() - 1) {
       return true;
   }
   ```
    - If the current index (`idx`) is the last character of the word, the algorithm confirms the word exists on the board.

3. **Mark the Cell as Visited**:
   ```java
   visited[i][j] = true;
   ```
    - The algorithm marks the current cell as visited to prevent revisiting it during this path.

4. **Recursive Exploration**:
   ```java
   boolean found = Arrays.stream(Direction.values())
           .anyMatch(dir -> dfs(board, visited, word, i + dir.getRowOffset(), j + dir.getColOffset(), idx + 1));
   ```
    - The algorithm explores all possible directions using the `Direction` enum.
    - For each direction, it calls `dfs` recursively on the neighboring cell, incrementing the index (`idx + 1`) to check the next character of the word.

5. **Backtrack**:
   ```java
   visited[i][j] = false;
   ```
    - After exploring all paths from the current cell, it resets the `visited` status of the cell.
    - This allows the cell to be reused in other paths.

6. **Return the Result**:
   ```java
   return found;
   ```
    - If any path successfully finds the word, the function returns `true`.

---

### **How `findWords` Uses DFS**

```java
public Set<String> findWords(BoggleBoard board, Set<String> dictionary) {
    Set<String> foundWords = new HashSet<>();
    boolean[][] visited = new boolean[board.getRowCount()][board.getColCount()];
```

1. **Iterate Through Words**:
    - The algorithm iterates over all words in the dictionary.

2. **Start DFS From Each Cell**:
   ```java
   for (int i = 0; i < board.getRowCount(); i++) {
       for (int j = 0; j < board.getColCount(); j++) {
           if (dfs(board, visited, word, i, j, 0)) {
               foundWords.add(word);
           }
       }
   }
   ```
    - For each word, it starts a DFS search from every cell `(i, j)` in the grid.

3. **Add Matching Words**:
    - If `dfs` confirms the word is found, it adds the word to the `foundWords` set.

---

### **Why This Works for Boggle**

- **Recursive Exploration**:
    - Boggle requires exploring all possible character sequences in the grid, and DFS is well-suited for this task.
    - It efficiently handles branching paths and backtracking when a path doesn't lead to a valid solution.

- **Direction Management**:
    - The `Direction` enum encapsulates movement logic, keeping the code clean and easy to extend if needed.

- **Visited Array**:
    - Ensures no cell is reused in the same path, adhering to Boggle rules.

---

### **Example Walkthrough**

#### Board:
```
G  I  Z
U  E  K
Q  S  E
```

#### Word: `"GEEK"`

1. Start at `(0,0)` (`G`):
    - Matches the first letter.
    - Mark `(0,0)` as visited.

2. Explore Neighbors:
    - Move to `(0,1)` (`I`): Doesn't match.
    - Move to `(1,0)` (`U`): Doesn't match.
    - Move to `(1,1)` (`E`): Matches the second letter.
        - Mark `(1,1)` as visited.

3. Continue to Match `E`:
    - From `(1,1)`, move to `(1,2)` (`K`): Matches.
    - Mark `(1,2)` as visited.

4. Match `K`:
    - From `(1,2)`, explore neighbors. Find `K` completes the word.

5. Word Found:
    - Backtrack, unmark visited cells, and continue searching for other words.

---

### **Complexity Analysis**

1. **Time Complexity**:
    - For each word, DFS explores all paths in the grid.
    - Worst-case: \(O(N \times M \times 8^L)\), where:
        - \(N, M\): Grid dimensions.
        - \(L\): Length of the word.
        - \(8\): Directions.

2. **Space Complexity**:
    - \(O(N \times M)\) for the `visited` array.

This algorithm is efficient and fits well within the constraints of typical Boggle boards. Let me know if you have questions!