class Solution {
    public int findClosest(int x, int y, int z) {
       if((x - z) * (x - z) == (y - z) * (y - z)){
        return 0;
       }else if((x - z) * (x - z) > (y - z) * (y - z)){
        return 2;
       }else{
        return 1;
       }
    }
}
