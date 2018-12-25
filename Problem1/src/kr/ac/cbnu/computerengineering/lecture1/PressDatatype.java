package kr.ac.cbnu.computerengineering.lecture1;
import java.util.Date;

public class PressDatatype {
	private String title;
	private String contents;
	private Date date;
	
	public PressDatatype() {}
	public PressDatatype(Date date,String title,String contents) {
		this.title = title;
		this.contents = contents;
		this.date = date;
	}
	
	public String gettitle() {
		return this.title;
	}
	public String getcontents() {
		return this.contents;
	}
	public void settitle(String title) {
		this.title = title;
	}
	public void setcontents(String contents) {
		this.contents = contents;
	}
	public Date getdate() {
		return this.date;
	}
	public void setdate(Date date) {
		this.date = date;
	}
}
