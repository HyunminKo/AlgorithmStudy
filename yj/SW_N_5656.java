package SWExpertAcademy;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class N_5656 {
	static int max_case;
	static int N;
	static int[][] arr;
	static int[][] temp;
	static int result;
	static int dot[][] = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }; // 상하좌우
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{	
			N = sc.nextInt();
			int W = sc.nextInt();
			int H = sc.nextInt();
			result = Integer.MAX_VALUE;
			
			temp= new int[H][W]; // 원본 배열
			arr= new int[H][W];
			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					arr[i][j] = sc.nextInt();
					temp[i][j] = arr[i][j];
				}
			}
			
			go(0,""); // 경우의 수 구하기
			System.out.println("#"+test_case+" "+result);
		}
	}
	
	public static void removeBrick(String caseIndex) {
		String[] s = caseIndex.split(",");
		for(int i=0;i<s.length;i++) {
			int index = Integer.parseInt(s[i]);
			for(int j=0;j<arr.length;j++) {
				if(arr[j][index] == 1) {
					arr[j][index] = 0;
					break;
				}else if(arr[j][index] > 1) {
					dfs(j,index,arr[j][index]); // 영향 있는거 모두 제거하고
					break;
				}
			}
			arr = fitArray(); // 칸 맞춰주기
		}
		result = Math.min(result, getArrayNum());
		initArray();
	}
	
	public static void dfs(int j,int i,int brickNum) {
		arr[j][i] = 0; 
		
		for(int k=0;k<4;k++) {
			for(int l=1;l<brickNum;l++) {
				int dx = j + dot[k][0]*l;
				int dy = i + dot[k][1]*l;
				
				if(dx < 0 || dy < 0 || dx >= arr.length || dy >= arr[0].length) continue;
				
				if(arr[dx][dy] == 1) {
					arr[dx][dy] = 0;
				}else if(arr[dx][dy] > 1) {
					dfs(dx,dy,arr[dx][dy]);
				}				
			}
		}
	}
	
	public static int[][] fitArray() { 
		int[][] newArr = new int[arr.length][arr[0].length];
		for(int j=0;j<arr[0].length;j++) {
			List<Integer> dlist = new ArrayList<Integer>();
			for(int i=arr.length-1;i>=0;i--) {
				if(arr[i][j] > 0) {
					dlist.add(i);
				}
			}
			
			int k = arr.length-1;
			int index = 0;
			while(index < dlist.size()) {
				newArr[k--][j] = arr[dlist.get(index++)][j];
			}
		}
		return newArr;
	}
	
	public static void go(int count,String str) {
		if(count >= N){
			removeBrick(str);
            return;
        }
        for(int i = 0 ; i < arr[0].length; i++){
            String temp = str + i +",";
            go(count+1,temp);
        }
	}
	
	public static int getArrayNum() {
		int n = 0;
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[0].length;j++) {
				if(arr[i][j] != 0) n++;
			}
		}
		return n;
	}
	
	public static void initArray() {
		for(int i=0;i<temp.length;i++) {
			for(int j=0;j<temp[0].length;j++) {
				arr[i][j] = temp[i][j];
			}
		}
	}
}
