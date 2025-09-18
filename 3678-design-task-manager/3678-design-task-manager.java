import java.util.*;

class TaskManager {
    private Map<Integer, int[]> taskMap;  // taskId -> [userId, priority]
    private TreeSet<int[]> taskSet;       // stores [priority, taskId] sorted by priority desc, taskId desc

    public TaskManager(List<List<Integer>> tasks) {
        taskMap = new HashMap<>();
        taskSet = new TreeSet<>((a, b) -> {
            if (a[0] != b[0]) return b[0] - a[0];       // higher priority first
            return b[1] - a[1];                         // if tie, higher taskId first
        });
        for (List<Integer> t : tasks) {
            add(t.get(0), t.get(1), t.get(2));
        }
    }

    public void add(int userId, int taskId, int priority) {
        taskMap.put(taskId, new int[]{userId, priority});
        taskSet.add(new int[]{priority, taskId});
    }

    public void edit(int taskId, int newPriority) {
        int[] info = taskMap.get(taskId);
        int userId = info[0];
        int oldPriority = info[1];
        taskSet.remove(new int[]{oldPriority, taskId});
        taskSet.add(new int[]{newPriority, taskId});
        taskMap.put(taskId, new int[]{userId, newPriority});
    }

    public void rmv(int taskId) {
        int[] info = taskMap.remove(taskId);
        int oldPriority = info[1];
        taskSet.remove(new int[]{oldPriority, taskId});
    }

    public int execTop() {
        if (taskSet.isEmpty()) return -1;
        int[] top = taskSet.pollFirst();  // removes and returns the highest in our ordering
        int taskId = top[1];
        int userId = taskMap.get(taskId)[0];
        taskMap.remove(taskId);
        return userId;
    }
}
