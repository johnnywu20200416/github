package tw.leonchen.jspproject._hw.javaTest;

import java.util.Scanner;

public class TestWhoWin_java {

	public static void main(String[] args) {
		int again = 1;
		int p, c;
		
		Scanner scn = new Scanner(System.in);
		
		while(again==1) {
			System.out.println("input 2 numbers:");
			System.out.print("number p = ");
			p = scn.nextInt();
			System.out.print("number c = ");
			c = scn.nextInt();
			
			System.out.println();
			
			System.out.println("winner is: \n");
			winner(p, c);
			
			System.out.println("----------------------------");
			
			System.out.println("Again?");
			again = scn.nextInt();
			
			System.out.println();
		}
		
		scn.close();
	}
	
	
	public static void winner(int p, int c) {
		if(p>21 || (p<=21 && c<=21 && p<c)) {
			System.out.println("Player lose");
		}
		else if(p<=21 && (c>21 || p>c)) {
			System.out.println("Player win");
		}
		else if(p<=21 && c<=21 && p==c) {
			System.out.println("Tied");
		}
		
		//原始想法
//		if(p>21) {
//			System.out.println("Player lose");
//		}else {
//			if(c>21) {
//				System.out.println("Player win");
//			}else {
//				if(p>c) {
//					System.out.println("Player win");
//				}else if(p==c) {
//					System.out.println("Tied");
//				}else {
//					System.out.println("Player lose");
//				}
//			}
//		}
	}


}
