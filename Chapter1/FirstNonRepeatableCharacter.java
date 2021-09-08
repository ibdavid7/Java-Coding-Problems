import java.util.*;
import java.util.stream.*;

class FirstNonRepeatableCharacter {
    
    public static void main(String[] args) {
        
        String a1 = "aabbccddee";   //null non-repeated
        String a2 = "abcde";        //a 1st nonrepeated
        String a3 = "abcdcbae";     //d 1st nonrepeated
        String a4 = "abccbae";     //d 1st nonrepeated
        
        findFirstNonRepeatedCharacter(a1).ifPresent(System.out::println);
        findFirstNonRepeatedCharacter(a2).ifPresent(System.out::println);
        findFirstNonRepeatedCharacter(a3).ifPresent(System.out::println);
        findFirstNonRepeatedCharacter(a4).ifPresent(System.out::println);

    }
    
    public static Optional<Character> findFirstNonRepeatedCharacter(String s) {
        
        Set<Character> repeatedSet = new HashSet<>();
        
        return IntStream.range(0, s.length())
            .filter(i -> {
                if (i == s.length() - 1) {
                    return !repeatedSet.contains(s.charAt(i));
                } else {
                    if (s.substring(i + 1).indexOf(s.charAt(i)) < 0 &&
                    !repeatedSet.contains(s.charAt(i))) {
                        return true;
                    } else {
                        // System.out.println(repeatedSet);
                        repeatedSet.add(s.charAt(i));
                        return false;
                    }
                }
            
            })
            .mapToObj(i -> {
                // System.out.println(i);
                // System.out.println(repeatedSet);
                return s.charAt(i);
            })
            .findFirst();

    }
    
}