class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        Set<Integer> need = new HashSet<>();
        for (int[] f : friendships) {
            int u = f[0] - 1, v = f[1] - 1;
            if (!canCommunicate(languages[u], languages[v])) {
                need.add(u);
                need.add(v);
            }
        }
        if (need.isEmpty()) return 0;

        int[] freq = new int[n + 1];
        for (int u : need)
            for (int lang : languages[u])
                freq[lang]++;

        int max = 0;
        for (int f : freq) max = Math.max(max, f);
        return need.size() - max;
    }

    private boolean canCommunicate(int[] a, int[] b) {
        Set<Integer> set = new HashSet<>();
        for (int x : a) set.add(x);
        for (int y : b) if (set.contains(y)) return true;
        return false;
    }
}
