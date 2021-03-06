package com.light.client.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.light.messenger.client.MessengerController;

public class SendTCPClient {

	public void clientRun(String run) {
		Socket socket = null;
		FileInputStream fis = null;
		OutputStream os = null;
		
		try {
			
			socket = new Socket("192.168.201.252", 8888);

			fis = new FileInputStream(new File(run));//�����°�
			os = socket.getOutputStream();

			byte[] tmp = new byte[1024];
			
			while(fis.read(tmp)!=-1){
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
