package no_rank;

import java.io.*;
import java.util.*;

public class S5658_보물상자비밀번호 {
	static int N,K;
	static char arr[]; // 입력받은 문자열 받을 배열
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st= new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			String str = br.readLine();
			arr = new char[N];
			
			for (int i = 0; i < str.length(); i++) {
				arr[i] = str.charAt(i);
			} // end input
			
			//// 돌리기 시작 ////
			HashMap<String, Long> hm = new HashMap<String, Long>(); //돌리기 할때마다 결과 넣음
//			HashSet<String> hs =new HashSet<>();
			int RotateCnt = (N/4)-1; // (N/4)-1이 회전수임
			/* N이 4일 때 0번 회전해야함
			 * N이 8일 때 1번 회전해야함
			 * N이 12일 때 2번 회전해야함
			 * 16일때는 3번 회전함
			 *  */
			
			// 배열 돌릴 때마다 N/4개의 문자열을 해쉬맵에 넣기
			do {
				rotate(arr); // 배열 돌리기 완료
				for (int i = 0; i < N; i+=(N/4)) {
					String tmp = "";
					for (int j = i; j < i+(N/4); j++) {
						tmp += arr[j];
					}
					hm.put(tmp, Long.parseLong(tmp, 16)); // 16진수 문자열을 10진수 long으로 변환
				}
			} while(RotateCnt-->0);
			System.out.println(hm);
			
			// 10진수 long에 대해 내림차순 정렬 -> K번째 수 출력
			ArrayList<Long> list = new ArrayList<>(hm.values()); // 정수들을 리스트로
			list.sort((l1, l2)-> l2.compareTo(l1)); // 내림차순 정렬
			sb.append("#").append(t).append(" ").append(list.get(K-1)).append('\n');  
		}
		System.out.println(sb);
	}
	// 배열을 시계방향으로 돌림
	static void rotate(char[] s) {
		char tmp = s[s.length-1];
		for (int i = s.length-1; i > 0; i--) {
			s[i] = s[i-1];
		}
		s[0] = tmp;
	}
}
