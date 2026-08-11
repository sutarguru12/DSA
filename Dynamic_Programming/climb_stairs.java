class climb_stairs {
    public int climbStairs(int n) {
        int one = 1;
        int two = 1;

        for(int i = 0; i < n-1; i++){
            int temp = one;
            one = one + two;
            two = temp;
        }
        return one;
    }

    public static void main(String[] Args){
        int n = 5;
        System.out.println(new climb_stairs().climbStairs(n));
    }
}