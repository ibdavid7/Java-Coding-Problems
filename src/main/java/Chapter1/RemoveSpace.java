public class RemoveSpace {
    public static void main(String[] args) {
        String s1 = "  aa  bb  d $ lšš šš";
        System.out.println(removeSpaces(s1));
    }

    public static String removeSpaces(String s) {
        return s.replaceAll("\\s", "");
    }
}
