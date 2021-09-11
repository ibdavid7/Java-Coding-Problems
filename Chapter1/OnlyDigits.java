import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.regex.Pattern;




class OnlyDigits {
    
    public static void main(String[] args) {
        
        String a1 = "John Thomas 1";
        String a2 = "aaabbbccc";
        String a3 = "1235";
        String a4 = "123 123 ";
        String a5 = "💕💕💖 🎂🎂💻";
        
        System.out.println(checkOnlyDigits(a1));
        System.out.println(checkOnlyDigits(a2));
        System.out.println(checkOnlyDigits(a3));
        System.out.println(checkOnlyDigits(a4));
        System.out.println(checkOnlyDigits(a5));
        
        
        System.out.println(checkOnlyDigits1(a1));
        System.out.println(checkOnlyDigits1(a2));
        System.out.println(checkOnlyDigits1(a3));
        System.out.println(checkOnlyDigits1(a4));
        System.out.println(checkOnlyDigits1(a5));
        
        System.out.println(checkOnlyDigits2(a1));
        System.out.println(checkOnlyDigits2(a2));
        System.out.println(checkOnlyDigits2(a3));
        System.out.println(checkOnlyDigits2(a4));
        System.out.println(checkOnlyDigits2(a5));

    }
    

    public static boolean checkOnlyDigits(String s) {
    
    Pattern pattern = Pattern.compile("[0-9]");
    
    return s.chars()
            .allMatch(i -> pattern.matcher("" + (char) i).matches());

    }
    
    public static boolean checkOnlyDigits1(String s) {
    
        return s.matches("[0-9]+");

    }
    
    public static boolean checkOnlyDigits2(String s) {

    
    return s.chars()
            .allMatch(i -> Character.isDigit(i));

    }
    
}