class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int n = dimensions.length;

        int MaxDiag = 0;
        int MaxArea = 0;

        for(int i = 0; i < n; i++){
            int l = dimensions[i][0];
            int w = dimensions[i][1];

            int diag = l*l + w*w;
            int area = l*w;

            if(diag > MaxDiag){
               MaxDiag = diag;
               MaxArea = area;
            }

            else if(diag == MaxDiag){
                MaxArea = Math.max(MaxArea,area);
            }
        }
        return MaxArea;   
    }
}