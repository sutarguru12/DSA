import java.util.HashSet;

class Solution {
    private char[][] board;
    private String word;
    private int row, col;
    private HashSet<Integer> path = new HashSet<>();

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        row = board.length;
        col = board[0].length;

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(dfs(i, j, 0)) return true;
            }
        }
        return false;
    }

    private boolean dfs(int r, int c, int i){
        if(i == word.length()) return true;

        if(r < 0 || c < 0|| r >= row || c >= col || 
        board[r][c] != word.charAt(i) || path.contains(r*col + c)) return false;

        path.add(r*col + c);

        boolean found = dfs(r+1, c, i+1) ||
                        dfs(r-1, c, i+1) ||
                        dfs(r, c+1, i+1) ||
                        dfs(r, c-1, i+1);

        path.remove(r*col + c);

        return found;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        String word = "ABCCED";

        boolean result = solution.exist(board, word);
        System.out.println(result);
    }
}