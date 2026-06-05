package com.kh.project.mini1;

import java.util.List;

public class Vaccine {
	
	//매서드 : 파일 검사 후 파일의 status 변경하기.
	public void systemScan(List<VirtualFile> files, DB db) {
		
		int count = 0;		
		System.out.println("검사를 시작합니다.");
		
		for(VirtualFile file : files) {
			if(db.getWhiteList().contains(file.getFileName())) {
		        file.setStatus("허용");
		    } else if(db.isMalware(file)) {
		        file.setStatus("격리");
		        System.out.println("악성 파일 발견 : " + file.getFileName());
		        count++; 
		    } else {
		        file.setStatus("안전"); //기본 상태가 "안전"이긴 하지만 그래도 혹시 이상해져버리거나 할 수 있으므로 또 바꿔준다.
		    }
		}
		
		System.out.printf("검사를 완료했습니다. 발견한 악성 코드 : %d개", count);
		
	}

}
