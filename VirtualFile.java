package com.kh.project.mini1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VirtualFile {
	
	//필드
	private String fileName; //파일 이름
	private String content; //파일 내용
	private String author; //파일을 생성한 사람
	private String status; //파일 상태 - 안전, 격리, 허용-
	private LocalDateTime time = LocalDateTime.now(); //파일 생성 시간
	
	//생성자 : 이름, 생성한 사람, 내용을 받아 파일 생성
	public VirtualFile(String fileName, String author, String content) {
		super();
		this.fileName = fileName;
		this.content = content;
		this.author = author;
		this.status = "안전";
	}
	
	//getter&setter
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	//생성한 사람은 수정 X - getter 만
	public String getAuthor() {
		return author;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	//생성한 시간도 수정 X - getter 만
	public LocalDateTime getTime() {
		return time;
	}
	
	//매서드 : 파일 목록 불러올 때 반환할 내용([ 파일이름 ], 생성한사람, 생성시간, 상태)
	public String fileForList() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");
		String formattedTime = time.format(formatter);
		
		return "[ " + fileName + " ] " + ", " + author + ", " + formattedTime + ", " + status;
		
	}
	
}
