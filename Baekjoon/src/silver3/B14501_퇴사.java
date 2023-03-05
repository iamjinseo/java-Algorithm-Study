package silver3;

import java.util.*;
import java.io.*;

/*
d = d[N]
d[0] = 0

결정조건: 이 날에 일을 하거나, 안하거나.
d[오늘] = max(d[전날]+오늘페이, d[오늘])

일했음=>다음날 = 오늘+ti
일 안함=> 다음날 = 오늘+1




 * */
public class B14501_퇴사 {
	static class PayInfo {
		int t, p;

		public PayInfo(int t, int p) {
			super();
			this.t = t;
			this.p = p;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		PayInfo[] infos = new PayInfo[N];
		for (int i = 0; i < N; i++) {
			infos[i] = new PayInfo(sc.nextInt(), sc.nextInt());
		} // end input

		int[] d = new int[N + 2]; // 마지
		d[0] = 0;

		for (int i = N; i > 0; i--) {
			// 이 날에 일을 하거나, 안하거나.
			int p = infos[i].p;
			int t = infos[i].t;
			if (t + i <= N + 1) {
				d[i] = Math.max(p + d[i + t], d[i + 1]);
			}
			// 
			else { 
				d[i] = d[i+1];
			}
		}
	}
}
