package show;

public class DayShowListVO {
	private String moviename;
	private String cinemaname;
	private String showtime;
	private int seatnum;
	
	public int getSeatnum() {
		return seatnum;
	}
	public void setSeatnum(int seatnum) {
		this.seatnum = seatnum;
	}
	
	public String getShowtime() {
		return showtime;
	}
	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}
	
	public String getCinemaname() {
		return cinemaname;
	}
	public void setCinemaname(String cinemaname) {
		this.cinemaname = cinemaname;
	}
	
	public String getMoviename() {
		return moviename;
	}
	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}
}
