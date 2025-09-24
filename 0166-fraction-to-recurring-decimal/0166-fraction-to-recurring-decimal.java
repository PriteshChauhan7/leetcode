import java.util.*;

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        
        StringBuilder sb = new StringBuilder();
        
        // Step 1: Handle sign
        if ((numerator < 0) ^ (denominator < 0)) {
            sb.append("-");
        }
        
        // Convert to long to avoid overflow
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        
        // Step 2: Integer part
        sb.append(num / den);
        long remainder = num % den;
        if (remainder == 0) return sb.toString(); // no fraction
        
        sb.append(".");
        
        // Step 3: Fractional part
        Map<Long, Integer> seen = new HashMap<>();
        
        while (remainder != 0) {
            if (seen.containsKey(remainder)) {
                sb.insert(seen.get(remainder), "(");
                sb.append(")");
                break;
            }
            seen.put(remainder, sb.length());
            remainder *= 10;
            sb.append(remainder / den);
            remainder %= den;
        }
        
        return sb.toString();
    }
}
