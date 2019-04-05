package SWExpertAcademy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class N_5658 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			List<String> StringList = new ArrayList<String>();
			List<Long> numList = new ArrayList<Long>();
			int N = sc.nextInt();
			int k = sc.nextInt();
			String str = sc.next();
			char[] arr = new char[N];
			for(int i=0;i<str.length();i++) {
				arr[i] = str.charAt(i);
			}
			int count = N/4;
			while(count > 0) {
				for(int i=0;i<arr.length;i=i+(N/4)) {
					String s = "";
					for(int j =i;j<i+(N/4);j++) {
						s += arr[j];
					}
					StringList.add(s);
				}
				// right Shift
				char temp = arr[arr.length-1];
				for(int i=arr.length-1;i>0;i--) {
					arr[i] = arr[i-1];
				}
				arr[0] = temp;
				count--;
			}
			// 중복제거
			HashSet<String> hs = new HashSet<String>(StringList);
			StringList = new ArrayList<String>(hs);
            
            for(int i=0;i<StringList.size();i++) {
            	numList.add(Long.parseLong(StringList.get(i),16));
            }
            
            Collections.sort(numList);
            Collections.reverse(numList);
            System.out.println("#"+test_case+" "+numList.get(k-1));
		}
	}

}
