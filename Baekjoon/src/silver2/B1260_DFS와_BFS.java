package silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B1260_DFS와_BFS {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		int startNode = Integer.parseInt(st.nextToken());
		Graph g = new Graph();

		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			int N1 = Integer.parseInt(st.nextToken());
			int N2 = Integer.parseInt(st.nextToken());

			g.makeVertex(N1, N2);
		}

		g.DFS(startNode);
	}
}

class Graph {
	public Map<Integer, ArrayList<Integer>> adjList = new HashMap<>(); // 인접리스트(인접노드들)

	public void makeVertex(int N1, int N2) {
		// N1과 N2간의 Vertex추가
		// N1 노드가 value로 가지고있는 리스트에 N2추가

		ArrayList<Integer> temp = adjList.get(N1); // n1의 인접노드들

		if (temp == null) // 만약에 비었으면 새로만들기
			temp = new ArrayList<Integer>();

		// 간선만들기
		temp.add(N2);
		adjList.put(N1, temp);
	}

	public void DFS(int startNode) {
		Stack<Integer> stack = new Stack<>(); // DFS용 스택
		boolean[] visited = new boolean[adjList.size() + 1]; // 방문검사용 배열

		stack.add(startNode); // 스택에 순회 시작 노드 삽입

		// ================노드 순회 시작=====================
		while (stack != null) {
			int popedNode = stack.pop(); // 스택 상단에서 pop = 방문
			System.out.print(popedNode + " "); // pop한 노드(방문한 노드) 출력

			visited[popedNode] = true; // 방문처리

			// adjList에서 pop한 노드의 인접 노드들 검사
			ArrayList<Integer> popedList = adjList.get(popedNode);

			// 작은 노드부터 검사해야 하므로 정렬
			if (popedList != null) {
				Collections.sort(popedList);

				// 인접한 노드 하나 검사
				for (Integer node : popedList) {
					if (!visited[node]) {
						stack.add(node);
						break;
					}
				} // 인접한 노드 하나 검사 끝
			}

		} // 노드 순회 끝 ==================================

	} // DFS 끝 ==========================================

	public void BFS(int startNode) {

	}
}