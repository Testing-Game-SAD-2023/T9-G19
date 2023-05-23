package randoop;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//import TestRandoop.FileConverter;

public class RunRandoop {
	public static void main(String[] args) {
//		FileConverter file = new FileConverter();
//		file.convert();
		
		
		//String javaPath = "C:\\Program Files (x86)\\Java\\jre1.8.0_351\\bin\\java.exe";
		String name = "XMLParser";
		int timelimit = 20;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        String currentDate = dtf.format(now); 
        currentDate = currentDate.replace("/","-");
        currentDate =  currentDate.replace(" ", "--");
        currentDate =  currentDate.replace(":", "-");
			
		try {
			Runtime.getRuntime().exec("cmd /c start \"\" robot.bat "+" "+name+" "+timelimit+" "+currentDate );
			//Runtime.getRuntime().exec("cmd /c start \"\" /G19_T9/robot.bat ");
			//P.waitFor();
			///G19_T9/robot.bat
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		} 
		

	}
}
