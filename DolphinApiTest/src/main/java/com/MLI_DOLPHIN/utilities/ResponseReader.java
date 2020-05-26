package com.MLI_DOLPHIN.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResponseReader {
	
	public static  String ExcpectedValue;
	
	public static void readResponseFile(){
		
		try {
			ExcpectedValue= new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+File.separator+"PanDob.text")));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	

}
