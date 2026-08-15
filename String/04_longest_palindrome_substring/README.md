# Longest Palindromic Substring

## Problem Statement

Given a string `s`, return the **longest substring** of `s` that is a palindrome (reads the same forwards and backwards).

**Examples:**

- `"babad"` → `"bab"` (or `"aba"` — both are valid answers)
- `"cbbd"` → `"bb"`

## Solution Algorithm — Expand Around Center

A palindrome is symmetric around its middle. This solution tries every possible **center** in the string and expands outward as long as the characters on both sides match.

There are `2n - 1` possible centers for a string of length `n`:

- **n centers** for odd-length palindromes (the center is a single character, e.g. `"aba"` centers on `b`).
- **n − 1 centers** for even-length palindromes (the center falls between two characters, e.g. `"bb"` centers between the two `b`s).

**Steps:**

1. Loop over every index `i` in `s` as a potential center.
2. **Odd case:** call `helper(s, i, i)` — start with `l = r = i` and expand outward.
3. **Even case:** call `helper(s, i, i+1)` — start with `l = i`, `r = i+1` and expand outward.
4. `helper` expands `l` left and `r` right one step at a time while `s.charAt(l) == s.charAt(r)`, stopping when the characters mismatch or a boundary is hit. It then returns `s.substring(l+1, r)` — the palindrome found (the `+1` and non-inclusive `r` correct for the one extra step taken past the true boundary).
5. After each center is checked, keep whichever palindrome (`odd` or `even`) is the longest seen so far.

**Example trace — `s = "cbbd"`:**
| i | odd center | even center | longest so far |
|---|-----------|-------------|-----------------|
| 0 | `"c"` | `""` | `"c"` |
| 1 | `"b"` | `"bb"` | `"bb"` |
| 2 | `"b"` | `""` | `"bb"` |
| 3 | `"d"` | `""` | `"bb"` |

Result: `"bb"`.

## Complexity

- **Time:** O(n²) — there are `n` centers, and each expansion can take up to O(n) steps in the worst case (e.g. a string of all identical characters).
- **Space:** O(1) extra space, not counting the output string itself (no auxiliary data structures are used — just pointers).
