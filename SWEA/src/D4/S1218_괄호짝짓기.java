package D4;

import java.util.Scanner;
import java.util.Stack;

public class S1218_괄호짝짓기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int t = 1; t <= 10; t++) {
			int result = -1;
			int len = sc.nextInt();

			String str = sc.next();
			Stack s = new Stack();

			for (int i = 0; i < len; i++) {
				if (str.charAt(i) == '(' || str.charAt(i) == '{' || str.charAt(i) == '[' || str.charAt(i) == '<')
					s.push(str.charAt(i));
				else if (str.charAt(i) == ')' || str.charAt(i) == '}' || str.charAt(i) == ']' || str.charAt(i) == '>') {
					char pop = (char) s.pop();
					if ((str.charAt(i) == ')' && pop == '(') || (str.charAt(i) == '}' && pop == '{')
							|| (str.charAt(i) == ']' && pop == '[') || (str.charAt(i) == '>' && pop == '<')) {
						continue;
					} else {
						result = 0;
					}

				}
				if (result == 0)
					break;
			}
			if (s.size() != 0)
				result = 0;
			else
				result = 1;

			System.out.println("#" + t + " " + result);

		}
	}
}
