class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        // Max-heap prioritizes classes by the potential gain in pass ratio from adding one student.
        PriorityQueue<ClassNode> maxHeap =
            new PriorityQueue<>((a, b) -> Double.compare(b.delta, a.delta));

        // Initialize the heap with current class stats and their incremental gain
        for (int[] cls : classes) {
            int pass = cls[0], total = cls[1];
            maxHeap.offer(new ClassNode(pass, total, extraPassRatio(pass, total)));
        }

        // Distribute extra students greedily
        for (int i = 0; i < extraStudents; i++) {
            ClassNode top = maxHeap.poll();
            top.pass++;
            top.total++;
            top.delta = extraPassRatio(top.pass, top.total);
            maxHeap.offer(top);
        }

        // Compute final average pass ratio
        double sumRatio = 0;
        while (!maxHeap.isEmpty()) {
            ClassNode cn = maxHeap.poll();
            sumRatio += (double) cn.pass / cn.total;
        }
        return sumRatio / classes.length;
    }

    // Compute the marginal gain in pass ratio if one extra student is added
    private double extraPassRatio(int pass, int total) {
        return (pass + 1) / (double) (total + 1) - pass / (double) total;
    }

    // Helper structure for heap entries
    static class ClassNode {
        int pass;
        int total;
        double delta;
        ClassNode(int p, int t, double d) { pass = p; total = t; delta = d; }
    }
}
