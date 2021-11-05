import java.util.*;
import java.util.stream.*;
import java.util.regex.Pattern;




class ReverseLettersWords {
    
    public static void main(String[] args) {
        
        String a1 = "John Thomas";   //null non-repeated
        String a2 = "aaabbbccc";        //a 1st nonrepeated
        String a3 = "Text messaging, or texting, is the act of composing and sending electronic messages, typically consisting of alphabetic and numeric characters, between two or more users of mobile devices, desktops/laptops, or another type of compatible computer.";     //d 1st nonrepeated
        String a4 = "TodaY I AM GoIng to eat apPlEs";     //e 1st nonrepeated
        String a5 = "💕💕💖 🎂🎂💻";
        
        System.out.println(reverseWords(a1));
        System.out.println(reverseWords(a2));
        System.out.println(reverseWords(a3));
        System.out.println(reverseWords(a4));
        System.out.println(reverseWords(a5));
        
        
        System.out.println(reverseWordsFunctional(a1));
        System.out.println(reverseWordsFunctional(a2));
        System.out.println(reverseWordsFunctional(a3));
        System.out.println(reverseWordsFunctional(a4));
        System.out.println(reverseWordsFunctional(a5));

    }
    

    public static String reverseLetters(String s) {
    
    return s.codePoints()
                .mapToObj(i -> String.valueOf(Character.toChars(i)))
                .reduce("", (acc, cur) -> cur + acc, (s1, s2) -> s1 + s2);

    }
    
    public static String reverseWords(String s) {
    
        return Arrays.stream(s.split("\b"))
                    .map(ReverseLettersWords::reverseLetters)
                    .reduce("", (acc, cur) -> cur + "\b" + acc, (s1, s2) -> s1 + s2);

    }
    
    public static String reverseWordsFunctional(String s) {
    
        Pattern pattern = Pattern.compile("\b+");
        
        return pattern.splitAsStream(s)
                    .map(w -> new StringBuilder(w).reverse())
                    .collect(Collectors.joining("\b"));

    }
    
}