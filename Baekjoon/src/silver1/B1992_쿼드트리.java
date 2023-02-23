package silver1;

import java.io.*;
import java.io.*;

/*
 * 분할정복을 이용한 문제이다.
 * 분할정복 재귀를 들어갈 때 현재 시작 지점부터 범위까지의 부분의 합을 구한다.
 * 만약 합이 0이면 모두 흰색이고 사이즈^2이면 모두 검정색이다.
 * 색이 모두 같지 않으면 네 범위로 분할하여 재귀를 또 하기 전에 (를 붙인다.
 * 색이 모두 같으면 재귀를 멈추고 색에 해당하는 문자를 붙인다.
 * 네 범위 분할이 모두 끝나고 나왔을 때 )를 붙인다.  
 * */
public class B1992_쿼드트리 {
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}// end input
		concur(0,0,N); //분할정복 시작
		System.out.println(sb);
	}
	static void concur(int iStart, int jStart, int size) {
		// map에 속해있는 색 더하기
		int sum = 0;
		for (int i = iStart; i < iStart+size; i++) {
			for (int j = jStart; j < jStart+size; j++) {
				sum += map[i][j];
			}
		}
		// 색 검사
		if(sum == size*size) { //전부 1이면 size의 제곱인 합이나옴
			sb.append(1);
			return; 
		} else if(sum == 0) { //전부 0이면 하얀색
			sb.append(0);
			return;
		} else { //색이 혼합돼있음
			sb.append('('); //어랏?! 색이 달라?! 괄호를 시작
			
			// 네분할 하여 분할정복
			concur(iStart, jStart, size/2); //좌상
			concur(iStart, jStart+(size/2), size/2); //우상
			concur(iStart+(size/2), jStart, size/2); //좌하
			concur(iStart+(size/2), jStart+(size/2), size/2); //우하
			
			sb.append(')'); //네분할 정복 끝마침! 괄호를 끝냄
		}
	}
}
