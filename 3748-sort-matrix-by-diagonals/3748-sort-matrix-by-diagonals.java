import java.util.*;

class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;

        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                groups.computeIfAbsent(i - j, k -> new ArrayList<>()).add(grid[i][j]);
            }
        }

        
        Map<Integer, Deque<Integer>> sorted = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> e : groups.entrySet()) {
            int key = e.getKey();
            List<Integer> vals = e.getValue();
            if (key >= 0) {
                
                vals.sort(Collections.reverseOrder());
            } else {
                
                Collections.sort(vals);
            }
            sorted.put(key, new ArrayDeque<>(vals));
        }

        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sorted.get(i - j).removeFirst();
            }
        }
        return grid;
    }
}
