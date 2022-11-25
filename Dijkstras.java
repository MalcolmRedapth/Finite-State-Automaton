import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * The Class DijkstrasShortestPath.
 */
public class Dijkstras {

    /** The Constant EPS. */
    private static final double EPS = 1e-6;

    /**
     * The Class Edge.
     */
    public static class Edge {

        /** The distance. */
        int start, end, distance;

        /** The name. */
        String name;

        /**
         * Instantiates a new edge.
         *
         * @param start the start
         * @param end the end
         * @param name the name
         * @param distance the distance
         */
        public Edge(int start, int end, String name, int distance) {
            this.start = start;
            this.end = end;
            this.name = name;
            this.distance = distance;
        }
    }

    /**
     * The Class State.
     */
    public static class State {

        /** The id. */
        int iD;

        /** The distance. */
        int distance;

        /**
         * Instantiates a new state.
         *
         * @param iD the id
         * @param distance the distance
         */
        public State(int iD, int distance) {
            this.iD = iD;
            this.distance = distance;
        }
    }

    /** The states. */
    private static int states;

    /** The distance. */
    private static int[] distance;

    /** The previous. */
    private static int[] previous;

    /** The graph. */
    private static List<List<Edge>> graph;

    /** The comparator. */
    private static Comparator<State> comparator = new Comparator<State>() {
        @Override
        public int compare(State state1, State state2) {
            if (Math.abs(state1.distance - state2.distance) < EPS) {
                return 0;
            }
            if ((state1.distance - state2.distance) > 0) {
                return +1;
            } else {
                return -1;
            }

        }
    };

    /**
     * Instantiates a new dijkstras shortest path.
     *
     * @param states the states
     */
    public Dijkstras(int states) {
        Dijkstras.states = states;
        createEmptyGraph(states);
    }

    /**
     * Instantiates a new dijkstras shortest path.
     *
     * @param states the states
     * @param comparator the comparator
     */
    public Dijkstras(int states, Comparator<State> comparator) {
        this(states);
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        Dijkstras.comparator = comparator;
    }

    /**
     * Adds the edge.
     *
     * @param start the start
     * @param end the end
     * @param name the name
     */
    public static void addEdge(int start, int end, String name) {
        graph.get(start).add(new Edge(start, end, name, 1));
    }

    /**
     * Gets the graph.
     *
     * @return the graph
     */
    public static List<List<Edge>> getGraph() {
        return graph;
    }

    /**
     * Dijkstra.
     *
     * @param start the start
     * @param end the end
     * @param dfa the dfa
     * @return the int
     */
    public static int dijkstra(int start, int end, String[][] dfa) {
        createEmptyGraph(states);
        for (int i = 0; i < states; i++) {
            for (int j = 0; j < states; j++) {
                if (!dfa[i][j].equals("  ")) {
                    addEdge(i, j, dfa[i][j].toString());
                }
            }
        }

        distance = new int[states];
        Arrays.fill(distance, (int) Double.POSITIVE_INFINITY);
        distance[start] = 0;

        PriorityQueue<State> pq = new PriorityQueue<>(2 * states, comparator);
        pq.offer(new State(start, 0));

        boolean[] visited = new boolean[states];
        previous = new int[states];

        while (!pq.isEmpty()) {
            State state = pq.poll();
            visited[state.iD] = true;

            if (distance[state.iD] < state.distance) {
                continue;
            }

            List<Edge> edges = graph.get(state.iD);
            for (int i = 0; i < edges.size(); i++) {
                Edge edge = edges.get(i);

                if (visited[edge.end]) {
                    continue;
                }

                int newDistance = distance[edge.start] + edge.distance;
                if (newDistance < distance[edge.end]) {
                    previous[edge.end] = edge.start;
                    distance[edge.end] = newDistance;
                    pq.offer(new State(edge.end, distance[edge.end]));
                }
            }

            if (state.iD == end) {
                return distance[end];
            }

        }
        return (int) Double.POSITIVE_INFINITY;
    }

    /**
     * Reconstruct path.
     *
     * @param s the start
     * @param e the end
     * @param d the dfa
     * @return the list
     */
    public static List<Integer> reconstructPath(int s, int e, String[][] d) {
        int distance = dijkstra(s, e, d);
        List<Integer> path = new ArrayList<>();
        if (distance == (int) Double.POSITIVE_INFINITY) {
            return path;
        }
        for (int current = e; current != s; current = previous[current]) {
            path.add(current);
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * Creates the empty graph.
     *
     * @param states the states
     */
    private static void createEmptyGraph(int states) {
        graph = new ArrayList<>(states);
        for (int i = 0; i < states; i++) {
            graph.add(new ArrayList<>());
        }
    }

    /**
     * First edge to SP.
     *
     * @param start the start
     * @param end the end
     * @param dfa the dfa
     * @return the string
     */
    public static String firstEdgeToSP(int start, int end, String[][] dfa) {
        states = dfa.length;
        List<Integer> path = reconstructPath(start, end, dfa);
        int firstStep = path.get(0);
        String fS = "-" + dfa[start][firstStep];
        return fS;
    }

    /**
     * Int to alpha.
     *
     * @param number the number
     * 
     * @return the string
     */
    public static String intToAlpha(int number) {
        switch (number) {
        case 0:
            return "-a";
        case 1:
            return "-b";
        case 2:
            return "-c";
        case 3:
            return "-d";
        case 4:
            return "-e";
        case 5:
            return "-f";
        case 6:
            return "-g";
        case 7:
            return "-h";
        case 8:
            return "-i";
        case 9:
            return "-j";
        case 10:
            return "-k";
        case 11:
            return "-l";
        case 12:
            return "-m";
        case 13:
            return "-n";
        case 14:
            return "-o";
        case 15:
            return "-p";
        case 16:
            return "-q";
        case 17:
            return "-r";
        case 18:
            return "-s";
        case 19:
            return "-t";
        case 20:
            return "-u";
        case 21:
            return "-v";
        case 22:
            return "-w";
        case 23:
            return "-x";
        case 24:
            return "-y";
        case 25:
            return "-z";

        default:
            return "0";
        }
    }
}
