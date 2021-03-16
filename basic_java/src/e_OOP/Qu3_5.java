package e_OOP;

import java.util.Scanner;
/**
 * 3-5 문제 (계산기)
 * @author PC-18
 * @since 2020-08-17
 */
public class Qu3_5 {
	public static void main(String[] args) {
		Calc cc = new Calc();
		Scanner sc = new Scanner(System.in);
		float resultNum = 0;
		int firstNum, secondNum;
		String buho = "";
		boolean firstcal = false;
		
		while(true){
			if(firstcal == false){
				firstcal = true;
				// 입력받는 곳
				System.out.print("정수 a 입력: ");
				firstNum = sc.nextInt();
				System.out.print("부호 입력    : ");
				buho = sc.next();
				
				// 부호가 사칙연산이아니면 종료, c면 초기화
				if( !("+".equals(buho) || "-".equals(buho) 
						|| "*".equals(buho) || "/".equals(buho) 
							|| "c".equals(buho) || "C".equals(buho))){
					System.out.println("연산종료");
					break;
				}else if("c".equals(buho) || "C".equals(buho)){
					firstcal = false;
					System.out.println("초기화 되었습니다.");
				}else{
					System.out.print("정수 b 입력: ");
					secondNum = sc.nextInt();
					
					// 부호별로 계산
					if("+".equals(buho)){
						int addResult = cc.add(firstNum, secondNum);
						System.out.println(firstNum +" + "+ secondNum +" = "+ addResult);
						resultNum = addResult; 
					}else if("-".equals(buho)){
						int subResult = cc.substract(firstNum, secondNum);
						System.out.println(firstNum +" - "+ secondNum +" = "+ subResult);
						resultNum = subResult; 
					}else if("*".equals(buho)){
						int mulResult = cc.multiply(firstNum, secondNum);
						System.out.println(firstNum +" * "+ secondNum +" = "+ mulResult);
						resultNum = mulResult; 
					}else if("/".equals(buho)){
						float divResult = cc.divide(firstNum, secondNum);
						System.out.println(firstNum +" / "+ secondNum +" = "+ divResult);
						resultNum = divResult; 
					}
				}
				
			}else{
				// 입력받는 곳  
				System.out.print("부호 입력    : ");
				buho = sc.next();
				
				if( !("+".equals(buho) || "-".equals(buho)
						|| "*".equals(buho) || "/".equals(buho) 
							|| "c".equals(buho) || "C".equals(buho))){
					System.out.println("연산종료");
					break;
				}else if("c".equals(buho) || "C".equals(buho)){
					firstcal = false;
					System.out.println("초기화 되었습니다.");
				}else{
					System.out.print("정수 b 입력: ");
					secondNum = sc.nextInt();
					
					if("+".equals(buho)){
						float addResult = cc.add2(resultNum, secondNum);
						System.out.println(resultNum +" + "+ secondNum +" = "+ addResult);
						resultNum = addResult; 
					}else if("-".equals(buho)){
						float subResult = cc.substract2(resultNum, secondNum);
						System.out.println(resultNum +" + "+ secondNum +" = "+ subResult);
						resultNum = subResult; 
					}else if("*".equals(buho)){ 
						float mulResult = cc.multiply2(resultNum, secondNum);
						System.out.println(resultNum +" + "+ secondNum +" = "+ mulResult);
						resultNum = mulResult; 
					}else if("/".equals(buho)){
						float divResult = cc.divide2(resultNum, secondNum);
						System.out.println(resultNum +" + "+ secondNum +" = "+ divResult);
						resultNum = divResult; 
					}
	
				}
				
			} //else
		} //while
		
	
	}
}

class Calc {
//	1.
	int add(int a, int b){
		return a+b;
	}
	
//	2.
	int substract(int a, int b){
		return a-b;
	}
	
//	3.
	int multiply(int a, int b){
		return a*b;
	}
	
//	4.
	float divide(int a, int b){
		return (int)((float)a/b*100+0.5)/100f;
	}
	
	
	

	float add2(float a, int b){
		return (float)a+b;
		
	}
	
	float substract2(float a, int b){
		return a-b;
	}
	
	float multiply2(float a, int b){
		return a*b;
	}
	
	float divide2(float a, int b){
		return (int)(a/b*100+0.5)/100f;
	}
	
	
}
