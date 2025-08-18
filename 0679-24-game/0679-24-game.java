import java.util.*;

class Solution {
    public boolean judgePoint24(int[] nums) {
        List<Double> doubleNums = new ArrayList<>();
        for (int num : nums) {
            doubleNums.add((double) num);
        }
        return dfs(doubleNums);
    }

    private boolean dfs(List<Double> nums) {
        if (nums.size() == 1) {
            return Math.abs(nums.get(0) - 24.0) < 0.001;
        }

        int n = nums.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                List<Double> next;
                for (double val : generate(nums.get(i), nums.get(j))) {
                    next = new ArrayList<>();
                    next.add(val);
                    for (int k = 0; k < n; k++) {
                        if (k != i && k != j) {
                            next.add(nums.get(k));
                        }
                    }
                    if (dfs(next)) return true;
                }
            }
        }
        return false;
    }

    private List<Double> generate(double a, double b) {
        List<Double> list = new ArrayList<>();
        list.add(a + b);
        list.add(a - b);
        list.add(b - a);
        list.add(a * b);
        if (b != 0) list.add(a / b);
        if (a != 0) list.add(b / a);
        return list;
    }
}
