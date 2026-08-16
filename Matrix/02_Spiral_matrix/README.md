# Spiral Matrix

## Problem Statement

Given an `m x n` matrix, return **all elements of the matrix in spiral
order** — starting at the top-left corner, moving right across the top row,
then down the right column, then left across the bottom row, then up the
left column, and repeating this inward spiral until every element has been
visited.

**Example**

Input:

```
1  2  3
4  5  6
7  8  9
```

Output: `[1, 2, 3, 6, 9, 8, 7, 4, 5]`

## Algorithm

The approach uses **four shrinking boundaries** — `left`, `right`, `top`,
`bottom` — that define the still-unvisited sub-rectangle of the matrix.
`right` and `bottom` are kept as _exclusive_ bounds (one past the last
valid index), which is why the loops use `<` instead of `<=`.

Each iteration of the outer `while` loop peels off one full "ring" of the
spiral in four passes:

1. **Top row, left → right**
   Walk `matrix[top][i]` for `i` from `left` to `right - 1`.
   Then shrink the top boundary: `top += 1`.

2. **Right column, top → bottom**
   Walk `matrix[i][right - 1]` for `i` from the new `top` to `bottom - 1`.
   Then shrink the right boundary: `right -= 1`.

3. **Boundary check (guards against re-visiting cells)**
   After the first two passes, check whether a valid rectangle still
   remains: `if (!(left < right && top < bottom)) break;`
   This is essential for **single-row or single-column** matrices — without
   it, the bottom-row and left-column passes below would re-add elements
   already emitted by the top-row/right-column passes.

4. **Bottom row, right → left**
   Walk `matrix[bottom - 1][i]` for `i` from `right - 1` down to `left`.
   Then shrink the bottom boundary: `bottom -= 1`.

5. **Left column, bottom → top**
   Walk `matrix[i][left]` for `i` from `bottom - 1` down to the (possibly
   updated) `top`.
   Then shrink the left boundary: `left += 1`.

The `while (left < right && top < bottom)` condition at the top of the loop
ensures the process stops as soon as the boundaries cross — i.e., once the
entire matrix has been consumed.

### Walkthrough on the example

Starting boundaries: `left=0, right=3, top=0, bottom=3`

- Top row: `1, 2, 3` → `top=1`
- Right col: `6, 9` → `right=2`
- Check: `left(0) < right(2)` and `top(1) < bottom(3)` → continue
- Bottom row: `8, 7` → `bottom=2`
- Left col: `4` → `left=1`
- Loop check: `left(1) < right(2)` and `top(1) < bottom(2)` → continue
- Top row: `5` → `top=2`
- Right col: nothing (`top=2` to `bottom=2` is empty) → `right=1`
- Check: `left(1) < right(1)` is false → break

Result: `[1, 2, 3, 6, 9, 8, 7, 4, 5]` ✓

## Complexity Analysis

Let `m` = number of rows, `n` = number of columns.

- **Time Complexity:** `O(m * n)`
  Every element of the matrix is visited and added to the result exactly
  once across all four passes combined.

- **Space Complexity:** `O(1)` extra space
  Only a handful of integer boundary variables are used; the output list
  itself (`O(m * n)`) is not counted as extra space since it's the required
  result.
