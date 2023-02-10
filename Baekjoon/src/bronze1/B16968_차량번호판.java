package bronze1;

import java.util.Scanner;

public class B16968_차량번호판 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str = sc.nextLine();
		str += " ";
		int sum = 1;
		int d = 0;
		int c = 0;

		for (int i = 0; i < str.length() - 1; i++) {
			char l = str.charAt(i);
			char next = str.charAt(i + 1);

			if (next == 'd') {
				if (l == next) {
					sum *= 9;
				} else {
					sum *= 26;
				}
			} else if (next == 'c') {
				if (l == next) {
					sum *= 25;
				} else {
					sum *= 10;
				}
			} else if (next == ' ') {
				if (l == 'd')
					sum *= 10;
				else
					sum *= 26;
			}
		}
		System.out.println(sum);
	}

}
