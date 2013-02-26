package edu.toronto.ece1779.ec2.entity;

public class Image {

	private int id;
	private int userId;
	private String key1;
	private String key2;
	private String key3;
	private String key4;
	
	public Image(){
		
	}
	
	public Image(int userId, String key1, String key2, String key3, String key4) {
		this.userId = userId;
		this.key1 = key1;
		this.key2 = key2;
		this.key3 = key3;
		this.key4 = key4;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the key1
	 */
	public String getKey1() {
		return key1;
	}

	/**
	 * @param key1 the key1 to set
	 */
	public void setKey1(String key1) {
		this.key1 = key1;
	}

	/**
	 * @return the key2
	 */
	public String getKey2() {
		return key2;
	}

	/**
	 * @param key2 the key2 to set
	 */
	public void setKey2(String key2) {
		this.key2 = key2;
	}

	/**
	 * @return the key3
	 */
	public String getKey3() {
		return key3;
	}

	/**
	 * @param key3 the key3 to set
	 */
	public void setKey3(String key3) {
		this.key3 = key3;
	}

	/**
	 * @return the key4
	 */
	public String getKey4() {
		return key4;
	}

	/**
	 * @param key4 the key4 to set
	 */
	public void setKey4(String key4) {
		this.key4 = key4;
	}
	
	
	
}
