# Top K Frequent Elements

## Problem Statement

Given an integer array `nums` and an integer `k`, return the `k` most frequent elements in the array. The answer can be returned in any order.

**Example:**

```
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
```

## Algorithm

1. **Count frequencies** — Use a `HashMap<Integer, Integer>` to count how many times each number appears in `nums`.

2. **Create a min-heap ordered by frequency** — Build a `PriorityQueue<Integer>` with a custom comparator so that, instead of comparing numbers by their value, it compares them by their frequency:

   ```java
   PriorityQueue<Integer> qu = new PriorityQueue<>((a, b) -> freqMap.get(a) - freqMap.get(b));
   ```

   This keeps the number with the **smallest frequency** on top of the heap.

3. **Keep the heap size limited to k** — Go through every unique number:
   - Add it to the heap (`offer`).
   - If the heap size exceeds `k`, remove the top element (`poll`) — this is always the least frequent number currently in the heap.

   After processing all numbers, the heap contains exactly the `k` most frequent elements.

4. **Extract the result** — Drain the heap into the result array using `poll()`.

## Complexities

| Metric | Complexity   | Explanation                                                                                                                |
| ------ | ------------ | -------------------------------------------------------------------------------------------------------------------------- |
| Time   | `O(n log k)` | There are `n` unique numbers to process, and each heap insert/remove takes `O(log k)` since the heap is capped at size `k` |
| Space  | `O(n + k)`   | `O(n)` for the frequency map, `O(k)` for the heap                                                                          |
