package com.kh.project.mini1;

public class Run {

	public static void main(String[] args) {

		DB db = new DB();
		Vaccine vaccine = new Vaccine();
		FileManager manager = new FileManager();
		
		manager.addFile("safe.txt", "user", "이 파일을 안전합니다!");
		manager.addFile("danger.exe", "해커", "이 안에는 malware가 있습니다.");
		manager.addFile("my_project.java", "user", "trojan 패턴이 들어있지만 사용자가 허가한 파일입니다.");

		Menu menu = new Menu(db, vaccine, manager);
		
		menu.MenuUI();
		
	}
	
}