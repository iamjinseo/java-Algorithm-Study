package silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B1260_DFS��_BFS {
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
	public Map<Integer, ArrayList<Integer>> adjList = new HashMap<>(); // ��������Ʈ(��������)

	public void makeVertex(int N1, int N2) {
		// N1�� N2���� Vertex�߰�
		// N1 ��尡 value�� �������ִ� ����Ʈ�� N2�߰�

		ArrayList<Integer> temp = adjList.get(N1); // n1�� ��������

		if (temp == null) // ���࿡ ������� ���θ����
			temp = new ArrayList<Integer>();

		// ���������
		temp.add(N2);
		adjList.put(N1, temp);
	}

	public void DFS(int startNode) {
		Stack<Integer> stack = new Stack<>(); // DFS�� ����
		boolean[] visited = new boolean[adjList.size() + 1]; // �湮�˻�� �迭

		stack.add(startNode); // ���ÿ� ��ȸ ���� ��� ����

		// ================��� ��ȸ ����=====================
		while (stack != null) {
			int popedNode = stack.pop(); // ���� ��ܿ��� pop = �湮
			System.out.print(popedNode + " "); // pop�� ���(�湮�� ���) ���

			visited[popedNode] = true; // �湮ó��

			// adjList���� pop�� ����� ���� ���� �˻�
			ArrayList<Integer> popedList = adjList.get(popedNode);

			// ���� ������ �˻��ؾ� �ϹǷ� ����
			if (popedList != null) {
				Collections.sort(popedList);

				// ������ ��� �ϳ� �˻�
				for (Integer node : popedList) {
					if (!visited[node]) {
						stack.add(node);
						break;
					}
				} // ������ ��� �ϳ� �˻� ��
			}

		} // ��� ��ȸ �� ==================================

	} // DFS �� ==========================================

	public void BFS(int startNode) {

	}
}