# Set Matrix Zeroes

## Problem Statement

Given an `m x n` integer matrix, if an element is `0`, set its entire row and
entire column to `0`, and do it **in place**.

**Example**

Input:

```
1 1 1
1 0 1
1 1 1
```

Output:

```
1 0 1
0 0 0
1 0 1
```

**Constraints / Goal**

- Modify the matrix in place.
- Aim for `O(1)` extra space (beyond the input matrix itself), rather than
  using an auxiliary set/array to remember which rows and columns to zero.

## Algorithm

The key idea is to reuse the **first row** and **first column** of the
matrix itself as marker arrays, instead of allocating extra space to record
which rows/columns must become zero. Since the first row and first column
overlap at `matrix[0][0]`, a separate flag is used to track whether the
first row itself needs to be zeroed.

Steps:

1. **Scan the matrix and mark rows/columns**
   Iterate over every cell `matrix[r][c]`. If a cell is `0`:
   - Mark `matrix[0][c] = 0` → records "column `c` must be zeroed".
   - If `r > 0`, mark `matrix[r][0] = 0` → records "row `r` must be zeroed".
   - If `r == 0`, we can't use `matrix[0][0]` alone to encode this (it's
     shared with the column marker), so a separate boolean `rowZero` is set
     to `true` to remember that row 0 itself contains a zero.

2. **Zero out interior cells using the markers**
   For every cell `matrix[r][c]` with `r >= 1` and `c >= 1`, set it to `0`
   if either its row marker (`matrix[r][0]`) or its column marker
   (`matrix[0][c]`) is `0`. This is done _after_ step 1 completes, so the
   markers aren't corrupted mid-scan.

3. **Handle column 0**
   Check `matrix[0][0]`. If it's `0`, it means column 0 originally
   contained a zero (as detected in step 1), so the entire first column is
   zeroed out.

4. **Handle row 0**
   If `rowZero` is `true`, the entire first row is zeroed out last (it must
   be done last since row 0 was used as the column-marker array throughout
   steps 1–3).

## Why This Works

- The first row and first column double as storage for "does this
  row/column need to be zeroed?" — avoiding any extra `O(m)` or `O(n)`
  arrays.
- `matrix[0][0]` is ambiguous (it belongs to both row 0 and column 0), so
  the `rowZero` boolean cleanly resolves that ambiguity for row 0, while
  `matrix[0][0]` itself is repurposed to represent column 0's state.
- Processing order matters: the interior is zeroed out (step 2) **before**
  column 0 and row 0 are finally overwritten (steps 3–4), ensuring the
  marker values in `matrix[r][0]` and `matrix[0][c]` are still intact when
  they're read.

## Complexity Analysis

Let `m` = number of rows, `n` = number of columns.

- **Time Complexity:** `O(m * n)`
  The matrix is traversed a constant number of times (once to mark, once to
  zero the interior, once each for column 0 and row 0), each pass visiting
  at most `m * n` cells.

- **Space Complexity:** `O(1)`
  No auxiliary data structures proportional to the input size are used —
  only a single boolean (`rowZero`) and a few integer variables. The matrix
  itself is reused as the marker storage.
