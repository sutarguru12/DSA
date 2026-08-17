class Solution {
    public void rotate(int[][] matrix) {
        int left = 0;
        int right = matrix[0].length -1;

        while(left < right){
             for(int i = 0; i < right - left; i++){
                int top = left;
                int bottom = right;
                int topLeft = matrix[top][left + i];

                matrix[top][left + i] =  matrix[bottom - i][left];

                matrix[bottom - i][left] = matrix[bottom][right - i];

                matrix[bottom][right - i] = matrix[top + i][right];

                matrix[top + i][right] = topLeft;
             }
             right -= 1;
             left += 1;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        solution.rotate(matrix);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}