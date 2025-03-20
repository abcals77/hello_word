package com.yedam.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamExe {
	public static void main(String[] args) {
		// 입력 + 출력 => 복사
		try {
			InputStream is = new FileInputStream("c:/temp/image2.jpg");
			OutputStream os = new FileOutputStream("c:/temp/image4.jpg");
			
			byte[] buf = new byte[100];
			while(true) {
				int data = is.read(buf);
				if(data == -1) {
					break;
				}
				os.write(buf);
				os.flush();
			}
			is.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("end of prog");
	} // end of main
	
	
	static void read() {
		// 입력스트림(바이트)
				try {
					InputStream is = new FileInputStream("c:/temp/data.bin");
					while(true) {
						int data = is.read(); // 파일에 끝 부분에 도착하면 -1 반환
						if(data == -1) {
							break;
						}
						System.out.println(data);
					}
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				
	}
	
	
	static void write() {
		// 출력스트림(바이트)
				try {
					OutputStream os = new FileOutputStream("c:/temp/data.bin");
					os.write(10);
					os.write(20);
					os.write(30);
					os.flush();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
	
}
