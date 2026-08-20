# Insert Interval

## Problem Statement

You are given an array of non-overlapping intervals `intervals`, where `intervals[i] = [start_i, end_i]` represents the start and end of the `i`-th interval. The intervals are sorted in ascending order by `start_i`.

You are also given a new interval `newInterval = [start, end]`.

Insert `newInterval` into `intervals` such that the resulting array of intervals is still sorted in ascending order by start time, and no two intervals overlap (merging overlapping intervals if necessary).

Return the resulting array of intervals.

**Note:** You don't need to modify `intervals` in-place. You can make a new array and return it.

### Example 1

```
Input:  intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
```

### Example 2

```
Input:  intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: newInterval = [4,8] overlaps with [3,5],[6,7],[8,10].
```

---

## Algorithm

Since the input `intervals` is already sorted by start time, a single linear pass is enough — no additional sorting is required. The pass is split into three phases:

1. **Before the overlap**
   Add every interval that ends _before_ `newInterval` starts (`intervals[i][1] < newInterval[0]`). These intervals are completely unaffected and are copied as-is into the result.

2. **Merge the overlap**
   While the current interval starts at or before `newInterval` ends (`intervals[i][0] <= newInterval[1]`), it overlaps with `newInterval`. Instead of adding it directly, expand `newInterval` to fully cover it:

   ```
   newInterval[0] = min(newInterval[0], intervals[i][0])
   newInterval[1] = max(newInterval[1], intervals[i][1])
   ```

   This continues until no more intervals overlap. The (possibly expanded) `newInterval` is then added to the result exactly once.

3. **After the overlap**
   Add all remaining intervals unchanged, since they start after `newInterval` ends and cannot overlap with it.

### Why this works

Because the intervals are pre-sorted and non-overlapping, once an interval is found that no longer overlaps with the (growing) `newInterval`, no later interval can overlap with it either. This guarantees the three-phase scan never needs to backtrack.

---

## Complexity

| Metric    | Complexity | Reason                                                                                                       |
| --------- | ---------- | ------------------------------------------------------------------------------------------------------------ |
| **Time**  | `O(n)`     | Single linear pass through `intervals`; each interval is visited exactly once across the three while-loops.  |
| **Space** | `O(n)`     | The `res` list (and the returned array) holds up to `n + 1` intervals in the worst case (no merging occurs). |

No sorting step is needed here (unlike the general "Merge Intervals" problem), since the input is guaranteed sorted — this is what keeps the algorithm at `O(n)` instead of `O(n log n)`.
