class Solution {
    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        boolean rowZero = false;

        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                if(matrix[r][c] == 0){
                    matrix[0][c] = 0;
                    if(r > 0) matrix[r][0] = 0;
                    else rowZero = true;
                }
            }
        }

        for(int r = 1; r < row; r++){
            for(int c = 1; c < col; c++){
                if(matrix[r][0] == 0 || matrix[0][c] == 0){
                    matrix[r][c] = 0;
                }
            }
        }

        if(matrix[0][0] == 0){
            for(int i = 0; i < row; i++){
                matrix[i][0] = 0;
            }
        }

        if(rowZero){
            for(int c =  0; c < col; c++){
                matrix[0][c] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };

        solution.setZeroes(matrix);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}