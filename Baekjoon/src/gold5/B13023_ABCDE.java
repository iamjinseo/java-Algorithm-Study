package gold5;

import java.io.*;
import java.util.*;

/*
 * 만약 친구 관계도를 그래프로 그린다면
 * 간선을 4번 탈 수 있어야 함
 * ========================
 * 예를 들어 2번 예제를 그래프로 그려보면
 * 4번 정점에서 탐색을 시작했을 때
 * 간선을 4번 타서 또다른 4개의 정점을 만날 수 있음
 * 간선을 탈때마다 'vertex1은 vertex2의 친구다' 라는 관계를 형성한다고 생각하면 됨
 * ========================
 * 그렇다면 모든 vertex를 루트노드로 지정하여 DFS 탐색을 시작하고
 * 조건에 부합하는지 검사해야 함.
 * */
public class B13023_ABCDE {
	static int N; // 친구 수
	static int M; // 친구 관계 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Graph g = new Graph(N);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()); // 관계 입력
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());

			g.makeEdge(v1, v2); // v1과 v2사이의 관계 형성
		} // end input

		// 모든 정점에 대해 DFS탐색 시작
		// depth가 1에서 시작하며 탐색 시작함. 5에 도달하면 조건 충족임
		for (int i = 0; i < N; i++) {
			g.DFS(i, new boolean[N], 1);
		}

		// 탐색했는데도 조건 충족하지 못해 exit코드 실행 못하면 여기로 와서 0출력
		System.out.println(0);
	}
}

class Graph {
	public int vertexNum;
	public Map<Integer, ArrayList<Integer>> adjList;

	public Graph(int vertexNum) {
		super();
		this.vertexNum = vertexNum;
		this.adjList = new HashMap<Integer, ArrayList<Integer>>();
	}

	// 정점간의 관계를 형성한다. 관계는 양방향이다.
	public void makeEdge(int v1, int v2) {
		// ========== v1의 인접리스트에 v2추가하기 ===========
		ArrayList<Integer> temp = this.adjList.get(v1); // v1의 인접하는 노드들 가져오기

		if (temp == null) { // 인접하는 노드가 하나도 없으면
			temp = new ArrayList<Integer>();
		}
		temp.add(v2); // v2추가

		adjList.put(v1, temp);

		// =========== v2의 인접리스트에 v1추가하기 ===========
		temp = this.adjList.get(v2); // v2의 인접하는 노드들 가져오기

		if (temp == null) { // 인접하는 노드가 하나도 없으면
			temp = new ArrayList<Integer>(); // 새로 생성
		}
		temp.add(v1); // v1추가

		adjList.put(v2, temp);
	}

	// depth가 1에서 시작하며 탐색 시작함. N에 도달하면 조건 충족임
	public void DFS(int root, boolean[] visit, int depth) {
		visit[root] = true; // 방문 먼저
		System.out.println("root: " + root + ", visit: " + Arrays.toString(visit) + ", depth: " + depth);

		// 깊이가 M이면 조건 충족 -> 종료
		if (depth == 5) {
			System.out.println(1);
			System.exit(0);
		}

		// 인접하는 노드가 있고 방문하지 않았을 때 탐색 시작
		ArrayList<Integer>adjNodes = adjList.get(root);
		if (adjNodes != null) {
			for (Integer v : adjNodes) {
				System.out.println("  v: " + v + " visited: " + visit[v]);
				if (!visit[v])
					DFS(v, visit, depth + 1);
			}
		}
		visit[root] = false; // depth==5에서 리턴되지 못하고 결국 끝났을 때: 실패한 경로일 때 방문 해제
	}
}
