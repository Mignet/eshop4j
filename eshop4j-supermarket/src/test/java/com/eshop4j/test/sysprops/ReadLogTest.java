package com.eshop4j.test.sysprops;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadLogTest {
	public static void main(String[] args) {
		File f = new File("logs/eshop4j-supermarket.log");
		if(f.canRead()){
			try {
				FileReader fileReader = new FileReader(f);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				StringBuffer stringBuffer = new StringBuffer();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					stringBuffer.append(line);
					stringBuffer.append("\n");
				}
				fileReader.close();
				System.out.println("Contents of file:");
				System.out.println(stringBuffer.toString());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println(System.getProperty( "user.home" ));
			System.out.println(System.getProperty( "user.dir" ));
		}
	}
}
