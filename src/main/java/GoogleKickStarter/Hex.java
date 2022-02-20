package GoogleKickStarter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Starter code for the Kick Start 2022 problem Hex.
 */
public class Hex {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Read the number of test cases.
        int t = scanner.nextInt();
        for (int caseIndex = 1; caseIndex <= t; caseIndex++) {
            // Read the board size.
            int n = scanner.nextInt();
            // Read each row of the board.
            char[][] board = new char[n][];
            for (int i = 0; i < n; i++) {
                board[i] = scanner.next().toCharArray();
            }
            // Determine the game status and display it.


            String status = determineStatus(board);
            System.out.println("Case #" + caseIndex + ": " + status);


        }
    }

    /**
     * Returns a status string as specified by the Hex problem statement.
     */
    static String determineStatus(char[][] board) {
        // TODO(you): Implement this method to solve the problem!

        // Initilized padded_board
        int n = board.length + 2;
        char[][] padded_board = new char[n][n];


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                padded_board[i][j] = (j == 0) || (j == n - 1) ? 'B' : (i == 0 || i == n - 1) ? 'R' : board[i - 1][j - 1];
            }

        }

        int red_count = 0;
        int blues_count = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                red_count = board[i][j] == 'R' ? red_count + 1 : red_count;
                blues_count = board[i][j] == 'B' ? blues_count + 1 : blues_count;
            }
        }

        if (Math.abs(red_count - blues_count) > 1) {
            return "Impossible";
        }
        ;

        Set<Pair> blue_path_south = bluePathSouth(padded_board, n);
        if (blue_path_south != null) {
            Set<Pair> blue_path_north = bluePathNorth(padded_board, n);
            Set<Pair> blue_intersection = new HashSet<>(blue_path_south);
            blue_intersection.retainAll(blue_path_north);
            if (blue_intersection.size() > 0 && blues_count >= red_count) {
                return "Blue wins";
            } else {
                return "Impossible";
            }
        }

        Set<Pair> red_path_west = redPathWest(padded_board, n);
        if (red_path_west != null) {
            Set<Pair> red_path_east = redPathEast(padded_board, n);
            Set<Pair> red_intersection = new HashSet<>(red_path_west);
            red_intersection.retainAll(red_path_east);
            if (red_intersection.size() > 0 && red_count >= blues_count) {
                return "Red wins";
            } else {
                return "Impossible";
            }
        }

        return "Nobody wins";
    }

    static Set<Pair> redPathWest(char[][] board, int n) {
        Pair left = new Pair(n - 1, 0);
        Pair right = new Pair(n - 1, 1);

        Set<Pair> path_set = new HashSet<>();

//        System.out.println("Start pair:" + left + "; " + right);

        while (right.row > 0) {   //Break if we reach North wall
            path_set.add(right);
            Pair[] arr = nextStepStayLeft(board, 'R', left, right);
            left = arr[0];
            right = arr[1];
//            System.out.println("New pair:" + left + "; " + right);
            if (left.col == n - 1) {
                return null;
            }
        }

        return path_set;

    }

    static Set<Pair> redPathEast(char[][] board, int n) {
        Pair left = new Pair(n - 1, n - 2);
        Pair right = new Pair(n - 1, n - 1);

        Set<Pair> path_set = new HashSet<>();

        while (left.row > 0) {   //Break if we reach North wall
            path_set.add(left);
            Pair[] arr = nextStepStayRight(board, 'R', left, right);
            left = arr[0];
            right = arr[1];
            if (right.col == 0) {
                return null;
            }
        }

        return path_set;

    }

    static Set<Pair> bluePathSouth(char[][] board, int n) {
        Pair left = new Pair(n - 1, 0);
        Pair right = new Pair(n - 1, 1);

        Set<Pair> path_set = new HashSet<>();

        while (left.col < n - 1) {   //Break if we reach East wall
            path_set.add(left);
            Pair[] arr = nextStepStayRight(board, 'B', left, right);
            left = arr[0];
            right = arr[1];
            if (left.row == 0) {    //Break if we reach North wall
                return null;
            }
        }

        return path_set;

    }

    static Set<Pair> bluePathNorth(char[][] board, int n) {
        Pair left = new Pair(0, 1);
        Pair right = new Pair(0, 0);

        Set<Pair> path_set = new HashSet<>();

        while (right.col < n - 1) {   //Break if we reach East wall
            path_set.add(right);
            Pair[] arr = nextStepStayLeft(board, 'B', left, right);
            left = arr[0];
            right = arr[1];
            if (right.row == n - 1) {      //Break if we reach South wall
                return null;
            }
        }

        return path_set;

    }

    static Pair[] nextStepStayRight(char[][] board, char color, Pair left, Pair right) {
        Pair next_hex = getNextHex(left, right);
        // If next_nex == color turn right, other wise left and return new pair of hexes
        return board[next_hex.row][next_hex.col] == color ? new Pair[]{next_hex, right} : new Pair[]{left, next_hex};

    }

    static Pair[] nextStepStayLeft(char[][] board, char color, Pair left, Pair right) {
        Pair next_hex = getNextHex(left, right);
//        System.out.println("Next hex:" + next_hex);
//        System.out.println("Input Hex:" + left + "; " + right);
//        System.out.println("Output Hex:" + Arrays.toString(board[next_hex.row][next_hex.col] == color ? new Pair[]{left, next_hex} : new Pair[]{next_hex, right}));

        // If next_nex == color turn left, other wise right and return new pair of hexes
        return board[next_hex.row][next_hex.col] == color ? new Pair[]{left, next_hex} : new Pair[]{next_hex, right};

    }

    static Pair getNextHex(Pair left, Pair right) {
        List<Pair> dirs = List.of(new Pair(0, 1), new Pair(-1, 1), new Pair(-1, 0), new Pair(0, -1),
                new Pair(1, -1), new Pair(1, 0));

        Pair relative_right = new Pair(right.row - left.row, right.col - left.col);

        Pair next_hex = IntStream.range(0, dirs.size())
                .filter(i -> dirs.get(i).equals(relative_right))
                .mapToObj(i -> {
                    i = (i + 1) % 6;
                    return left.add(dirs.get(i));
                }).findFirst().get();
        return next_hex;
    }

    static String boardToString(char[][] board) {
        return "["
                +

                Arrays.stream(board)
                        .map(Arrays::toString)
                        .collect(Collectors.joining(",\n"))
                +
                "]";
    }

    static String arrToString(boolean[][] arr) {
        return

                Arrays.stream(arr)
                        .map(Arrays::toString)
                        .map(s -> {
                            s = s.replaceAll("true", "T");
                            s = s.replaceAll("false", ".");
                            s = s.replaceAll("\\[", "");
                            s = s.replaceAll("]", "");
                            s = s.replaceAll(" ", "");
                            s = s.replaceAll(",", "");
                            return s;
                        })
                        .collect(Collectors.joining("\n"));
    }


    static class Pair {
        int row;
        int col;

        Pair(int x, int y) {
            this.row = x;
            this.col = y;
        }

        public Pair add(Pair other) {
            return new Pair(this.row + other.row, this.col + other.col);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (row != pair.row) return false;
            return col == pair.col;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + col;
            return result;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }
}