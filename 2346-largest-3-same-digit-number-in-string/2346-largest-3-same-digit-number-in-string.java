class Solution {
    public String largestGoodInteger(String num) {
        for( int d=9;d>=0;d--){
            String triple = String.valueOf(d).repeat(3);
            if(num.contains(triple)) {
                return triple;
            }
        }
        return "";
        
    }
}