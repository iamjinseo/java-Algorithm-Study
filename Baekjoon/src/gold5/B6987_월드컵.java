package gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
A는 B~F와 경기하고 B는 C~F와 경기하고...등등등 하면 경기를 15번 함
각 경기마다 두 팀이 이기고 비기고 지는 경우가 있음
각 경우에 대해 모두 가정해보고 옳다면 재귀를 돌리고 옳지 않다면 백트래킹 함
 * */
public class B6987_월드컵 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int[][] states = new int[6][3]; // 6팀의 승무패 상태

		// start league
		for (int t = 0; t < 4; t++) {
			boolean isPossible = true; // 가능한 리그인가
			String str = br.readLine(); // 리그 하나의 상태
			int idx = 0; // 입력용 idx

			// start input
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					states[i][j] = str.charAt(idx) - '0';
					idx += 2;
				}
			} // end input


			if (isPossible)
				sb.append(1).append(" ");
			else
				sb.append(0).append(" ");
		} // end league
		System.out.println(sb);
	}
}
