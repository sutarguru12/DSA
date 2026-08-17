# Word Search

## Problem Statement

Given an `m x n` grid of characters `board` and a string `word`, return `true` if `word` exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same cell may **not** be used more than once within a single word.

**Example**

Input:

```
board = [
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED"
```

Output: `true`

**Constraints**

- `m == board.length`
- `n == board[i].length`
- `1 <= m, n <= 6`
- `1 <= word.length <= 15`
- `board` and `word` consist of only lowercase and uppercase English letters.

---

## Solution Algorithm

This is solved with **backtracking (DFS)**, trying to match the word starting from every cell in the grid.

1. **Try every starting cell.** `exist()` loops over every `(i, j)` in the board and calls `dfs(i, j, 0)` — attempting to match the word starting at that cell, from character index `0`.

2. **`dfs(r, c, i)` matches `word.charAt(i)` against `board[r][c]`:**
   - **Base case (success):** if `i == word.length()`, every character has already been matched in sequence, so return `true`.
   - **Base case (failure):** return `false` if the current cell is out of bounds, the character doesn't match `word.charAt(i)`, or the cell has already been used in the current path (checked via the `path` set).

3. **Mark and explore.** If the cell is valid, mark it as visited by adding its encoded position to `path`, then recursively try all 4 directions (down, up, right, left) for the next character `i + 1`. The `||` short-circuits, so as soon as one direction succeeds, the rest aren't explored.

4. **Backtrack.** After exploring all 4 directions, remove the cell from `path` regardless of the outcome. This is essential — it "frees" the cell so it can be reused in a _different_ path (e.g. one that starts from a different origin cell, or takes a different route through this one).

5. **Encoding visited cells.** Since a `HashSet<Integer>` can't store a coordinate pair directly, each cell `(r, c)` is encoded as a single integer `r * col + c`, which is a unique, reversible encoding as long as `c < col` (always true here).

---

## Complexity Analysis

Let `m` and `n` be the number of rows and columns of the board, and `L` be the length of `word`.

**Time Complexity:** `O(m * n * 4^L)`

- `exist()` tries every one of the `m * n` cells as a starting point.
- From each starting point, `dfs` branches into up to 4 directions at each of the `L` characters to match, giving `4^L` possible paths in the worst case.
- In practice this is a loose upper bound — many branches terminate early on a character mismatch or boundary check, and one direction is always the cell just visited (bounded more tightly to 3 effective choices after the first step), but `O(m * n * 4^L)` is the standard worst-case bound for this algorithm.

**Space Complexity:** `O(L)`

- The `path` set holds at most `L` entries at any point (one per cell in the current path being explored).
- The recursion stack also goes at most `L` frames deep.
- (This excludes the input `board` itself, which is not extra space.)
