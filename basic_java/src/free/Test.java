package free;

import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//			  1  2  3  4  5  6  7  8  9  10
		// A  □  ■  ■  ■  ■  ■  ■  ■  ■  ■  
		// B  ■  ■  ■  ■  ■  ■  ■  ■  ■  ■
		// C  □  □  ■  ■  ■  ■  ■  ■  ■  ■ 
		// D  ■  ■  ■  ■  ■  ■  ■  ■  ■  ■
		// E  ■  ■  ■  ■  □  ■  ■  ■  ■  ■  
		// F  ■  ■  ■  ■  ■  ■  ■  ■  ■  ■  
		// G  ■  ■  ■  ■  ■  ■  ■  ■  ■  ■  
		// H  ■  ■  ■  ■  ■  ■  ■  ■  ■  ■  
		// I  ■  ■  ■  ■  ■  ■  ■  ■  ■  ■  
		// J  ■  ■  ■  ■  ■  ■  ■  ■  ■  □  
		// K  ■  ■  ■  ■  ■  ■  ■  ■  ■  ■  
		
		
		//열 A B C D E F G H I J K
		int colume = 11;
	
		//행 1 2 3 4 5 6 7 8 9 10
		int raw = 10;
		
		String str[] = new String[5];
		str[0] = "A1";
		str[1] = "C1";
		str[2] = "C2";
		str[3] = "E5";
		str[4] = "J10";
		//좌석이 A1 C1 C2 E5 J10 에 예약이 되어 있다 쳐봄
		
		
		char colticket;
		int rawticket;
		
		for(int i = 0; i<str.length; i++){
			if(str[i].length() == 2){
				colticket = str[i].charAt(0);
				rawticket = str[i].charAt(1) - 48;
			}else{
				colticket = str[i].charAt(0);
				rawticket = (str[i].charAt(1) - 48)*10 + (str[i].charAt(2) - 48);
			}			
			System.out.print(colticket);
			System.out.println(rawticket);
		}
		
//////		i가 0 1 2 3 4 5 6 7 8 9 까지 올라감
////		col
//		for(char i = 0; i<colume; i++){
//			for(int j = ; j<raw; j++){
//				if(col = '')
//							
//			}
//			col++;
//		}
		
	}
}
