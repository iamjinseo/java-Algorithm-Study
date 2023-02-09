package silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B21736_헌내기는_친구가_필요해 {
	static int N;
	static int M;
	static char[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N  = Integer.parseInt(st.nextToken());
		N  = Integer.parseInt(st.nextToken());
		map = new char[N][M]; 
		
		for (int i = 0; i < N; i++) { //맵 입력
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = ch[j]; 
			}
		}
		
		
	}
}
