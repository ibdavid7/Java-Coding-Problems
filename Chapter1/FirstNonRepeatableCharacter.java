import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class FirstNonRepeatableCharacter {
    
    public static void main(String[] args) {
        
        String a1 = "aabbccddee";   //null non-repeated
        String a2 = "abcde";        //a 1st nonrepeated
        String a3 = "abcdcbae";     //d 1st nonrepeated
        String a4 = "abccbae";     //e 1st nonrepeated
        String a5 = "💕💕🐱💖🎂🎂💻";
        
        findFirstNonRepeatedCharacter(a1).ifPresentOrElse(System.out::println, () -> System.out.println(Character.MIN_VALUE));
        findFirstNonRepeatedCharacter(a2).ifPresent(System.out::println);
        findFirstNonRepeatedCharacter(a3).ifPresent(System.out::println);
        findFirstNonRepeatedCharacter(a4).ifPresent(System.out::println);
        
        findFirstNonRepeatedCodePoint(a5).ifPresent(System.out::println);

    }
    
    // public static Optional<Character> findFirstNonRepeatedCharacter(String s) {
        
    //     Set<Character> repeatedSet = new HashSet<>();
        
    //     return IntStream.range(0, s.length())
    //         .filter(i -> {
    //             if (i == s.length() - 1) {
    //                 return !repeatedSet.contains(s.charAt(i));
    //             } else {
    //                 if (s.substring(i + 1).indexOf(s.charAt(i)) < 0 &&
    //                 !repeatedSet.contains(s.charAt(i))) {
    //                     return true;
    //                 } else {
    //                     // System.out.println(repeatedSet);
    //                     repeatedSet.add(s.charAt(i));
    //                     return false;
    //                 }
    //             }
            
    //         })
    //         .mapToObj(i -> {
    //             // System.out.println(i);
    //             // System.out.println(repeatedSet);
    //             return s.charAt(i);
    //         })
    //         .findFirst();

    // }
    
    public static Optional<Character> findFirstNonRepeatedCharacter(String s) {
        
        // Map<Character, Long> charMap = new LinkedHashMap<>();
        
        Map<Character, Long> charMap = s.chars()
                        .mapToObj(i -> (char) i)
                        .collect(Collectors.groupingBy(Function.identity(), () -> new LinkedHashMap<Character, Long>(), Collectors.counting()));
        
        return charMap.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() == 1)
                    .map(entry -> entry.getKey())
                    .findFirst();
      

    }
    
    public static Optional<String> findFirstNonRepeatedCodePoint(String s) {
        
        return s.codePoints()
                    .mapToObj(i -> String.valueOf(Character.toChars(i)))
                    .collect(Collectors.groupingBy(Function.identity(),() -> new LinkedHashMap<String, Long>(), Collectors.counting()))
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() == 1L)
                    .map(entry -> entry.getKey())
                    .findFirst();
    }
    
}