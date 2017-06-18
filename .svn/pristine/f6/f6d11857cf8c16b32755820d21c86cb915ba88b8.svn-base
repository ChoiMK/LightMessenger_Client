package com.light.client.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileOutWriter {
	public void fileWrite(String content, String filepath){
		File file = new File(filepath);
		FileWriter fw = null;	
		String line = System.getProperty("line.separator");
		content = content.replace("\n", line);
		try {
			fw = new FileWriter(file);
				fw.write(content);
				
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fw!=null){
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
