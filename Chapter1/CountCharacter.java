public class CountCharacter {

    public static void main(String[] args) {

        String s1 = "Alibaba1";
        String s2 = "❤❤💕😘😍😊❤❤✌🌹🎂😢💖🐱‍👤🎂❤";

        System.out.println(countCharacter(s1, 'a'));
        System.out.println(countCharacter(s2, '❤'));

    }

    public static long countCharacter(String s, int codePoint) {

        return s.codePoints()
//                .peek(System.out::println)
                .filter(i -> i == codePoint)
                .count();
    }


}
