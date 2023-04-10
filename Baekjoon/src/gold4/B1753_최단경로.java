package gold4;

import java.util.*;
import java.io.*;

public class B1753_최단경로 {
	static final int INF = Integer.MAX_VALUE;
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
		List<Edge>[] edgeList = new ArrayList[V+1]; // 간선리스트
		int dist[] = new int[V+1]; // 거리 배열
		int start = Integer.parseInt(br.readLine());
		boolean[] visit = new boolean[V+1];
		
		for (int i = 0; i < V+1; i++) {
			edgeList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edgeList[from].add(new Edge(to, weight));
		} 
		// 간선리스트 생성 끝
		
		// start dijkstra
		Arrays.fill(dist, INF);
		PriorityQueue<Edge> pq = new PriorityQueue<>(); 
		pq.offer(new Edge(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			if(visit[now.to]) continue;
			visit[now.to] = true;
			
			for (Edge edge : edgeList[now.to]) {
				int newDist = dist[now.to] + edge.weight;
				if(newDist < dist[edge.to])
					dist[edge.to] = newDist;
				
				pq.offer(new Edge(edge.to, dist[edge.to]));
			}
		} // end dijkstra
		for (int i = 1; i < V+1; i++) {
			System.out.println(dist[i]==INF?"INF":dist[i]);
		}
	}
}
