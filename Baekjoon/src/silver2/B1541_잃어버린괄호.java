package silver2;

import java.util.*;
import java.io.*;

/*
 * 수식을 - 로 쪼갠 후 맨처음 나온 건 그냥 더하기
 * 그 다음 원소부터는 +로 쪼개기
 * 쪼개진 문자열들에 대하여 parseInt후 sum에서 빼기
 * */
public class B1541_잃어버린괄호 {
	public static void main(String[] args) {
		int res = 0;
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine(), "-");
		StringTokenizer st2 = new StringTokenizer(st.nextToken(), "+");
		
		while(st2.hasMoreTokens()) {
			res += Integer.parseInt(st2.nextToken());
		}
		while(st.hasMoreTokens()) {
			st2 = new StringTokenizer(st.nextToken(), "+");
			while(st2.hasMoreTokens()) {
				res -= Integer.parseInt(st2.nextToken());
			}
		}
		System.out.println(res);
	}
}
