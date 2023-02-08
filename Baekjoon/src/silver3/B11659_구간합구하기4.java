package silver3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N M 입력
 * 숫자들 입력 => 배열
 * 만약 1 3 입력받음 => 1~3 합 구함
 * 만약 2 4 입력받음 => 1~3합에서 1 빼고 4넣음
 * 만약 3 5 입력받음 => 2~4합에서 2빼고 5넣음
 * 만약 2 3 입력받음 => 3~5합에서 2 더하고 5 4 뺌
 * */
public class B11659_구간합구하기4 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int nums[] = new int[N];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		// 구간합 시작
		int sum = 0;
		int from = 0, to = 0, prev_from = 0, prev_to = 0;
		for (int i = 0; i < M; i++) {
			prev_from = from;
			prev_to = to;
			
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			if (i == 0) { // 첫시도일 때만 처음부터 더하기
				for (int j = from - 1; j < to; j++) {
					sum += nums[j];
				}
			}
			
			int from_diff = prev_from - from; 

		}

	}
}
