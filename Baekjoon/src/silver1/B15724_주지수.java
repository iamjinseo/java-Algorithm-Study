package silver1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
N, M, map 입력 받음
map의 각 행에 대하여 누적합을 만들어 새 배열에 넣음(새 배열은 N+1 x M+1)
그리고 직사각형 범위 개수 입력받음
직사각형 범위만큼의 합을 누적합에서 구함
예를 들어 3x3짜리에서 오른쪽 위의 1x1이 궁금하다면: 맨 윗 행에서 맨 오른쪽 누적합 - 맨 오른쪽에서 앞의 누적합
 * */
public class B15724_주지수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];

	}

}
