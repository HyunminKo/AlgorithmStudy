package SWExpertAcademy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N_5650 {
	static int[][] arr;
	static int result;
	static String[] wormHole;
	static Queue<DOT> queue;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			arr = new int[N][N];
			queue = new LinkedList<>();
			wormHole = new String[10]; // 최대 5쌍
			result = 0;
			
			for (int i = 0; i < N; i++) { // 배열 생성및 구분
				for (int j = 0; j < N; j++) {
					arr[i][j] = sc.nextInt();
					if(arr[i][j] == 0) { // 핀볼 놓을 수 있는 위치
						queue.add(new DOT(i,j,0));
					}else if(arr[i][j] > 5) { // 웜홀이면
						String str = i+","+j;
						if(wormHole[arr[i][j] - 1] == null) {
							wormHole[arr[i][j] - 1] = str;	
						}else {
							wormHole[arr[i][j] - 6] = str;		
						}
					}
				}
			}
			selectFinbol();
			System.out.println("#"+test_case+" "+result);
		}
	}
	
	public static void selectFinbol() {
		while(!queue.isEmpty()) { // 위치 선정
			DOT d = queue.poll(); 
			
			for(int i =0;i<4;i++) { // 방향 선정
				moveFinbol(d.x,d.y,d.x,d.y,i);
			}
		}
	}
	
	//웜홀 반복 현상 다시 짜야 함
	public static void moveFinbol(int sx,int sy,int x,int y,int dir) { 
		boolean check = false; // 처음 경우 체크
		int score = 0;
		String[] temp;
		DOT d = null;
		while(!check || ((!(sx == x && sy == y)) && arr[x][y] != -1)) { // 처음 위치로 혹은 블랙홀이면 끝냄
			check = true;	
			int tx = x;
			int ty = y;
			
			if(arr[x][y] > 5) { // 웜홀이면
				if(wormHole[arr[x][y]-1].equals(x+","+y)) { // 지금 웜홀의 반대쪽이 아니면 반대쪽 웜홀로 고고
					temp = wormHole[arr[x][y]-6].split(",");
				}else { // 지금 웜홀이 반대쪽이면
					temp = wormHole[arr[x][y]-1].split(",");
				}
				x = Integer.parseInt(temp[0]);
				y = Integer.parseInt(temp[1]);
				tx = x;
				ty = y;
			}
			
			if(dir == 0) { // 상
				if(arr[x][y] != 0 && arr[x][y] < 6) { // 블럭이면
					d = meetBlock(x, y,dir);
					dir = d.dir;
					x = d.x;
					y = d.y;
					score++;
				}else  { // 빈공간 이면
					x = x - 1;
				}
			}else if(dir == 1) { // 하
				if(arr[x][y] != 0 && arr[x][y] < 6) { // 블럭이면
					d = meetBlock(x, y,dir);
					dir = d.dir;
					x = d.x;
					y = d.y;
					score++;
				}else { // 빈공간 이면
					x = x + 1;
				}
			}else if(dir == 2) { // 좌
				if(arr[x][y] != 0 && arr[x][y] < 6) { // 블럭이면
					d = meetBlock(x, y,dir);
					dir = d.dir;
					x = d.x;
					y = d.y;
					score++;
				}else { // 빈공간 이면
					y = y - 1;
				}
			}else { // 우
				if(arr[x][y] != 0 && arr[x][y] < 6) { // 블럭이면
					d = meetBlock(x, y,dir);
					dir = d.dir;
					x = d.x;
					y = d.y;
					score++;
				}else { // 빈공간 이면
					y = y + 1;
				}
			}
			
			if(x < 0 || y < 0 || x >= arr.length || y >= arr[0].length) { // 벽이면 방향 반대로, 위치 원래대로, 점수 증가
				if(dir == 0 || dir == 2) {
					dir = dir + 1;
				}else if(dir == 1 || dir == 3) {
					dir = dir - 1;
				}
				score++;
				x = tx;
				y = ty;
			}
		}
		result = Math.max(score, result);
	}

	public static DOT meetBlock(int x, int y,int dir) { // 블럭 만나면 방향 바꿔주기
		if(dir == 0) { // 상
			if(arr[x][y] == 2) { // 대각선 부분
				return new DOT(x,y+1,3);
			}else if(arr[x][y] == 3){
				return new DOT(x,y-1,2);
			}else {
				return new DOT(x+1,y,1);
			}
		}else if(dir == 1) { // 하
			if(arr[x][y] == 1) { // 대각선 부분
				return new DOT(x,y+1,3);
			}else if(arr[x][y] == 4){
				return new DOT(x,y-1,2);
			}else {
				return new DOT(x-1,y,0);
			}
		}else if(dir == 2) { // 좌
			if(arr[x][y] == 1) { // 대각선 부분
				return new DOT(x-1,y,0);
			}else if(arr[x][y] == 2){
				return new DOT(x+1,y,1);
			}else {
				return new DOT(x,y+1,3);
			}
		}else{ // 우
			if(arr[x][y] == 3) { // 대각선 부분
				return new DOT(x+1,y,1);
			}else if(arr[x][y] == 4){
				return new DOT(x-1,y,0);
			}else {
				return new DOT(x,y-1,2);
			}
		}

	}
	
	
	public static class DOT{
		int x,y,dir;
		
		public DOT(int x,int y,int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}

