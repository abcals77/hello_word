package com.yedam.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CharacterExe {
	public static void main(String[] args) {
		Scanner scn = null;
		try {
			scn = new Scanner(new FileInputStream("c:/temp/message.txt"));
			while(true) { // 한줄 단위로 진행
				String msg = scn.nextLine();
				String[] msgAry = msg.split(" "); // 공백을 기준으로 문자를 나눈다.
				System.out.println("코드 : " + msgAry[0] //
						+ ", 이름 : " + msgAry[1] // 
						+ ", 가격 : " + msgAry[2]);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(NoSuchElementException e) {
		
		}
		
		
		scn.close();
		System.out.println("end of prog");
	}
	
	static void read() {
		// 입력스트림(문자)
				try {
					Reader reader = new FileReader("D:/Dev/git/hello_word/HelloWorld/src/com/yedam/io/StreamExe.java");
					while(true) {
						int data = reader.read(); // 2바이트식 읽음 
						if(data == -1) {
							break;
						}
						System.out.print((char) data);
					}
					reader.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
	}
		
	static void write() {
		try {
			Writer writer = new FileWriter("c:/temp/data.txt");
			writer.write('a');
			writer.write('b');
			writer.write('c');
			
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	static void write2() {
		Scanner scn = new Scanner(System.in);
		
		// 입력값을 파일 출력.
		try {
			Writer writer = new FileWriter("c:/temp/message.txt");
			while(true) {
				System.out.print("입력>>");
				String msg = scn.nextLine();
				if(msg.equals("quit")) // 값을 계속 입력하고 중단하고 싶을 때 'quit' 입력
					break;
				writer.write(msg + "\n");
				writer.flush();
				
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		scn.close();	
	}
}
