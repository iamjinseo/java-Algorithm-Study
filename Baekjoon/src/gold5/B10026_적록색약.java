package gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class B10026_적록색약 {
 
    static int N;
    static char map[][], map2[][]; //일반배열, 적록색약용 배열
    static int di[] = { -1, 1, 0, 0 };
    static int dj[] = { 0, 0, -1, 1 };
 
    // 색약 아닌사람
    public static void dfs(int i, int j, char col) {
        // 방문 처리
        map[i][j] = 'X'; 
 
        for (int n = 0; n < 4; n++) {
            int ni = i + di[n];
            int nj = j + dj[n];
            // 범위검사
            if (ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
            // 방문검사, 색 검사
            if (map[ni][nj] == 'X' || col!=map[ni][nj]) continue;
            dfs(ni, nj, map[ni][nj]);
        }
    }
 
    // 적록색약
    public static void dfs2(int i, int j, char col) {
        // 방문 처리
        map2[i][j] = 'X';
 
        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];
            // 범위를 넘어가면 pass
            if (ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
            // 이미 방문한 곳이면 pass
            if (map2[ni][nj] == 'i' || col!=map2[ni][nj]) continue;
            dfs2(ni, nj, map2[ni][nj]);
        }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        map = new char[N][N];     // 정상
        map2 = new char[N][N];    // 적록색약
        
        for (int i = 0; i < N; i++) {
            String in = br.readLine();
            for (int j = 0; j < N; j++) {
                // 정상
                map[i][j] = in.charAt(j); 
                // 적록색약: 빨간색과 초록색의 차이를 거의 느끼지 못한다 (G일 경우 R로 저장)
                if (in.charAt(j) == 'G')  map2[i][j] = 'R'; 
                else map2[i][j] = in.charAt(j); 
            }
        } // end input
 
        int cnt = 0; // 정상
        int cnt2 = 0;    // 적록색약
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 정상 : 방문하지 않은 곳이면
                if (map[i][j] != 'X') {
                    dfs(i, j, map[i][j]);
                    cnt++;
                }
                // 적록색약 : 방문하지 않은 곳이면
                if (map2[i][j] != 'X') {
                    dfs2(i, j, map2[i][j]);
                    cnt2++;
                }
            }
        }
        System.out.println(cnt + " " + cnt2);
    }
}