package pojo;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "sc_notify")
public class Notify extends Base{
	private String content;

	@Column(name = "rec_id")
	private Integer recId;
	
	private String title;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getRecId() {
		return recId;
	}

	public void setRecId(Integer recId) {
		this.recId = recId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
