# Palindrome Number

## Problem Statement

Given an integer `x`, return `true` if `x` is a palindrome (reads the same forwards and backwards), and `false` otherwise.

**Constraint / twist:** solve it **without converting the integer to a string**.

**Examples:**

- `121` → `true`
- `-121` → `false` (the `-` sign breaks symmetry)
- `10` → `false` (reversed, `10` becomes `01`, which isn't a valid mirror)

## Solution Algorithm

Instead of reversing the _entire_ number (which risks integer overflow) or converting to a string, this solution reverses only **half** of the number and compares the two halves.

1. **Early rejection:**
   - Negative numbers can never be palindromes → `false`.
   - Any positive number ending in `0` (except `0` itself) can't be a palindrome, since the leading digit can't be `0` → `false`.

2. **Reverse the second half:**
   - Repeatedly peel off the last digit of `x` (`x % 10`) and build it into `reverted`.
   - Stop once `x <= reverted` — at this point, roughly half the digits have moved from `x` into `reverted`.

3. **Compare:**
   - **Even number of digits:** `x == reverted` (both halves match exactly).
   - **Odd number of digits:** `x == reverted / 10` (drop the middle digit of `reverted`, which doesn't need to match anything).

**Example trace — `x = 121`:**
| Step | x | reverted |
|------|-----|----------|
| start | 121 | 0 |
| 1 | 12 | 1 |
| 2 | 1 | 12 |

Loop stops (`x <= reverted`). Odd-length case: `x (1) == reverted / 10 (1)` → `true`.

## Complexity

- **Time:** O(log₁₀ x) — the loop runs roughly once per digit of `x`.
- **Space:** O(1) — only a few integer variables are used, no string conversion or extra data structures.
