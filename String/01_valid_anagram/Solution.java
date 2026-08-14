import java.util.HashMap;

public class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }

        HashMap<Character, Integer> count = new HashMap<>();

        for(int i = 0; i < s.length(); i++){
            count.put(s.charAt(i), count.getOrDefault(s.charAt(i), 0) + 1);
        }

        for(int i = 0; i < t.length(); i++){
            count.put(t.charAt(i), count.getOrDefault(t.charAt(i), 0) - 1);
            if(count.get(t.charAt(i)) < 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String Args[]){
        Solution sol = new Solution();
        String s = "anagram";
        String t = "nagaram";
        System.out.println(sol.isAnagram(s, t));
    }
}

