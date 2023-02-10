package D3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Swea1225_암호생성기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int t = 1; t <= 10; t++) {
			int n = sc.nextInt();

			Deque<Integer> q = new ArrayDeque<>();
			for (int i = 0; i < 8; i++) {
				q.add(sc.nextInt());
			} // 입력완료

			int cnt = 1;
			while (true) {
				int first = q.pollFirst();
				if (first - cnt <= 0) {
					q.addLast(0);
					break;
				} else {
					q.addLast(first - cnt);
				}
				cnt = cnt % 5;
				cnt++;
			}
			System.out.print("#"+t+" ");
			for (Integer i : q) {
				System.out.print(i+" ");
			}
			System.out.println();
		}

	}
}
