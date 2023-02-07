package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1208_Flatten_2 {
	static int[] arr = new int[100];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 100; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			System.out.printf("#%d %d\n", t, dump(N));
		}
	}

	static int dump(int d) {
		int max_i = 0;
		int min_i = 0;

        for(int i=0; i<100; i++){
            if(arr[i] > arr[max_i]){
                max_i = i;
            }
            if(arr[i] < arr[min_i]){
                min_i = i;
            }
        } // 최대최소 탐색 끝
		if(d==0 || --arr[max_i] - ++arr[min_i] <=1) { //조건문 내에서 평탄화
			return arr[max_i] - arr[min_i];
		}
		return dump(d-1);
	}
}
