package util;
// TODO: Auto-generated Javadoc

/**
 * ���԰���.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class MessageBoardClass {
	
	/** ����������. */
	private String name;
	
	/** ����. */
	private String topic;
	
	/** ��������. */
	private String contact;
	
	/**
	 * Get the ����������.
	 *
	 * @return ��������������
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the ����������.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the ����.
	 *
	 * @return ����
	 */
	public String getTopic() {
		return topic;
	}
	
	/**
	 * Sets the ����.
	 *
	 * @param topic the new topic
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	/**
	 * Gets the ��������.
	 *
	 * @return the ��������
	 */
	public String getContact() {
		return contact;
	}
	
	/**
	 * Sets the ��������.
	 *
	 * @param contact the ��������
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

