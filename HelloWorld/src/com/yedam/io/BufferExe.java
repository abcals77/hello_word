package com.yedam.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferExe {
	public static void main(String[] args) {
		try {
			// 입력스트림.
			FileInputStream fis = new FileInputStream("c:/Users/admin/Downloads/jdk-21_windows-x64_bin.exe");
			BufferedInputStream bis = new BufferedInputStream(fis);
			// 출력스트림.
			FileOutputStream fos = new FileOutputStream("c:/temp/jdk-21_windows-x64_bin.exe");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			
			while(true) {
				int data = bis.read();
				if(data == -1) { // 
					break;
				}
				bos.write(data);

			}
			bos.flush();
			bos.close();
			fos.close();
			bis.close();
			fis.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("end of prog");
		
	}
}	
