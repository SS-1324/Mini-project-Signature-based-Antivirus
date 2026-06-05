package com.kh.project.mini1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileManager {
	
	//필드 : 파일 목록 2개 만들기
	private List<VirtualFile> fileList = new ArrayList<>(); // 정상 파일 목록
    private List<VirtualFile> quarantineList = new ArrayList<>(); // 격리소 : 악성 파일 목록
	
	//메서드 : 파일 추가하기, 파일 이름 수정하기, 파일 내용 수정하기, 파일 목록 출력하기, 격리소로 이동시키기, 격리소 목록 보기, 격리소 비우기 기능 필요
    public void addFile(String fileName, String author, String content) {
        VirtualFile newFile = new VirtualFile(fileName, author, content);
        fileList.add(newFile);
        System.out.println(fileName + " 파일이 추가되었습니다.");
    }
    
    //내부 메서드 : fileList를 돌아다니며 파일이 있는지 찾는 메서드. 파일 이름 및 내용 수정에 사용. 
    private VirtualFile findFile(String fileName) {
        for (VirtualFile file : fileList) {
            if (file.getFileName().equals(fileName)) {
                return file;
            }
        }
        return null; // 파일이 없을 때 null 을 반환한다
    }
    
    //파일 이름, 내용 바꾸기 매서드
    public void editFileName(String oldName, String newName) {
        VirtualFile file = findFile(oldName);
        if (file != null) {
        	
        	//추가 : 격리 태그가 달린 파일은 접근 불가!
        	if (file.getStatus().equals("격리")) {
                System.out.println("위험한 파일의 이름은 수정할 수 없습니다.");
                return;
            }
            file.setFileName(newName);
            System.out.println(oldName + "이 " + newName + "(으)로 변경되었습니다.");
        } else {
            System.out.println("파일을 찾을 수 없습니다.");
        }
    }
    public void editFileContent(String fileName, String newContent) {
        VirtualFile file = findFile(fileName);
        if (file != null) {
        	//추가 : 격리 태그가 달린 파일은 접근 불가!
        	if (file.getStatus().equals("격리")) {
                System.out.println("위험한 파일의 이름은 수정할 수 없습니다.");
                return;
            }
            file.setContent(newContent);
            System.out.println("파일의 내용이 변경되었습니다.");
        } else {
            System.out.println("파일을 찾을 수 없습니다.");
        }
    }
    
	//파일 목록 보기
    public void printFileList() {
        System.out.println("\n===== 현재 파일 목록 =====");
        if (fileList.isEmpty()) {
            System.out.println("저장된 파일이 없습니다.");
            return;
        }
        for (VirtualFile file : fileList) {
            System.out.println(file.fileForList());
        }
        System.out.println("======================\n");
    }
    
    //격리소 메서드들 : 격리소로 보내기, 격리소에 있는 파일 리스트 보기, 격리소 비우기
    public void moveToQuarantine() {
		// iterator: fileList를 순회한다.
		Iterator<VirtualFile> iterator = fileList.iterator(); 
		int count = 0; // 몇 개 옮겼는지 세기 위한 변수
		// hasNext(): 다음 파일이 있는지 확인
		while(iterator.hasNext()) {
			VirtualFile file = iterator.next(); //다음 파일로
			// 꺼내온 파일이 격리 상태인지 확인
			if(file.getStatus().equals("격리")) {
				quarantineList.add(file); // 격리소에 넣는다.
				iterator.remove(); // 원래 있던 fileList에서 삭제한다.
				count++;
				}
			}
		if (count > 0) {
            System.out.println(count + "개의 악성 의심 파일이 격리소로 이동되었습니다.");
        } else {
            System.out.println("위험 파일이 없습니다.");
        }
		}
	
		public void printQuarantineList() {
			System.out.println("\n ===== 위험 파일 목록 =====");
			if (quarantineList.isEmpty()) {
				System.out.println("격리된 파일이 없습니다.");
				return;
			}
			for (VirtualFile file : quarantineList) {
				System.out.println(file.fileForList());
			}
			System.out.println("======================\n");
		}
		
		public void emptyQuarantine() {
			if(quarantineList.isEmpty()) {
				System.out.println("격리소가 비었습니다");
				return;
			}
			int count = quarantineList.size(); // 지우기 전의 갯수
			quarantineList.clear(); // 리스트 비우기
			System.out.println(count + "개의 악성 파일이 영구 삭제되었습니다.");
		}
		
		public List<VirtualFile> getFileList() {
		    return fileList;
		}

}
