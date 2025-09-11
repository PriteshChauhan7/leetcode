class Solution {
    public String sortVowels(String s) {
        List<Character> vowels = new ArrayList<>();
        char[] arr = s.toCharArray();
        
        for (char c : arr) {
            if (isVowel(c)) {
                vowels.add(c);
            }
        }
        
        Collections.sort(vowels);
        int vIndex = 0;
        StringBuilder sb = new StringBuilder();
        
        for (char c : arr) {
            if (isVowel(c)) {
                sb.append(vowels.get(vIndex++));
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
    
    private boolean isVowel(char c) {
        char x = Character.toLowerCase(c);
        return x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u';
    }
}
