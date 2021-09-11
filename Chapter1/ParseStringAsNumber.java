public class ParseStringAsNumber {

    public static void main(String[] args) {

        String i = "1234";
        String f = "122.5f";
        String l = "12345";
        String d = "123.45d";


        try {
            double res = Integer.parseInt(i) + Float.parseFloat(f) + Long.parseLong(l) + Double.parseDouble(d);
            System.out.println(res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
