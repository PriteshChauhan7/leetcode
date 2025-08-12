class Solution {
    public int numberOfWays(int n, int x) {
        final int MOD=1000000007;
        int[] dp = new int[n+1];
        dp[0]=1;
        int a = 1;
        while (true) {
            long ax= (long) Math.pow(a,x);
            if(ax>n) 
            break;
            for (int i=n; i>= ax;i--){
                dp[i] = (dp[i]+ dp[(int)(i-ax)])% MOD;   
             }  
             a++;       
        }
        return dp[n];
    
    }
}