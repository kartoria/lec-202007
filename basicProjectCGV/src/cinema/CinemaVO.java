package cinema;

public class CinemaVO {
	private String cinema_name;
	private int cinema_id;
	private int seat_number;
	private int cinema_price;
	private int isDelete;
	
	
	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public int getCinema_id() {
		return cinema_id;
	}
	
	public void setCinema_id(int cinema_id) {
		this.cinema_id = cinema_id;
	}
	
	public int getSeat_number() {
		return seat_number;
	}
	
	public void setSeat_number(int seat_number) {
		this.seat_number = seat_number;
	}
	
	public String getCinema_name() {
		return cinema_name;
	}
	
	public void setCinema_name(String cinema_name) {
		this.cinema_name = cinema_name;
	}
	
	public int getCinema_price() {
		return cinema_price;
	}
	
	public void setCinema_price(int cinema_price) {
		this.cinema_price = cinema_price;
	}
	
	
}
