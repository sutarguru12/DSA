# Merge Intervals

## Problem Statement

Given an array of intervals where `intervals[i] = [start_i, end_i]`, merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

Unlike "Insert Interval," the input here is **not** guaranteed to be sorted or non-overlapping — it can be in any order, with any amount of overlap.

### Example 1

```
Input:  intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: [1,3] and [2,6] overlap, so they merge into [1,6].
```

### Example 2

```
Input:  intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping since they touch at 4.
```

---

## Algorithm

1. **Sort by start time**
   `Arrays.sort(intervals, (a, b) -> a[0] - b[0])` sorts intervals in ascending order of their start values. This is the key step that makes a single linear pass sufficient afterward — once sorted, any interval that could overlap with the current merged interval must appear immediately next in the sequence.

2. **Single pass, compare against the last merged interval**
   Walk through the sorted intervals one at a time, always comparing against the _last interval added_ to the result list (`ans`):
   - **No overlap:** if the current interval's start is strictly greater than the last merged interval's end (`intervals[i][0] > ans.get(ans.size()-1)[1]`), there's no overlap — push the current interval as a brand-new entry in `ans`.
   - **Overlap:** otherwise, the current interval overlaps (or touches) the last one — merge them by extending the last interval's end:
     ```
     ans.get(ans.size()-1)[1] = Math.max(ans.get(ans.size()-1)[1], intervals[i][1])
     ```

3. **Why `Math.max` matters**
   Consider `[[1,10],[2,3]]`. If you overwrote the end directly with `intervals[i][1]` (3) instead of taking the max, `[1,10]` would incorrectly shrink to `[1,3]`, losing data from a shorter interval nested inside a longer one. `Math.max` guards against this.

4. **Return the result**
   Convert `ans` (an `ArrayList<int[]>`) into a plain `int[][]` via `ans.toArray(new int[ans.size()][])`.

---

## Complexity

| Metric    | Complexity   | Reason                                                                                                                                                                                                                                                                                                            |
| --------- | ------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Time**  | `O(n log n)` | Dominated by the sort step; the subsequent linear pass is `O(n)`.                                                                                                                                                                                                                                                 |
| **Space** | `O(n)`       | For the `ans` list / output array. (Sorting itself may use `O(log n)` to `O(n)` auxiliary space depending on the JVM's sort implementation, typically a dual-pivot quicksort for primitives or Timsort for objects — here `int[][]` uses a comparator, so it's Timsort, which is `O(n)` space in the worst case.) |

### Comparison with Insert Interval

This problem requires sorting first (`O(n log n)`) because the input can arrive in any order. "Insert Interval" achieves `O(n)` overall because its input is _already_ sorted and non-overlapping — that guarantee is what removes the need for a sort step there.
