package util;
// TODO: Auto-generated Javadoc

/**
 * 留言板类.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class MessageBoardClass {
	
	/** 留言人名字. */
	private String name;
	
	/** 话题. */
	private String topic;
	
	/** 留言内容. */
	private String contact;
	
	/**
	 * Get the 留言人名字.
	 *
	 * @return 返回留言人名字
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the 留言人名字.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the 主题.
	 *
	 * @return 主题
	 */
	public String getTopic() {
		return topic;
	}
	
	/**
	 * Sets the 主题.
	 *
	 * @param topic the new topic
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	/**
	 * Gets the 留言内容.
	 *
	 * @return the 留言内容
	 */
	public String getContact() {
		return contact;
	}
	
	/**
	 * Sets the 留言内容.
	 *
	 * @param contact the 留言内容
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	/**
	 * Instantiates a new leaving message.
	 */
	public MessageBoardClass() {
	}
	
	/**
	 * Instantiates a new leaving message.
	 *
	 * @param name the name
	 * @param topic the topic
	 * @param contact the contact
	 */
	public MessageBoardClass(String name, String topic, String contact) {
		this.name = name;
		this.topic = topic;
		this.contact = contact;
	}
}

