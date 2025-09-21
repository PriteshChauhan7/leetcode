import java.util.*;

class MovieRentingSystem {
    private Map<Integer, TreeSet<int[]>> availableByMovie; 
    private TreeSet<int[]> rentedSet;
    private Map<String, Integer> priceMap; 

    public MovieRentingSystem(int n, int[][] entries) {
        availableByMovie = new HashMap<>();
        priceMap = new HashMap<>();
        
        for (int[] e : entries) {
            int shop = e[0], movie = e[1], price = e[2];
            priceMap.put(shop + "#" + movie, price);
            availableByMovie.putIfAbsent(movie, new TreeSet<>(
                (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]
            ));
            availableByMovie.get(movie).add(new int[]{price, shop});
        }
        
        rentedSet = new TreeSet<>(
            (a, b) -> a[0] == b[0] ? (a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]) : a[0] - b[0]
        );
    }

    public List<Integer> search(int movie) {
        List<Integer> res = new ArrayList<>();
        if (!availableByMovie.containsKey(movie)) return res;
        int count = 0;
        for (int[] entry : availableByMovie.get(movie)) {
            res.add(entry[1]); // shop
            if (++count == 5) break;
        }
        return res;
    }

    public void rent(int shop, int movie) {
        int price = priceMap.get(shop + "#" + movie);
        availableByMovie.get(movie).remove(new int[]{price, shop});
        rentedSet.add(new int[]{price, shop, movie});
    }

    public void drop(int shop, int movie) {
        int price = priceMap.get(shop + "#" + movie);
        rentedSet.remove(new int[]{price, shop, movie});
        availableByMovie.get(movie).add(new int[]{price, shop});
    }

    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();
        int count = 0;
        for (int[] r : rentedSet) {
            res.add(Arrays.asList(r[1], r[2]));
            if (++count == 5) break;
        }
        return res;
    }
}
