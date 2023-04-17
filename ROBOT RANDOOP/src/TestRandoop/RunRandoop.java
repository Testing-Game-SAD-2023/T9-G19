package TestRandoop;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class RunRandoop {

	public static void main(String[] args) {
		
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
			Runtime.getRuntime().exec("cmd /c start \"\" robot.bat "+" "+name+" "+timelimit+" "+currentDate );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}
		
	}
}
