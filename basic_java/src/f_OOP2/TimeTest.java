package f_OOP2;

public class TimeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimeVO time = new TimeVO();
		//40시간 -> 16시
		//200분  -> 19:20
		//10000초 -> 22:6:40
		time.setHour(16);
		time.setMin(200);
		time.setSec(10000);
		time.getTime();
		// 22시        -> 22
		// 100분      -> 23:40
		// 10000초 -> 2:26:40
		time.setHour(22);
		time.setMin(100);
		time.setSec(10000);
		time.getTime();
	}
}


class TimeVO{
	private int hour, min, sec; // 시 분 초
	
	void setHour(int hour){
		this.hour = hour%24;
	}
	
	void setMin(int min){
		setHour(hour += min/60);
		this.min = min%60;
	}
	
	void setSec(int sec){
		setMin(min += sec/60);
		this.sec = sec%60;
	}
	
	void getTime(){
			System.out.println("지금 시각은 " + hour + "시 " + min + "분 " + sec + "초입니다.");						
	}
		
}
