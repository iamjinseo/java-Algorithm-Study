package D4;

import java.io.*;
import java.util.*;

/*크루스칼 알고리즘을 이용한 최소스패닝트리*/
public class S3124_최소스패닝트리 {
	// 간선 클래스
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	// 전역변수들
	static int V, E; //정점, 간선 수 
	static Edge[] edgeList;
	static int[] parents; //서로소집합
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb =new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		// testcase
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken()); // 정점 개수
			E = Integer.parseInt(st.nextToken()); // 간선 개수
			edgeList = new Edge[E];
			long resWeight = 0; // 최종 가중치
			int count = 0;  // 크루스칼 알고리즘으로 형성된 엣지 개수
			
			// edge 입력
			for (int e = 0; e < E; e++) {
				st =new StringTokenizer(br.readLine());
				// 정점1, 2, 가중치
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				edgeList[e] = new Edge(from, to, weight);
			}
			
			Arrays.sort(edgeList); // 크루스칼 알고리즘을 위한 가중치 기반 오름차순 정렬
			makeSet(); 
			
			// 모든 엣지에 대해
			for (Edge e : edgeList) {
				if(union(e.from, e.to)) { // 서로소 집합을 형성해봤는데 가능하다면
					resWeight += e.weight;   // 가중치에 더함
					if(++count == V-1) {
						break;
					}
				}
			}
			sb.append("#").append(t).append(" ").append(resWeight).append('\n');
		}// end testcase
		System.out.println(sb);
	}
	static void makeSet() { //서로소집합 만들기
		parents = new int[V+1];
		for (int i = 0; i <= V; i++) {
			parents[i] = i;
		}
	}
	
	// 경로압축
	static int findSet(int a) {
		if(a==parents[a])
			return a;
		return parents[a] = findSet(parents[a]); 
	}
	
	static boolean union(int from, int to) {
		int p1 = findSet(from);
		int p2 = findSet(to);
		
		if(p1==p2)
			return false;
		parents[p1] = p2; 
		return true;
	}
}
