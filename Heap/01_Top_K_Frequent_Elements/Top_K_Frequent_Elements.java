import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0)+1);
        }

        Integer []keys = freqMap.keySet().toArray(new Integer[0]);

        //compare the frequencies
        PriorityQueue<Integer> qu = new PriorityQueue<>((a, b) -> freqMap.get(a) - freqMap.get(b));

        for(int i = 0; i < keys.length; i++){
            qu.offer(keys[i]);
            if(qu.size() > k){
                qu.poll();
            }
        }

        int idx = 0;
        int []res = new int[k];

        while(!qu.isEmpty()){
            res[idx++] = qu.poll();
        }

        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result = sol.topKFrequent(nums, k);
        System.out.println(Arrays.toString(result)); // Output: [1, 2]
    }
}