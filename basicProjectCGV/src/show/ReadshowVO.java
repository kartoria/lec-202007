package show;

public class ReadshowVO {
	private int show_id;
	private String show_start_time;
	private String show_delete;
	private String movie_name;
	private String cinema_name;
	private String show_date;
	
	public String getShow_date() {
		return show_date;
	}
	public void setShow_date(String show_date) {
		this.show_date = show_date;
	}
	
	public String getCinema_name() {
		return cinema_name;
	}
	public void setCinema_name(String cinema_name) {
		this.cinema_name = cinema_name;
	}
	
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	
	public String getShow_delete() {
		return show_delete;
	}
	public void setShow_delete(String show_delete) {
		this.show_delete = show_delete;
	}
	
	public String getShow_start_time() {
		return show_start_time;
	}
	public void setShow_start_time(String show_start_time) {
		this.show_start_time = show_start_time;
	}
	
	public int getShow_id() {
		return show_id;
	}
	public void setShow_id(int show_id) {
		this.show_id = show_id;
	}

	

}
