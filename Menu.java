package com.kh.project.mini1;

import java.util.Scanner;

public class Menu {
	
	// 필드: 스캐너, 데이터베이스, 백신, 파일매니저 불러오기
	private Scanner sc = new Scanner(System.in);
	private DB db;
	private Vaccine vaccine;
	private FileManager manager;
	
	//생성자
	public Menu(DB db, Vaccine vaccine, FileManager manager) {
		this.db = db;
		this.vaccine = vaccine;
		this.manager = manager;
	}
	
	// 메뉴판 메서드
	public void MenuUI() {
		
		System.out.println("\n백신 가동");
		
		while (true) {
			System.out.println("\n======================");
			System.out.println("1. 새 파일 추가하기");
			System.out.println("2. 전체 파일 목록 보기");
			System.out.println("3. 파일 수정하기");
			System.out.println("4. 시스템 백신 스캔 시작");
			System.out.println("5. 감염 파일 격리소로 이동");
			System.out.println("6. 격리소 목록 보기");
			System.out.println("7. 격리소 완전히 비우기");
			System.out.println("8. 텍스트 파일에서 최신 DB 불러오기");
			System.out.println("0. 프로그램 종료");
			System.out.print("번호를 입력하세요 : ");
			
			int n = sc.nextInt();
			sc.nextLine(); // 버퍼 비우기
			
			switch (n) {
			case 1:
				System.out.print("생성할 파일 이름: ");
				String name = sc.nextLine();
				System.out.print("작성자 이름: ");
				String author = sc.nextLine();
				System.out.print("파일 내용 입력: ");
				String content = sc.nextLine();
				
				manager.addFile(name, author, content);
				break;
				
			case 2:
				manager.printFileList();
				break;
				
			case 3:
				System.out.println("파일 이름과 파일 내용 중 무엇을 수정할지 입력해주세요.");
				System.out.println("| 1. 파일 이름 수정 | 2. 파일 내용 수정 |");
				int editChoice = sc.nextInt();
				sc.nextLine();
				if(editChoice == 1) {
					System.out.print("기존 파일 이름: ");
					String oldName = sc.nextLine();
					System.out.print("새로운 파일 이름: ");
					String newName = sc.nextLine();
					manager.editFileName(oldName, newName);
				} else if(editChoice == 2) {
					System.out.print("내용을 수정할 파일 이름: ");
					String fileName = sc.nextLine();
					System.out.print("새로운 내용 입력: ");
					String newContent = sc.nextLine();
					manager.editFileContent(fileName, newContent);
				}
				break;
				
			case 4:
				vaccine.systemScan(manager.getFileList(), db);
				break;
				
			case 5:
				manager.moveToQuarantine();
				break;
				
			case 6:
				manager.printQuarantineList();
				break;
				
			case 7:
				manager.emptyQuarantine();
				break;
				
			case 8:
				db.loadPatternFromFile();
				break;
				
			case 0:
				System.out.println("백신 프로그램을 종료합니다.");
				return;
				
			default:
				System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
			}
		}
	}
}