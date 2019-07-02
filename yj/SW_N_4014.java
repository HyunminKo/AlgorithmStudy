package SWExpertAcademy;

import java.util.Scanner;

public class N_4014 {
	static int[][] arr;
	static int X;
	static int result;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			result = 0;
			int N = sc.nextInt(); // 한변의 크기
			X = sc.nextInt(); // 경사로의 길이
			arr = new int[N][N];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			checkRow();
			checkCol();
			System.out.println("#"+test_case+" "+result);
		}
	}
	
	public static void checkRow() {
		for(int i=0;i<arr.length;i++) {
			boolean check = true;
			int num = arr[i][0];
			int count = 1;
			int use = 0;
			for(int j=1;j<arr[0].length;j++) {
				if(arr[i][j]-num == -1) { // 3 >> 2
					int temp = 0;
					num = arr[i][j];
					count = 1;
					for(int k=j;k<arr[0].length;k++) {
						if(arr[i][k] != num) break; 
						temp++;
					}
					if(temp < X) {
						check = false;
						break;
					}else {
						use = num;
					}
				}else if(arr[i][j]-num == 1) { // 2 >> 3
					if(count < X || (num == use && count - X < X)) {
						check = false;
						break;
					}
					num = arr[i][j];
					count = 1;
				}else if(arr[i][j] == num){ // 2 == 2
					count++;					
				}else if(Math.abs(arr[i][j] - num) != 1) {
					check = false;
					break;
				}
			}
			if(check) result++;
		}
	}
	
	public static void checkCol() {
		for(int i=0;i<arr[0].length;i++) {
			boolean check = true;
			int num = arr[0][i];
			int count = 1;
			int use = 0;
			for(int j=1;j<arr.length;j++) {
				if(arr[j][i]-num == -1) { // 3 >> 2
					int temp = 0;
					num = arr[j][i];
					count = 1;
					for(int k=j;k<arr.length;k++) {
						if(arr[k][i] != num) break; 
						temp++;
					}
					if(temp < X) {
						check = false;
						break;
					}else {
						use = num;
					}
				}else if(arr[j][i]-num == 1) { // 2 >> 3
					if(count < X || (num == use && count - X < X)) {
						check = false;
						break;
					}
					num = arr[j][i];
					count = 1;
				}else if(arr[j][i] == num){ // 2 == 2
					count++;					
				}else if(Math.abs(arr[j][i] - num) != 1) {
					check = false;
					break;
				}
			}
			if(check) result++;
			}
		}
	

}
