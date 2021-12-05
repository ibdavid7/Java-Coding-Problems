package Mathematics;

public class BinaryConversion {

    public static void main(String[] args) {
        int i = 25000;
        long j = convertDecToBinary(i);
        System.out.println(j);
        System.out.println(j + 99);
    }

    public static long convertDecToBinary(int i) {

        if (i == 0) {
            return i;
        } else {

            int q = i;
            int r = i;
            StringBuilder sb = new StringBuilder();

            while (q > 0) {

                r = q % 2;
                sb.append(r);
                q = q >> 1;
//                q = q / 2;

            }

            sb.reverse();
            System.out.println(sb);
            return Long.parseLong(sb.toString());

        }

    }

}
