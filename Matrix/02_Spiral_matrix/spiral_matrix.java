import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();

        int left = 0;
        int right = matrix[0].length;
        int top = 0;
        int bottom = matrix.length;

        while(left < right && top < bottom){
            for(int i = left; i < right; i++){
                res.add(matrix[top][i]);
            }
            top += 1;

            for(int i = top; i < bottom; i++){
                res.add(matrix[i][right-1]);
            }
            right -= 1;

            if(!(left < right && top < bottom)) break;

            for(int i = right -1; i >= left; i--){
                res.add(matrix[bottom -1][i]);
            }
            bottom -= 1;

            for(int i = bottom -1; i >= top; i--){
                res.add(matrix[i][left]);
            }
            left += 1;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        List<Integer> result = solution.spiralOrder(matrix);
        System.out.println(result);
    }
}