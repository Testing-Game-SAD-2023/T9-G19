package servicedemo;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.springframework.web.multipart.MultipartFile;

public class RandoopServiceDemo {
	public String[] RunRandoop(MultipartFile class_file,int timelimit, String VecchioTimestamp) {
		//String name = "XMLParser";	
		String name= (String) class_file.getOriginalFilename().subSequence(0,class_file.getOriginalFilename().length()-5 );
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        String currentDate = dtf.format(now); 
        currentDate = currentDate.replace("/","-");
        currentDate =  currentDate.replace(" ", "--");
        currentDate =  currentDate.replace(":", "-");
        String zip_ritorno = null;
        String xml_ritorno = null;
        Random r = new Random();
        int low = 0;
        int high = 230000;
        int seed = r.nextInt(high-low) + low;
        System.out.println("Random seed " + seed);
        
        
        
    	
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

			zip_ritorno = (".\\"+name+"-"+currentDate+"-dati_di_copertura\\"+name+"_Test.zip");
			File f = new File(zip_ritorno);
			xml_ritorno = (".\\"+name+"-"+currentDate+"-dati_di_copertura\\coveragetot.xml");
			Process p=Runtime.getRuntime().exec("cmd /c start \"\" robot.bat "+" "+name+" "+timelimit+" "+currentDate+" "+seed+" "+VecchioTimestamp );
			
			while(!f.exists())
				Thread.sleep(100);
			

			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}
		String[] ritorno = {zip_ritorno,xml_ritorno, currentDate};
		return ritorno;

	}
}
