package silver4;

import java.util.*;
import java.io.*;

/*
 * 만약에 남자면
 * for from i-1 to 배열끝 do i+i-1
 *  상태바꾸기
 * 만약 여자면
 * 	변경가능 스위치 인덱스1, 2 = 초기인덱스, 초기인덱스 
 * 	현재위치에서 -1, +1 대칭 검사. 배열 벗어나면 안됨
 * 		다름 -> 변경가능 스위치 인덱스 1,2 범위의 스위치 바꾸기
 *  	같음 -> 변경 가능 스위치 인덱스1, 2, 저장.
 *  		  -> 무한반복
 * */
public class B1244_스위치켜고끄기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 스위치 입력받기
		int N = Integer.parseInt(br.readLine()); // 스위치 개수
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] ss = new int[N];
		for (int i = 0; i < N; i++) {
			ss[i] = Integer.parseInt(st.nextToken());
		}

		// 학생 입력받기
		int students = Integer.parseInt(br.readLine()); // 학생수
		for (int i = 0; i < students; i++) {
			st = new StringTokenizer(br.readLine()); // 성별과 스위치 입력
			int gender = Integer.parseInt(st.nextToken()); // 성별
			int s = Integer.parseInt(st.nextToken()); // 스위치

			if (gender == 1) { // 남자
				for (int j = s - 1; j < N; j += s)
					ss[j] = ss[j] == 0 ? 1 : 0; // 스위치 상태 바꾸기
			} else { // 여자
				int idx1 = s-1, idx2 = s-1; // 변경가능 스위치 인덱스 1,2
				while (true) { // 범위 확장하며 대칭검사
					int n1 = idx1 - 1; // 변경가능 스위치 인덱스로부터 1씩 범위 넓히기
					int n2 = idx2 + 1;

					//범위 밖이거나 대칭 아닐때 
					if (n1<0 || n2>=N || ss[n1] != ss[n2]) { 
						for (int j = idx1; j <= idx2; j++) // 가능 범위까지의 상태 바꾸기
							ss[j] = ss[j] == 0 ? 1 : 0; // 스위치 상태 바꾸기
						break;
					} else { // 대칭임
						// 변경가능 스위치 인덱스 재조정
						idx1 = n1;
						idx2 = n2;
					}
				}
			} // 여자 남자 검사 끝

		} /// 테스트케이스 끝
		for (int i = 1; i <= N; i++) {
			sb.append(ss[i-1]).append(" ");
			if(i%20==0) sb.append("\n");
		}
		System.out.println(sb);
	}
}
