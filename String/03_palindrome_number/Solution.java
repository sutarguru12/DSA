class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0 || (x%10 == 0 && x > 0)){
            return false;
        }
        int reverted = 0;
        while(x>reverted){
            reverted = reverted * 10 + x % 10;
            x = x / 10;
        }

        return x == reverted || x == reverted/10;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        System.out.println(sol.isPalindrome(121));
    }
}