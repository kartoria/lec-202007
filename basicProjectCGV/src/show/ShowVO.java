package show;

public class ShowVO {
	
	private int show_id;
	private String show_start_time;
	private int movie_id;
	private int cinema_id;
	
	public int getShow_id() {
		return show_id;
	}
	public void setShow_id(int show_id) {
		this.show_id = show_id;
	}
	
	public String getShow_start_time() {
		return show_start_time;
	}
	public void setShow_start_time(String show_start_time) {
		this.show_start_time = show_start_time;
	}
	
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	
	public int getCinema_id() {
		return cinema_id;
	}
	public void setCinema_id(int cinema_id) {
		this.cinema_id = cinema_id;
	}

}
