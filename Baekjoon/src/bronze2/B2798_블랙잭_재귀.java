package bronze2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B2798_블랙잭_재귀 {
	static int N;
	static int M;
	static int[] given; //주어진 수
	static int[] combination = new int[3]; // 조합용 배열
	static ArrayList<Integer> results = new ArrayList<>(); //조합후 덧셈결과

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 카드개수
		M = Integer.parseInt(st.nextToken()); // 만들어야 하는 숫자

		// 주어진 숫자 입력
		given = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			given[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(given);

		// 조합
		comb(0, 0, N-1);
		
		//조합 후 M에 가까운 최대 합 구하기
		int res = 0;
		for (int i : results) {
			if(i>res && i<=M) res = i;
		}
		System.out.println(res);
	}

	static void comb(int idx, int start, int last) {
		int sum=0;
		if(idx == 3) {
			for (Integer i : combination) {
				sum += i;
			}
			results.add(sum);
			return;
		}
		
		for (int i=start; i<=last; i++) {
			combination[idx] = given[i];
			comb(idx+1, i+1, last);
		}
	}
}
