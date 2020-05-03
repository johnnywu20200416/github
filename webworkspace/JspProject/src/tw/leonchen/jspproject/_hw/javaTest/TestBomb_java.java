package tw.leonchen.jspproject._hw.javaTest;

public class TestBomb_java {

//	private static int hasA = 0;

	public static void main(String[] args) {
		int sum = 0;
		
		int[] testA = {0, 13, 1, 2, 3, 3};
		int[] testB = {0, 13, 7, 11, 8};
		
		int[] testA0 = {0, 13};
		int[] testA1 = {0, 13, 1};
		int[] testA2 = {0, 13, 1, 2};
		int[] testA3 = {0, 13, 1, 2, 3};
		int[] testA4 = {0, 13, 1, 2, 3, 3};
		
		int[] testB0 = {0, 13};
		int[] testB1 = {0, 13, 7};
		int[] testB2 = {0, 13, 7, 11};
		int[] testB3 = {0, 13, 7, 11, 8};
		
		
//		sumCardValue2(testA);
		System.out.println("testA0------------------");
		sum = sumCardValue2(sum, testA0);
		System.out.println("testA1------------------");
		sum = sumCardValue2(sum, testA1);
		System.out.println("testA2------------------");
		sum = sumCardValue2(sum, testA2);
		System.out.println("testA3------------------");
		sum = sumCardValue2(sum, testA3);
		System.out.println("testA4------------------");
		sum = sumCardValue2(sum, testA4);
		
		
		System.out.println("sum=" + sum);
		
		System.out.println("------------------------------------------------");
		
//		sumCardValue2(testB);
	}

	public static int sumCardValue2(int sum, int[] test) {
		int cardValue;
		int card;

		if(test.length<=2) {
			for(int i=0;i<2;i++) {
//				if(test[i]%13==0) {
//					hasA = 1;
//				}
				sum = justSumCardValue(sum, test[i]);
			}
		}else {
			sum = justSumCardValue(sum, test[test.length-1]);
		}
		
//		if(sum>21 && hasA==1) {
//			sum = sum -10;
//			hasA = 0;
//		}
		
		if(sum>21) {
			for(int i=0;i<2;i++) {
				if(test[i]%13==0) {
					sum = sum - 10;
					break;
				}
			}
		}
		
		return sum;
	}

	public static void sumCardValue(int[] test) {
		int cardValue;
		int card;
		int hasA = 0;
		int sum = 0;
		
		for(int i=0;i<test.length;i++) {
			card = test[i] % 13;
			
			if(card==0) {
				if(21-sum >= 11) {
					card = 11;
					hasA = 1;
				}else {
					card = 1;
				}
				
			}else if(card>9) {
				card = 10;
			}else {
				++card;
			}
			
			sum += card;
			System.out.println("sum" + (i+1) + " = " + sum + "\n");
			
			if(sum>21 && hasA==1) {
				sum = sum - 10;
				hasA = 0;
				System.out.println("sum - 10 =  " + sum + "\n");
			}
			
			System.out.println("sum" + (i+1) + " over 21 = " + sum + "\n");
		}
		
	}

	public static int justSumCardValue(int sum, int test) {
		int cardValue;
		int card;
//		int hasA = 0;
		
		
			card = test % 13;
			
			if(card==0) {
				if(21-sum >= 11) {
					card = 11;
//					hasA = 1;
				}else {
					card = 1;
				}
				
			}else if(card>9) {
				card = 10;
			}else {
				++card;
			}
			
			sum += card;
			System.out.println("sum first= " + sum + "\n");
			
//			if(sum>21 && hasA==1) {
//				sum = sum - 10;
//				hasA = 0;
//				System.out.println("sum - 10 =  " + sum + "\n");
//			}
			
			System.out.println("sum last= " + sum + "\n");
		
		return sum;
	}

}
