package BaekJoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class N_14502 {
	static int[][] arr;
	static int dot[][] = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }; // �����¿�
	static List<DOT> virus;
	static List<DOT> list;
	static int result = Integer.MIN_VALUE;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		arr = new int[N][M];
		list = new ArrayList<>();
		virus = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j] == 0) list.add(new DOT(i,j));
				else if(arr[i][j] == 2) virus.add(new DOT(i,j));
			}
		}
		setWall(0,0,"");
		System.out.println(result);
	}
	
	public static void setWall(int index,int start,String str) { // ����� �� ���� �� ����� 
		if(index >= 3) { // �� 3������ �ǹǷ�
			String[] temp = str.split(",");
			int[][] visit = new int[arr.length][arr[0].length];
			for(int i=0;i<arr.length;i++) { // �ӽ� �迭�� ����
				System.arraycopy(arr[i], 0, visit[i], 0, arr[i].length);
			}
			
			for(int i=0;i<temp.length;i++) { // ���� ����� ���� ���� �� 3�� �����
				int list_index = Integer.parseInt(temp[i]);
				DOT d = list.get(list_index);
				visit[d.x][d.y] = 1; // �� �����
			}
			
			for(DOT d : virus) { // ���̷��� �۶߸���
				spreadVirus(d.x,d.y,visit);
			}
			result = Math.max(result, getResult(visit)); // ����� �� ū ������
			return;
		}
		
		for(int i = start;i<list.size();i++) { // ��ͷ� string�� ����� �� �����
			setWall(index+1,i+1,str+i+",");
		}
	}
	
	public static void spreadVirus(int x,int y,int[][] visit) { // dfs�� ���̷��� �۶߸���
		for(int i=0;i<4;i++) {
			int dx = x + dot[i][0];
			int dy = y + dot[i][1];
			
			if(dx < 0 || dy < 0 || dx >= arr.length || dy >= arr[0].length || visit[dx][dy] != 0) continue;
			
			visit[dx][dy] = 2;
			spreadVirus(dx,dy,visit);
		}			

		
	}
	
	public static int getResult(int[][] visit) { // ���� ���� ���� ����
		int count = 0;
		for(int i=0;i<visit.length;i++) {
			for(int j=0;j<visit[0].length;j++) {
				if(visit[i][j] == 0) count++;
			}
		}
		return count;
	}

	public static class DOT{
		int x,y;
		
		public DOT(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
}


