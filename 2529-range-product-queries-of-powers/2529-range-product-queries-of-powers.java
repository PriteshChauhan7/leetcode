class Solution {
    public int[] productQueries(int n, int[][] queries) {
        int m = queries.length;
        int mod = 1000000007;
        int[] ans = new int[m];
        List<Integer> powers = new ArrayList<>();
        int temp = n;
        int two = 1;
        while(temp>0){
            if(temp%2!=0){
                powers.add(two);
            }
            two <<=1;
            temp>>=1;
        }
        for(int i=0;i<m;i++){
            int currAns =1;
            int start = queries[i][0];
            int end = queries[i][1];
            for(int j=start;j<=end;j++){
                currAns = (int)(((long)currAns * (long)powers.get(j))%(long)mod);
            }
            ans[i]=currAns;
        }
        return ans;
    }
}
