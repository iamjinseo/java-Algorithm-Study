package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class S1228_암호문1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		for(int t=1; t<=10; t++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#"+t).append(" "); //출력용
			
			int N = Integer.parseInt(br.readLine()); // 원본 암호문 길이N
			ArrayList<Integer>se = new ArrayList<>(); //총 암호문
			StringTokenizer st =new StringTokenizer(br.readLine());
			while (st.hasMoreElements()) { //암호문에 숫자 입력
				se.add(Integer.parseInt(st.nextToken()));
			} // end input
//			System.out.println(se);
			
			int C = Integer.parseInt(br.readLine()); //명령어 개수
//			System.out.println(C);
			st =new StringTokenizer(br.readLine()); //전체명령어 입력
			while(C-->0) {
				switch (st.nextToken()) {
				case "I": //insert
					// x, y, s입력
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					// for n from x  to y
					for (int n = 0; n < y; n++) {
						// se.add(n, 입력받는 숫자들)
						se.add(x++, Integer.parseInt(st.nextToken()));
					}
					break;
				} // end switch
			} // end command
			for (int i = 0; i < 10; i++) {
				sb.append(se.get(i)).append(" ");
			} //end for-each for print
			System.out.println(sb);
		} // end test
	}
}
