package com.light.client.file;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.stream.ImageOutputStream;

import javafx.scene.image.Image;

public class ImageReceiveClient {
	
	public Image clientRun(int rnd){
		Socket socket = null;
		InputStream is = null;
		Image image = null;
		
		try {
			socket = new Socket("192.168.201.252",rnd);
			is = socket.getInputStream();
			image = new Image(is);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
				try {
					if(is!=null){
						is.close();
					}
					if(socket!=null){
						socket.close();
					}
					}catch (IOException e) {
					e.printStackTrace();
				}
			}
		return image;
	}
	
}

