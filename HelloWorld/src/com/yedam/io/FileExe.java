package com.yedam.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileExe {
	public static void main(String[] args) {
		File file = new File("c:/temp/new.txt"); // 파일 정보 생성
		File file1 = new File("c:/temp/images/img1/new");
		try {
			if (!file.exists()) {
				file.createNewFile(); // 파일생성.
				System.out.println("파일생성됨.");
			}
			if(!file1.exists()) {
				file1.mkdirs();
				System.out.println("폴더생성됨");
			}
			FileWriter fw = new FileWriter(file); // 파일 객체를 입출력스트림의 매개값으로 사용 
			fw.write("Hello\n");
			fw.write("World\n");
			fw.flush();
			fw.close();
			if(file.exists()) {
				file.delete();
				System.out.println("deleted.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("end of prog.");
	}
}
