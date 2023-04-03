package gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B9205_맥주마시면서걸어가기 {
	// 편의점 노드
	static class Node implements Comparable<Node>{
		int i, j, distance;
		
		public Node(int i, int j, int distance) {
			this.i = i;
			this.j = j;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			return this.distance - o.distance;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		 
		for (int t = 1; t <= T; t++) {
			PriorityQueue<Node> pq = new PriorityQueue<>();
			int con = Integer.parseInt(br.readLine());
			for (int c = 0; c < con; c++) {
				StringTokenizer st =new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				pq.offer(new Node(i, j, i+j));
			}
		}
		
	}
}
