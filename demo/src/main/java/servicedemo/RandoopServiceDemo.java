package servicedemo;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.multipart.MultipartFile;

public class RandoopServiceDemo {
	public void RunRandoop(MultipartFile class_file) {
		//String name = "XMLParser";	
		String name= (String) class_file.getOriginalFilename().subSequence(0,class_file.getOriginalFilename().length()-5 );
		int timelimit = 20;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        String currentDate = dtf.format(now); 
        currentDate = currentDate.replace("/","-");
        currentDate =  currentDate.replace(" ", "--");
        currentDate =  currentDate.replace(":", "-");
    	
   /*     try {
        	File f = new File(".\\classes\\"+name+".class");
			Process p = Runtime.getRuntime().exec("javac .\\classes\\"+name+".java");
			while(!f.exists())
				Thread.sleep(100);
			//int exit = p.waitFor();
		} catch (IOException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
        
		try {
			//"C:\Users\hp\Desktop\demo\XMLParser-2023-05-26--15-55-05-dati_di_copertura\coveragetot.xml"
			File f = new File(".\\"+name+"-"+currentDate+"-dati_di_copertura\\coveragetot.xml");
			Process p=Runtime.getRuntime().exec("cmd /c start \"\" robot.bat "+" "+name+" "+timelimit+" "+currentDate );
			
			while(!f.exists())
				Thread.sleep(100);

			
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}
	}
}
