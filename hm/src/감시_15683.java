import java.util.ArrayList;
import java.util.Scanner;

public class 감시_15683 {
	static int N,M,result=100;
	static int[][] map;
	static int[] rotationSize = {4,2,4,4,1};
	static ArrayList<CCTV> cctv;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); M = sc.nextInt();
		map = new int[N][M];
		cctv = new ArrayList<>();
		for(int i = 0 ; i < N; i++){
			for(int j = 0 ; j < M; j++){
				map[i][j] = sc.nextInt();
				if(map[i][j] != 0 && map[i][j]!=6){
					cctv.add(new CCTV(i,j,map[i][j]-1));
				}
			}
		}
		dfs(0);
		System.out.println(result);
	}
	private static void dfs(int index){
		if(index == cctv.size()){
			int count = 0;
			for(int i = 0 ; i < N; i++){
				for(int j = 0 ; j < M; j++){
					if(map[i][j] == 0) count++;
				}
			}
			if( result > count) {
				result = count;
			}
			return;
		}
		int[][] backUpMap = new int[N][M];
		CCTV camera = cctv.get(index);
		int type = camera.type;
		for(int dir = 0; dir < rotationSize[type]; dir++){
			copyMapAToB(map,backUpMap);
			if(type == 0){
				fill(dir,camera.x,camera.y);
			} else if (type == 1){
				fill(dir,camera.x,camera.y);
				fill(dir+2,camera.x,camera.y);
			} else if (type == 2){
				fill(dir,camera.x,camera.y);
				fill(dir+3,camera.x,camera.y);
			} else if (type == 3){
				fill(dir,camera.x,camera.y);
				fill(dir+2,camera.x,camera.y);
				fill(dir+3,camera.x,camera.y);
			} else if (type == 4){
				fill(dir,camera.x,camera.y);
				fill(dir+1,camera.x,camera.y);
				fill(dir+2,camera.x,camera.y);
				fill(dir+3,camera.x,camera.y);
			}

			dfs(index + 1);
			copyMapAToB(backUpMap,map);
		}

	}

	private static void fill(int dir, int x, int y) {
		dir = dir % 4;
		if(dir == 0){
			for(int j = y; j < M; j++){
				if(map[x][j] == 6) break;
				map[x][j] = 2;
			}
		} else if (dir == 1){
			for(int i = x; i < N; i++){
				if(map[i][y] == 6) break;
				map[i][y] = 2;
			}
		} else if (dir == 2){
			for(int j = y; j >= 0; j--){
				if(map[x][j] == 6) break;
				map[x][j] = 2;
			}
		} else if (dir == 3){
			for(int i = x; i >= 0; i--){
				if(map[i][y] == 6) break;
				map[i][y] = 2;
			}
		}
	}

	private static void copyMapAToB(int[][] src, int[][] des) {
		for(int i = 0 ; i < N; i++){
			for(int j = 0 ; j < M; j++){
				des[i][j] = src[i][j];
			}
		}
	}

	private static class CCTV {
		int x,y,type;

		public CCTV(int x, int y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}
}