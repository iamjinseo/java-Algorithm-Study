package level1;
import java.util.*;
import java.lang.*;
public class P과일장수 {
	static int k = 4;
	static int m = 3;
	static int[] score = {4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2};
	public static void main(String[] args) {
        // k가 점수, m이 개수
        // Arrays.asList(score).sort((o1, o2)->(o2.compareTo(o1)));
        // System.out.println(Arrays.toString(score));
		Arrays.sort(score);
		int min = Integer.MAX_VALUE;
		int res = 0;
		int cnt = 0;
		for (int i = score.length-1; i >= 0; i--) {
			cnt++;
			min = Math.min(mi, score[i]);
			if(cnt==m) {
				res += min*m;
				min = 0;
			}
		}
	}
}
