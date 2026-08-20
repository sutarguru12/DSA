import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        ArrayList<int[]> ans = new ArrayList<>();

        for(int i = 0; i < n; i++){
            if(ans.isEmpty() || intervals[i][0] > ans.get(ans.size()-1)[1]){
                ans.add(new int[]{intervals[i][0], intervals[i][1]});
            }
            else{
                ans.get(ans.size()-1)[1] = Math.max(ans.get(ans.size()-1)[1], intervals[i][1]);
            }
        }
        return ans.toArray(new int[ans.size()][]);

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result = solution.merge(intervals);
        System.out.println(Arrays.deepToString(result)); // Output: [[1, 6], [8, 10], [15, 18]]
    }
}