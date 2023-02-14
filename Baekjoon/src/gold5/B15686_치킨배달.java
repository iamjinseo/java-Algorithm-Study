package gold5;

import java.io.*;
import java.util.*;

/*
 * M개의 치킨집만 남긴 경우를 모두 구함(M은 1이상 13이하)
 * 치킨집의 개수는 M보다 크거나 같고, 13보다 작거나 같다.
 * 도시에 있는 치킨집 중에서 최대 M개를 고르고, 나머지 치킨집은 모두 폐업시켜야 한다. 
 * 만약 map 에 치킨집이 5개 있으면 1개,2개,3개,4개,5개 남기는 시나리오 필요
 * */
public class B15686_치킨배달 {
	static int[] di = { 1, 0, -1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int M;
	static int[][] map;
	static ArrayList<int[]> ones = new ArrayList<>();
	static ArrayList<int[]> twos = new ArrayList<>();
	static int res = Integer.MAX_VALUE; //결과

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					ones.add(new int[] { i, j });
				else if (map[i][j] == 2)
					twos.add(new int[] { i, j });
			}
		} // end input
		combi_twos(new int[M], 0, 0);
		
		System.out.println(res);

	}
	static void combi_twos(int[] result, int idx, int start) {
		if(idx == M) { // 치킨집 살아남기기 완료
			int sumDistance = 0;
			for (int[] one : ones) { //1들에 대해서
				int distance = Integer.MAX_VALUE; // 가장 가까운 치킨집들과의 거리
				for (int i : result) { //1에 대한 살아남은 치킨집에 대해서
					int[] temp = twos.get(i);
					distance = Math.min(distance, Math.abs(temp[0]-one[0])+Math.abs(temp[1]-one[1]));
				}
				sumDistance += distance;
			}
			res = Math.min(sumDistance, res);
			return;
		}
		for (int i = start; i < twos.size(); i++) { //M개의 부분조합 만들기
			result[idx] = i; //twos의 인덱스 저장
			combi_twos(result, idx+1, i+1);
		}
	}

}
