package SWExpertAcademy;

import java.util.Scanner;

public class N_2112 {
	static int result;
	static int K;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int D = sc.nextInt();
			int W = sc.nextInt();
			K = sc.nextInt();
			int[][] arr = new int[D][W];
			result = Integer.MAX_VALUE;
			for(int i=0;i<arr.length;i++) {
				for(int j=0;j<arr[0].length;j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			
			if(K == 1) { // K가 1이면 검사할 필요 x
				result = 0;
			}else {
				dfs(0,0,arr);				
			}
			System.out.println("#"+test_case+" "+result);
		}
	}
	
	public static void dfs(int index,int count,int[][] arr) {
		int[] temp = new int[arr[0].length];
		if(result <= count) return; // 최소값 보다 크거나 같으면 바로 리턴 더 계산할 필요 없으므
		if(index >= arr.length) {
			if(checkFilm(arr)) { // 성능 검사 통과
				result = Math.min(result, count);
			}
			return;
		}
		
		dfs(index+1,count,arr); // 약물 투여 x
		
		for(int j=0;j<arr[0].length;j++) { // 임시 저장
			temp[j] = arr[index][j];
		}

		for(int j = 0;j<arr[0].length;j++) {
			arr[index][j]= 0;
		}
		dfs(index+1,count+1,arr); // 약물 투여 A
		
		for(int j = 0;j<arr[0].length;j++) {
			arr[index][j]= 1;
		}
		dfs(index+1,count+1,arr); // 약물 투여 B
		
		for(int j=0;j<arr[0].length;j++) { // 복구하기
			arr[index][j] = temp[j];
		}
		
	}

	public static boolean checkFilm(int[][] arr) {
		for(int j=0;j<arr[0].length;j++) {	
			int feature = arr[0][j];
			int count=1;
			boolean check = false;
			for(int i=1;i<arr.length;i++) {
				if(arr[i][j] != feature) { // 다르면 카운트 초기화, 특성 바꿔주기
					count=1;
					feature = arr[i][j];
					continue;
				}
				count++;
				if(count == K) { // K개면 바로 리턴하도록
					check = true;
					break;
				}
			}
			if(!check) return false; // 한 열이라도 통과 못하면 바로 false리턴
		}
		return true;
	}
}
