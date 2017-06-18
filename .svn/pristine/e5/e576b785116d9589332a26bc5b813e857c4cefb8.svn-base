package com.light.client.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPFileClient {

	public void clientRun(String run) {
		Socket socket = null;
		FileInputStream fis = null;
		OutputStream os = null;

		try {
			
			socket = new Socket("192.168.201.252", 8888);

			fis = new FileInputStream(new File(run));//보내는거
			os = socket.getOutputStream();

			byte[] tmp = new byte[1024];
			int size = 0;
			while((size=fis.read(tmp))>0){
				os.write(tmp);
			}
			
			

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				if (fis != null) {
					fis.close();
				}				
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
