class longest_palindrome {
    public String longestPalindrome(String s) {
        String res = "";

        for(int i = 0; i <s.length(); i++){
            String odd = helper(s, i, i);
            if(odd.length() >= res.length()) res = odd;

            String even = helper(s, i, i+1);
            if(even.length() >= res.length()) res = even;
        }
        return res;
    }
private String helper(String s, int l, int r){
    while(l >= 0 && r <s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }
        return s.substring(l+1, r);
    }

    public static void main(String[] args){
        longest_palindrome sol = new longest_palindrome();
        System.out.println(sol.longestPalindrome("babad"));
    }
}
