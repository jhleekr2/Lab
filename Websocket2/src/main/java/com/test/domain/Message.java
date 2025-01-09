package com.test.domain;

public class Message {

	private String code; //상태코드
	private String sender; //보내는사람
	private String receiver; //받는사람
	private String content; //대화내용
	private String regdate; //날짜
	
	//기본생성자
	public Message() {}

	public Message(String code, String sender, String receiver, String content, String regdate) {
		super();
		this.code = code;
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "Message [code=" + code + ", sender=" + sender + ", receiver=" + receiver + ", content=" + content
				+ ", regdate=" + regdate + "]";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
}
