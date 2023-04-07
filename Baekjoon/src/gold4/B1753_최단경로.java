package gold4;

import java.util.*;
import java.io.*;

public class B1753_최단경로 {
	static class Edge implements Comparable<Edge> {
		int to, weight;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		List<Edge>[] edgeList = new ArrayList[V]; // 간선리스트
		int dist[] = new int[V]; // 거리 배열
		int start = Integer.parseInt(br.readLine());
		boolean[] visit = new boolean[V];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[from].add(new Edge(to, weight));
		} // 간선리스트 생성 끝
		
		// start dijkstra
		PriorityQueue<Edge> pq = new PriorityQueue<>(); 
		pq.offer(new Edge(start, 0));
		dist[start] = 0;
		visit[start] = true;
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			for (Edge edge : edgeList[now.to]) {
				int newDist = now.weight
			}
		} // end dijkstra

	}
}
