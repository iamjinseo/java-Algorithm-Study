package gold5;

import java.util.ArrayList;
import java.util.Scanner;

public class B13023_ABCDE_교수님버전 {
	static boolean ans;
	static int N, M;
	static ArrayList<Integer>[] adjList;
	static boolean[] visit;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		adjList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			adjList[a].add(b);
			adjList[b].add(a);
		}

		visit = new boolean[N];
		ans = false;

		for (int i = 0; i < N; i++) {
			dfs(i, 1);
			if (ans)
				break;
		}
	}
	static void dfs(int n, int cnt) {
		if(cnt == 5) {
			ans = true;
			return;
		}
		visit[n] = true;
		
		for (int next : adjList[n]) {
			if(!visit[next]) {
				dfs(next, cnt+1);
			}
			if(ans)break;
		}
	}
}
