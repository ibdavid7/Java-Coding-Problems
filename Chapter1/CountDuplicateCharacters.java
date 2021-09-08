import java.util.*;
import java.util.stream.*;


class CountDuplicateCharacters {
    
    public static void main(String[] args) {
        
        String a1 = "aabbccddee";   //4 repeating
        String a2 = "abcde";        //0 repeating
        
        System.out.println(countDuplicateCharacters(a1));
        System.out.println(countDuplicateCharacters(a2));
        
        
    }
    
    public static long countDuplicateCharacters(String s) {
        
        // Map<Character, Integer> map = new HashMap<>();
        
        // s.chars()
        //     .forEach(ch -> {
                
        //         map.compute(ch, (k, v) -> {
        //             return (v == null) ? 1 : ++v;
        //         })

        //     });
        
        Map<Character, Long> map = new HashMap<>();
            
        map = s.chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
            
        // System.out.println(map);
        
        return map.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .count();
        
    }
    
}