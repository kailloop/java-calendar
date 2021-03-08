package study;

public class Plan {
	
	private String date;
	private String content;
	
	
	public Plan() {
		// TODO Auto-generated constructor stub
	}

	public Plan(String date, String content) {
		super();
		this.date = date;
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Plan [date=" + date + ", content=" + content + "]";
	}
	
	public String saveString() {
		String ret = "";
		ret += date.toString() + content;
		return ret;
	}
	

}
