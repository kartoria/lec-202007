package notice;

public class NoticeVO {

	private String notice_no;
	private String notice_title;
	private String notice_commit;
	private String admin_id;

	public String getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(String notice_no) {
		this.notice_no = notice_no;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_commit() {
		return notice_commit;
	}

	public void setNotice_commit(String notice_commit) {
		this.notice_commit = notice_commit;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

}
