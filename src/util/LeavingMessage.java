package util;
/**
 * 留言板实体类
 * 留言人id，留言话题，留言内容
 * @author 汐子
 *
 */
public class LeavingMessage {
	private String name;
	private String topic;
	private String contact;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public LeavingMessage() {
	}
	public LeavingMessage(String name, String topic, String contact) {
		this.name = name;
		this.topic = topic;
		this.contact = contact;
	}
}

