package gold5;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class B2023_신기한소수 {
	static List<Integer> res;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		res = new ArrayList<>();
		getRes(N, 0);
		for (int num : res) {
			System.out.println(num);
		}
	}

	public static void getRes(int n, int num) {
		if (n == 0) {
			if (isPrime(num))
				res.add(num);
			return;
		}
		for (int i = 0; i < 10; i++) {
			if(isPrime(num*10+i))getRes(n-1, num*10+i);
		}
	}

	static boolean isPrime(int n) {
		if (n <= 1)
			return false;
		for (int i = 2; i <= (int) Math.sqrt(n); i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
}