package gold4;

import java.util.*;
import java.util.Map.Entry;
import java.io.*;

/*
 * 입력: 구역 개수 N
 * 입력: 구역 인구 1번~N번구역까지
 * 입력: 인접한 구역의 수와 인접한 구역의 번호들. 
 * => 뭔 뜻이냐면 첫번째로 입력받을 때, 1번 노드에 몇 개가 인접해있고 그 노드는 무엇인지 나타냄
 * ==========
 * 가능한 모든 조합에 대한 부분집합 생성
 * -> 부분집합 생성 후 그 조합이 서로 연결돼있는 것인지 확인
 * -> 연결돼있다면 각 구역에 대해 인구 합 구하고, 서로 뺀 다음 res와 비교
 * ==========
 * 연결 확인 매커니즘: BFS이용
 * 매개변수로 구역 리스트 area를 받아 0번 인덱스 값을 시작노드로 하고 BFS를 돌림
 * 현재 탐색중인 노드의 인접노드들에 대해 방문하지 않았고 같은 구역이 맞으면 큐에 넣음 -> 무한반복
 * 탐색이 모두 끝난 후 서로 연결된 것이 맞다면 방문검사 리스트에 area의 모든 원소가 들어있을 것
 * */
public class B17471_게리맨더링 {
	static int N; // 노드개수
	static Map<Integer, ArrayList<Integer>> adjList = new HashMap<>(); // 인접행렬
	static int[] populations;
	static boolean[] result; // 조합용 배열
	static ArrayList<Integer> red, blue; // 빨간팀 파란팀
	static int res = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 노드 개수
		populations = new int[N + 1];
		result = new boolean[N + 1]; // 조합용 배열

		// 인구 입력 시작
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			populations[i] = Integer.parseInt(st.nextToken());
		} // 인구 입력 끝

		// i번 노드에 대한 연결관계 입력
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int nodes = Integer.parseInt(st.nextToken()); // 인접 노드들
			while (st.hasMoreTokens()) {
				// 인접한 노드 하나에 대한 연결관계 생성.
				int node = Integer.parseInt(st.nextToken());
				ArrayList<Integer> tempList = adjList.get(i);
				if (tempList == null) {
					tempList = new ArrayList<Integer>();
				}
				tempList.add(node);
				adjList.put(i, tempList);

			}
		} // 연결관계 입력 끝
			// end input

		for (Entry<Integer, ArrayList<Integer>> e : adjList.entrySet()) {
			System.out.println("node " + e.getKey() + "의 인접노드들");
			System.out.println(e);
		}

		combi(1); // 구역 조합 실시
		if(res==Integer.MAX_VALUE) { //res가 결정이 안됐다는 건 구역을 나눌 수 없다는 뜻
			System.out.println(-1);
		}else {
			System.out.println(res);
		}
	}

	// 구역을 부분집합으로 조합한다.
	static void combi(int idx) {
		// 부분집합이 완성됐을 때, 구역 나누기
		if (idx == N + 1) {
			// 각 구역은 빨간 구역, 파란 구역
			red = new ArrayList<>();
			blue = new ArrayList<>();
			for (int i = 1; i <= N; i++) { // 각 노드에 대해 구역 선정
				if (result[i]) // true인 애들은 빨간 구역
					red.add(i); 
				else if (!result[i]) // false인 애들은 파란 구역
					blue.add(i); 
			}
			System.out.println("red: " + red);
			System.out.println("blue: " + blue);

			// 두 구역 다 연결 돼있으면
			if (isConnected(red) && isConnected(blue)) {
				// 각 구역의 인구수 합 구할 것
				System.out.println("연결됨");
				int redSum = 0;
				int blueSum = 0;
				// 모든 빨간 구역에 대해 순회
				for (int i = 0; i < red.size(); i++) {
					redSum += populations[red.get(i)];
				}
				for (int i = 0; i < blue.size(); i++) {
					blueSum += populations[blue.get(i)];
				}
				res = Math.min(res, Math.abs(redSum - blueSum));
			}
			return;
		}
		// 유도파트
		result[idx] = true;
		combi(idx + 1);
		result[idx] = false;
		if (result[1] == false)
			return;
		combi(idx + 1);
	}

	// 구역간 연결돼있는지 검사하는 함수. BFS응용
	// 예를들어 area에 4,1,3들어있을 때 무작위로 하나 골라서 탐색 시작한 후
	// 더이상 인접노드 없을때까지 탐색-> 지금까지 만나온 노드들이 area에 있는가?
	private static boolean isConnected(ArrayList<Integer> area) {
		// 선거구 area에 어떤 구역도 없으면 안됨
		if (area.size() == 0)
			return false;

		Set<Integer> visit = new HashSet<>(); // 방문검사용 세트. 확인할 때 O(1)
		Queue<Integer> dq = new ArrayDeque<>(); // bfs용 큐
		int root = area.get(0); // 첫번째로 탐색할 노드
		dq.offer(root);

		// bfs시작
		while (!dq.isEmpty()) {
			// 현재 탐색중인 노드의 인접노드들 꺼내기
			ArrayList<Integer> adjNodes = adjList.get(dq.poll());
			
			//인접노드가 없다? 말이 안됨
			if(adjNodes==null)
				return false;

			// 각 인접노드에 대해 탐색할 것
			for (Integer n : adjNodes) {
				if (!visit.contains(n) && area.contains(n)) { // 방문 안했고 같은 구역이어야 탐색
					// 방문처리
					dq.offer(n);
					visit.add(n);
				}
			}
		} // area의 모든 노드가 연결돼있는지 검사했음.

		// 만약 전부 연결돼있다면 visit에 area원소 전부 들어가 있을 것임
		boolean allContained = true;
		for (Integer node : area) {
			if (!visit.contains(node))
				allContained = false;
		}
		return allContained;
	}
}
