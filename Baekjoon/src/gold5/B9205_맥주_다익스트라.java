package gold5;

import java.util.*;

public class B9205_맥주_다익스트라 {

    // 편의점 노드
    static class Node {
        int i, j;
        ArrayList<Edge> edges = new ArrayList<>();

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public void addEdge(Edge e) {
            edges.add(e);
        }
    }

    // 간선
    static class Edge {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();

        while (T-- > 0) {
            int C = sc.nextInt();
            Node[] nodes = new Node[C + 2];

            // 출발지
            nodes[0] = new Node(sc.nextInt(), sc.nextInt());

            // 편의점
            for (int c = 1; c <= C; c++) {
                nodes[c] = new Node(sc.nextInt(), sc.nextInt());
            }

            // 도착지
            nodes[C + 1] = new Node(sc.nextInt(), sc.nextInt());

            // 그래프 생성
            for (int i = 0; i < nodes.length; i++) {
                for (int j = i + 1; j < nodes.length; j++) {
                    if (areNear(nodes[i], nodes[j])) {
                        // i지점과 j지점 거리가 1000이하이면 이동 가능
                        int weight = Math.abs(nodes[i].i - nodes[j].i) + Math.abs(nodes[i].j - nodes[j].j);
                        nodes[i].addEdge(new Edge(j, weight));
                        nodes[j].addEdge(new Edge(i, weight));
                    }
                }
            }

            // Dijkstra's algorithm
            int[] dist = new int[C + 2];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[0] = 0;
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            pq.offer(0);

            while (!pq.isEmpty()) {
                int curr = pq.poll();

                for (Edge e : nodes[curr].edges) {
                    int next = e.to;
                    int weight = e.weight;

                    if (dist[next] > dist[curr] + weight) {
                        dist[next] = dist[curr] + weight;
                        pq.offer(next);
                    }
                }
            }

            sb.append(dist[C + 1] != Integer.MAX_VALUE ? "happy" : "sad").append('\n');
        }

        System.out.print(sb);
    }

    private static boolean areNear(Node node1, Node node2) {
        return Math.abs(node1.i - node2.i) + Math.abs(node1.j - node2.j) <= 1000;
    }
}
