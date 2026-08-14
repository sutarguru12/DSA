Steps:

1. For each string, sort its characters → get a key (e.g., "eat" → "aet").
2. Use this key in a HashMap<String, List<String>> to group original strings that share the same key.
3. Return all the groups (map.values()).

Complexity:

Time: O(n · k log k) — n strings, each of length k, sorted individually
Space: O(n · k) — storing all strings
