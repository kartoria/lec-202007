package reserve;

public class ReserveVO {
	private int res_no;
	private int cinema_id;
	private String cinema_name;
	private String mem_id;
	private int seat_no;
	private String show_start;
	private int show_id;
	private int cinema_price;
	private int reserve_delete;
	
	public int getRes_no() {
		return res_no;
	}
	
	public void setRes_no(int res_no) {
		this.res_no = res_no;
	}
	
	public int getCinema_id() {
		return cinema_id;
	}
	
	public void setCinema_id(int cinema_id) {
		this.cinema_id = cinema_id;
	}
	
	public String getCinema_name() {
		return cinema_name;
	}
	
	public void setCinema_name(String cinema_name) {
		this.cinema_name = cinema_name;
	}
	
	public String getMem_id() {
		return mem_id;
	}
	
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	public int getSeat_no() {
		return seat_no;
	}
	
	public void setSeat_no(int seat_no) {
		this.seat_no = seat_no;
	}
	
	public String getShow_start() {
		return show_start;
	}
	
	public void setShow_start(String show_start) {
		this.show_start = show_start;
	}
	
	public int getShow_id() {
		return show_id;
	}
	
	public void setShow_id(int show_id) {
		this.show_id = show_id;
	}
	
	public int getCinema_price() {
		return cinema_price;
	}
	
	public void setCinema_price(int cinema_price) {
		this.cinema_price = cinema_price;
	}
	
	public int getReserve_delete() {
		return reserve_delete;
	}
	
	public void setReserve_delete(int reserve_delete) {
		this.reserve_delete = reserve_delete;
	}
}
