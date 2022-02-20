package GoogleKickStarter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Starter code for the Kick Start 2022 problem Hex.
 */
public class HexStarterCode {

    Map<Pair, Boolean> redsTopRowEvaluated = new HashMap<>();
    Map<Pair, Boolean> bluesLeftColEvaluated = new HashMap<>();

    char[][] board;

    int blueLegalConnections = 0;
    int blueIllegalConnections = 0;
    int redLegalConnections = 0;
    int redIllegalConnections = 0;

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

            HexStarterCode instance = new HexStarterCode();
            String status = instance.determineStatus(board);
            System.out.println("Case #" + caseIndex + ": " + status);

            //TODO reset static state

        }
    }

    /**
     * Returns a status string as specified by the Hex problem statement.
     */
    String determineStatus(char[][] board) {
        // TODO(you): Implement this method to solve the problem!

        // Initilized board
        this.board = board;

//        System.out.println(boardToString());
        // List of Red nodes in row 0


        for (int i = 0; i < this.board[0].length; i++) {
            if (this.board[0][i] == 'R') {
                redsTopRowEvaluated.put(new Pair(0, i), false);
            }
        }

//        TODO: keep track of starts already evaluated

        // List of Blue nodes in col 0

        for (int i = 0; i < this.board.length; i++) {
            if (this.board[i][0] == 'B') {
                bluesLeftColEvaluated.put(new Pair(i, 0), false);
            }
        }


        // Check for red connections
        redsTopRowEvaluated.keySet()
                .forEach(key -> {

                    if (!redsTopRowEvaluated.get(key)) {
                        checkForRedConnection(key);
                    }

                });

        // Check for blue connections
        System.out.println(bluesLeftColEvaluated);
        bluesLeftColEvaluated.keySet()
                .forEach(key -> {

                    if (!bluesLeftColEvaluated.get(key)) {
                        checkForBlueConnection(key);
                    }

                });

        // Check count of nodes
        int blueCount = countOccupiedNodes('B');
        int redCount = countOccupiedNodes('R');

        // Evaluate state scenarios

//        System.out.println(redLegalConnections);
        System.out.printf("Red Legal: %d; Red Illegal: %d; Blue Legal: %d; Blue Illegal: %d", redLegalConnections,
                redIllegalConnections, blueLegalConnections, blueIllegalConnections);

        // 1. count differs > 1 -> Impossible
        if (Math.abs(blueCount - redCount) > 1) {
            return "Impossible";
            // 2. Number of illegal connections > 1 -> Impossible
        } else if (redIllegalConnections + blueIllegalConnections > 0) {
            return "Impossible";
            // 3. sum of legal connections > 1 -> Impossible
        } else if (redLegalConnections + blueLegalConnections > 1) {
            return "Impossible";
            // 4. Legal connection sum == 0 -> Nobody wins
        } else if (redLegalConnections + blueLegalConnections == 0) {
            return "Nobody wins";
            // 5. either player legal connection == 1 -> player wins
        } else if (redLegalConnections == 1) {
            if (redCount >= blueCount) {
                return "Red wins";
            } else {
                return "Impossible";
            }
        } else if (blueLegalConnections == 1) {
            if (blueCount >= redCount) {
                return "Blue wins";
            } else {
                return "Impossible";
            }
        } else {
            return "Impossible";
        }
    }

    void checkForRedConnection(Pair start) {


        List<Pair> evaluatedStartNodes = new ArrayList<>();
        List<Pair> evaluatedFinishNodes = new ArrayList<>();
//        int numberOfStartNodes;
//        int numberOfFinishNodes;
        int minNumbOfNodesInBetween = Integer.MAX_VALUE;

        Pair[] dirs = {
                new Pair(1, -1),
                new Pair(1, 0),
                new Pair(0, -1),
                new Pair(0, 1),
                new Pair(-1, 0),
                new Pair(-1, 1)
        };

        boolean[][] visited = new boolean[board.length][board.length];

        // BFS queue
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.row][start.col] = true;


        // Start node

        while (!queue.isEmpty()) {
//            System.out.println(queue);
            // Remove from BFS queue and mark as visited in visited[][]
            Pair pair = queue.poll();

            Arrays.stream(dirs)
                    .filter(direction -> {
                        Pair newPair = pair.add(direction);
                        return isValid(newPair, 'R', visited);
                    })
                    .forEach(direction -> {
                        Pair newPair = pair.add(direction);
                        queue.offer(newPair);
                        visited[newPair.row][newPair.col] = true;

                    });

            // Test whether it's start node
            if (pair.row == 0) {
                evaluatedStartNodes.add(pair);
            }

            // Test whether it's finish node
            if (pair.row == board.length - 1) {
                evaluatedFinishNodes.add(pair);
            }
        }

        if (evaluatedFinishNodes.size() > 0) {

            minNumbOfNodesInBetween = Arrays.stream(visited)
                    .mapToInt(boolArr -> {
                        int count = 0;
                        for (int i = 0; i < visited.length; i++) {
                            if (boolArr[i]) {
                                count++;
                            }
                        }
                        return count;
                    })
                    .min()
                    .getAsInt();
        }


        // Update the state of the board with the start red nodes that were evaluated and should not be evaluated again
        evaluatedStartNodes.forEach(pair -> {
            redsTopRowEvaluated.compute(pair, (k, v) -> true);
        });

        // Evaluate connection

        // 1. Number of finish nodes == 0 -> no connection
        if (evaluatedFinishNodes.size() == 0) {
            return;
            // 2. Number of finish nodes > 0 && Min Number of Nodes in any row is 1 (choke point and last move to get to
            // the current state) -> 1 legal connection
        } else if (minNumbOfNodesInBetween == 1) {
            redLegalConnections++;
            // 3. Number of finish nodes > 0 && min number of nodes along the chain in between start and finish > 1 ->
            // illegal connection
        } else {
            redIllegalConnections++;
        }

    }

    void checkForBlueConnection(Pair start) {


        List<Pair> evaluatedStartNodes = new ArrayList<>();
        List<Pair> evaluatedFinishNodes = new ArrayList<>();
//        int numberOfStartNodes;
//        int numberOfFinishNodes;
        int minNumbOfNodesInBetween = Integer.MAX_VALUE;

        Pair[] dirs = {
                new Pair(1, -1),
                new Pair(1, 0),
                new Pair(0, -1),
                new Pair(0, 1),
                new Pair(-1, 0),
                new Pair(-1, 1)
        };

        boolean[][] visited = new boolean[board.length][board.length];

        // BFS queue
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.row][start.col] = true;

        // Start node

        while (!queue.isEmpty()) {
//            System.out.println(queue);
            // Remove from BFS queue and mark as visited in visited[][]
            Pair pair = queue.poll();

            Arrays.stream(dirs)
                    .filter(direction -> {
                        Pair newPair = pair.add(direction);
//                        System.out.println("Old Pair: " + pair + "; New Pair: " + newPair + "; isValid:" + isValid(newPair, 'B', visited));
                        return isValid(newPair, 'B', visited);
                    })
                    .forEach(direction -> {
                        Pair newPair = pair.add(direction);
                        queue.offer(newPair);
                        visited[newPair.row][newPair.col] = true;
                    });

            // Test whether it's start node
            if (pair.col == 0) {
                evaluatedStartNodes.add(pair);
            }

            // Test whether it's finish node
            if (pair.col == board.length - 1) {
                evaluatedFinishNodes.add(pair);
            }
//            System.out.println("Node: " + pair + ", Eval Start Node current: " + evaluatedStartNodes.size() + ", Eval" +
//                    " finish " +
//                    "current: " + evaluatedFinishNodes.size());
        }

//        numberOfStartNodes = evaluatedStartNodes.size();
//        numberOfFinishNodes = evaluatedFinishNodes.size();

//        System.out.println("Eval finish nodes:" + evaluatedFinishNodes.size());
        if (evaluatedFinishNodes.size() > 0) {
            int min = Integer.MAX_VALUE;

            for (int col = 0; col < visited.length; col++) {

                int count = 0;

                for (int row = 0; row < visited.length; row++) {

                    count = visited[row][col] ? count + 1 : count;

                }

                min = Math.min(min, count);
            }

            minNumbOfNodesInBetween = min;
            System.out.println(arrToString(visited));
        }

        // Update the state of the board with the start red nodes that were evaluated and should not be evaluated again
        evaluatedStartNodes.forEach(pair -> {
            bluesLeftColEvaluated.compute(pair, (k, v) -> true);
        });

        // Evaluate connection

        // 1. Number of finish nodes == 0 -> no connection
        System.out.println("min num:" + minNumbOfNodesInBetween);
        if (evaluatedFinishNodes.size() == 0) {
            return;
            // 2. Number of finish nodes > 0 && Min Number of Nodes in any col is 1 (choke point and last move to get to
            // the current state) -> 1 legal connection
        } else if (minNumbOfNodesInBetween == 1) {
            blueLegalConnections++;
            // 3. Number of finish nodes > 0 && min number of nodes along the chain in between start and finish > 1 ->
            // illegal connection
        } else {
            blueIllegalConnections++;
        }

//        System.out.println(arrToString(visited));

    }

    int countOccupiedNodes(char ch) {

        return Arrays.stream(board)
                .mapToInt(charArr -> {
                    int count = 0;
                    for (int i = 0; i < board.length; i++) {
                        if (charArr[i] == ch) {
                            count++;
                        }
                    }
                    return count;
                })
                .sum();
    }

    boolean isValid(Pair pair, char ch, boolean[][] visited) {

        if (pair.row >= 0 && pair.row < board.length && pair.col >= 0 && pair.col < board.length && board[pair.row][pair.col] == ch &&
                !visited[pair.row][pair.col]) {
            return true;
        }

        return false;

    }

    String boardToString() {
        return "["
                +

                Arrays.stream(board)
                        .map(Arrays::toString)
                        .collect(Collectors.joining(",\n"))
                +
                "]";
    }

    String arrToString(boolean[][] arr) {
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