package board.bean;

import java.util.Date;

import lombok.Data;

@Data
public class BoardDTO {

	private int seq;
	private String name;
	private String subject;
	private String content;
	private Date logtime;
	
	
//	public int getSeq() {
//		return seq;
//	}
//	public void setSeq(int seq) {
//		this.seq = seq;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getSubject() {
//		return subject;
//	}
//	public void setSubject(String subject) {
//		this.subject = subject;
//	}
//	public String getContent() {
//		return content;
//	}
//	public void setContent(String content) {
//		this.content = content;
//	}
//	public Date getLogtime() {
//		return logtime;
//	}
//	public void setLogtime(Date logtime) {
//		this.logtime = logtime;
//	}

}

