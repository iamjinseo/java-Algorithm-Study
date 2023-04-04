package gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class B9205_맥주마시면서걸어가기 {
	// 편의점 노드
	static class Node{
		int i, j;
		
		public Node(int i, int j) {
			this.i = i;
			this.j = j;
		}

	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		 
		
		for (int t = 1; t <= T; t++) {
			ArrayList<Node> list = new ArrayList<>();
			int C = Integer.parseInt(br.readLine());
			boolean[][] dist = new boolean[C+2][C+2]; // 출발지, 편의점, 도착지 
			
			// 출발지
			st =new StringTokenizer(br.readLine());
			list.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			
			// 편의점
			for (int c = 0; c < C; c++) {
				st = new StringTokenizer(br.readLine());
				list.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			// 도착지
			st = new StringTokenizer(br.readLine());
			list.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			// end input
			
			// 플로이드 하기 전 가능한 경우에 대해 미리 그래프 생성
			// 출발지, 편의점, 도착지 사이 거리가 1000이하이면 이동 가능
			for (int i = 0; i < list.size(); i++) {
				for(int j=i+1; j<list.size(); j++) {
					if(isNear(list.get(i), list.get(j))) {
						// i지점과 j지점 거리가 1000이하이면 이동 가능
						dist[i][j] = dist[j][i] = true;
					}
				}
			}
			
			// Floyd - Washall
			// i에서 j로 갈 수 있는지 검사
			for (int k = 0; k < list.size(); k++) {
				for (int i = 0; i < list.size(); i++) {
					if(i==k ) continue;
					for (int j = 0; j < list.size(); j++) {
						if(i==j || j==k) continue;
						if(dist[i][k] && dist[k][j]) {
							dist[i][j] = true;
						}
					}
				}
			} // end floyd
			
			sb.append((dist[0][C+1]?"happy":"sad")).append('\n');
		}
		System.out.println(sb);
	}
	
	
	private static boolean isNear(Node node, Node node2) {
		if(Math.abs(node.i - node2.i) 
		   + Math.abs(node.j - node2.j )<= 1000)
			return true;
		return false;
	}
}
