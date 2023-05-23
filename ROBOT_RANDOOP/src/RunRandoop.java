package TestRandoop;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class RunRandoop {

	public static void main(String[] args) {
		FileConverter file = new FileConverter();
		file.convert();
		
		
		//String javaPath = "C:\\Program Files (x86)\\Java\\jre1.8.0_351\\bin\\java.exe";
		String name = "jipa";
		int timelimit = 20;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        String currentDate = dtf.format(now); 
        currentDate = currentDate.replace("/","-");
        currentDate =  currentDate.replace(" ", "--");
        currentDate =  currentDate.replace(":", "-");
			
		try {
			Process P = Runtime.getRuntime().exec("cmd /c start \"\" /Progetto-SAD-G19_/ROBOT RANDOOP/src/robot.bat "+" "+name+" "+timelimit+" "+currentDate );
			P.waitFor();
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		} 
		

	}
}
