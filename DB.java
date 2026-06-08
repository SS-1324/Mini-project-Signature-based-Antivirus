package com.kh.project.mini1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DB {
	
	//필드
	//블랙리스트 : 컬렉션 객체 + 기본으로 들어있는 패턴
	private List<String> blackList = new ArrayList<>(Arrays.asList("malware", "trojan", "ransomware", "worm", "spyware", "virus", "adware"));
	//화이트리스트 : 허용할 파일 목록
	//원래 개발자 입장에서는 "악성 패턴과 유사하게 생겼지만 필요한 패턴" 등을 화이트리스트로 빼지만, 이 프로젝트에서는 '백신은 악성 파일로 의심된다고 했지만 사용자가 괜찮다고 한 파일'을 저장하는 공간으로 사용한다.
	private List<String> whiteList = new ArrayList<>(Arrays.asList("my_project.java"));
	
	//생성자
	public DB() {
		super();
	}
	
	//매서드
	//블랙리스트에 패턴 추가
	public void addBlackList(String pattern) {
		if(!blackList.contains(pattern)) {
			blackList.add(pattern);
			System.out.println("신규 악성 패턴이 등록되었습니다.");
			savePatternToFile();
		} else {
			System.out.println("이미 등록되어있는 패턴입니다.");
		}
	}
	//화이트리스트에 파일 추가
	public void addWhiteList(String fileName) {
		if(!whiteList.contains(fileName)) {
			whiteList.add(fileName);
			System.out.println(fileName + "파일이 허용되었습니다.");
		} 		
	}
	
	//악성 파일 검사
	public boolean isMalware(VirtualFile file) {
	    // 블랙리스트에 있는 파일 검사
	    String content = file.getContent().toLowerCase(); //대소문자 구분 없이 검사되도록 toLowerCase 사용
	    for (String pattern : blackList) { //패턴이 블랙리스트에 있는지 한 줄씩 검사
	        if (content.contains(pattern.toLowerCase())) {
	            return true; //블랙리스트에 패턴이 있을 때만 true = 위험 판정
	        }
	    }
	    return false;
	}
	
	// blacklist.txt에서 패턴을 읽어와서 DB에 추가
	public void loadPatternFromFile() {
		//파일에서 한 줄씩 읽어오기
		try (BufferedReader br = new BufferedReader(new FileReader("blacklist.txt"))) {
			String line;
			System.out.println("DB를 불러옵니다.");
			//파일에 남은 글자가 없을 때까지 반복해서 blacklist 에 담는다.
			while ((line = br.readLine()) != null) {
				if(!blackList.contains(line)) {
					blackList.add(line); 
				}
			}
			System.out.println("DB를 불러왔습니다.");
		} catch (IOException e) {
			System.out.println("blacklist.txt이 없습니다. 기본 내장 DB로 실행합니다.");
		}
	}
	
	//사용자가 추가한 패턴 기억하기
	public void savePatternToFile() {
	    // FileWriter에 "blacklist.txt"를 넣어서 기존 파일 내용을 지우고 새로 저장하기
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter("blacklist.txt"))) {
	        // 블랙리스트에 있는 모든 패턴을 꺼내기
	        for (String pattern : blackList) {
	            bw.write(pattern);// 메모장에 넣기
	            bw.newLine();// 줄바꾸기
	        }
	    } catch (IOException e) {
	        System.out.println("패턴을 파일에 저장하지 못했습니다.");
	    }
	}
	
	// getter : 외부에서 blacklist/white 목록을 조회할 수 있게 하기
	public List<String> getBlackList() { 
		return blackList; 
	}
	public List<String> getWhiteList() { 
		return whiteList; 
	}

}
