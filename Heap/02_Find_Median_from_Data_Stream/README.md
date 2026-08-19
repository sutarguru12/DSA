# Find Median from Data Stream

## Problem Statement

Design a data structure that supports the following two operations on a stream of numbers:

- `addNum(int num)` — Adds an integer number from the data stream to the data structure.
- `findMedian()` — Returns the median of all elements added so far.

**Example:**

```
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);
medianFinder.addNum(2);
medianFinder.findMedian(); // returns 1.5
medianFinder.addNum(3);
medianFinder.findMedian(); // returns 2.0
```

## Algorithm

The idea is to split all numbers seen so far into two halves, and use two heaps to keep track of them:

- **`lowerHalf`** — a **max-heap** holding the smaller half of the numbers (largest of these on top).
- **`upperHalf`** — a **min-heap** holding the larger half of the numbers (smallest of these on top).

The heaps are kept balanced in size (differing by at most 1), with `lowerHalf` allowed one extra element when the total count is odd. This way, the median is always available at the top of one or both heaps — no sorting needed.

### `addNum(num)`

1. Push `num` into `lowerHalf` first.
2. Move the largest element of `lowerHalf` into `upperHalf` (`upperHalf.offer(lowerHalf.poll())`). This guarantees every number in `lowerHalf` is ≤ every number in `upperHalf`.
3. If this makes `upperHalf` bigger than `lowerHalf`, move the smallest element of `upperHalf` back into `lowerHalf` to restore balance.

This "add then shuffle" pattern automatically places `num` in the correct half without needing to compare it to the current median directly.

### `findMedian()`

- If `lowerHalf` has one more element than `upperHalf` (odd total count), the median is the top of `lowerHalf`.
- Otherwise (even total count), the median is the average of the tops of both heaps.

## Complexities

| Operation    | Time       | Explanation                                                                           |
| ------------ | ---------- | ------------------------------------------------------------------------------------- |
| `addNum`     | `O(log n)` | Each call does a constant number of heap insertions/removals, each costing `O(log n)` |
| `findMedian` | `O(1)`     | Just peeking at the top of one or both heaps                                          |
| Space        | `O(n)`     | Both heaps together store every number added so far                                   |
