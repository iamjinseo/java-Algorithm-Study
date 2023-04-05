package silver1;

import java.util.*;
import java.io.*;

public class B2531_회전초밥 {
	static int[] sushi, count;
	static int N, d, k, c, ans; // 총 접시, 초밥 번호, 연속 접시 수, 쿠폰
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		d = sc.nextInt();
		k = sc.nextInt();
		c = sc.nextInt();
		
		sushi = new int[N];
		
		for (int i = 0; i < N; i++) 
			sushi[i] = sc.nextInt(); // 회전초밥 입력
		
		count = new int[d+1]; // 초밥 번호가 d번가지 있음
		
		count[c]++; // 쿠폰을 나중에 구간 변경하면서 체크하면 복잡하니까 무조건 먹은걸로 치고 가짓수 세기
		int tmp=1; // 먹은 초밥 가짓수 (쿠폰 초밥 먹음)
		
		for (int i = 0; i < k; i++) {
			if(count[sushi[i]]==0) { // i 접시에 담긴 sushi[i] 초밥을 먹은 카운트가 0이면 
				tmp++; // 가짓 수 증가
			}
			count[sushi[i]]++; // 해당 초밥 카운트 기록
		}
		for (int s = 1; s < N; s++) { // 이제 다음 구간의 시작 위치를 s부터로 하자
			count[sushi[s-1]]--; // 새로운 시작위치 바로 앞 접시의 초밥 뱉기
			if(count[sushi[s-1]]==0) // 알고보니 하나만 있는 초밥
				tmp--; // 가짓수 감소
			
			int newDish = (s+k-1)%N; // 이제 저 뒤에 새로 먹게되는 접시번호
			if(count[sushi[newDish]]==0) // 처음보는 초밥
				tmp++; // 가짓수 늘리기
			count[sushi[newDish]]++; //해당 초밥 먹은갯수 늘어남
			
			ans = Math.max(ans, tmp);
		}
		System.out.println(ans);
	}
}
