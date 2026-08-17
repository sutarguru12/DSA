# Rotate Image

## Problem Statement

You are given an `n x n` 2D `matrix` representing an image. Rotate the image **by 90 degrees (clockwise)**.

You have to rotate the image **in-place**, which means you have to modify the input 2D matrix directly. **Do not** allocate another 2D matrix and do the rotation.

**Example**

Input:

```
1 2 3
4 5 6
7 8 9
```

Output:

```
7 4 1
8 5 2
9 6 3
```

**Constraints**

- `n == matrix.length == matrix[i].length`
- `1 <= n <= 20`
- `-1000 <= matrix[i][j] <= 1000`

---

## Solution Algorithm

The matrix is rotated **layer by layer**, from the outermost layer moving inward. Each layer is a square "ring" of elements bounded by a `left`/`right` column pair (which also serve as the top/bottom row indices, since the matrix is square).

For each layer:

1. Set `top = left` and `bottom = right` as the row bounds of the current ring.
2. For each offset `i` from `0` to `(right - left - 1)`, perform a **4-way swap** of the elements that belong to the same rotation cycle:
   - top-left → temp
   - bottom-left → top-left
   - bottom-right → bottom-left
   - top-right → bottom-right
   - temp → top-right
3. After processing the full ring, shrink the boundary (`left += 1`, `right -= 1`) and repeat for the next inner layer.
4. Stop when `left >= right`, meaning there are no more layers left to rotate (a single cell or empty layer needs no swapping).

Since the matrix is square, each ring has exactly `right - left` groups of 4 cells to rotate — this is why the inner loop runs `right - left` times rather than `matrix.length` times (using the full matrix length would re-process cells already rotated and corrupt the result).

Each of the 4 swaps per `i` moves an element exactly 90° clockwise to its new position, and doing this in place (using a single temp variable `topLeft`) avoids allocating a second matrix.

---

## Complexity Analysis

**Time Complexity:** `O(n^2)`
Every element in the `n x n` matrix is visited and moved exactly once across all layers and offsets, so the total work is proportional to the number of cells.

**Space Complexity:** `O(1)`
The rotation is performed in-place using only a constant number of extra variables (`left`, `right`, `top`, `bottom`, `topLeft`), regardless of the input size.
