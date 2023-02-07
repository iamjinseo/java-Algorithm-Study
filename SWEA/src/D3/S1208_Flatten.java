package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

/*
 * 재귀 방식으로, 최대 높이-1 최소높이+1을 하며 덤프횟수 끝날 때까지 재귀
 * */
public class S1208_Flatten {
	static int[] arr = new int[100];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st =new StringTokenizer(br.readLine());
			for (int i = 0; i < 100; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			System.out.printf("#%d %d\n", t, dump(N));
		}
	}
	static int dump(int d) {
		Arrays.sort(arr);
		int max_h = arr[99];
		int min_h = arr[0];
		arr[99]--; arr[0]++;
				
		if(d==0) {
			return max_h - min_h;
		}
		if(max_h - min_h == 1 || max_h - min_h == 0) {
			return max_h - min_h;
		}
				
		return dump(d-1);
	}

}
