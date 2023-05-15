package TestRandoop;

import java.io.File;
import java.io.IOException;

public class FileConverter {
    File folder = new File("C:\\Users\\hp\\Desktop\\PROVA\\");
    File[] listOfFiles = folder.listFiles();
    String oldFilename;
    File f1,f2;
    public void convert(){
        for (int i=0; i<listOfFiles.length;i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains(".txt")) {
                oldFilename = new String(listOfFiles[i].getName());
                String newFilename = oldFilename.replaceAll("\\.txt$", ".java");
                File f1 = new File(listOfFiles[i].getParentFile(), newFilename);
                listOfFiles[i].renameTo(f1);
                
                listOfFiles = folder.listFiles(); //aggiorno listOfFiles
                
          //      if(listOfFiles[i].getName().contains(".java"))
            //        System.out.println("Il file "+ listOfFiles[i].getName() + " è stato correttamente convertito in .java");
               
            }
            
            if(listOfFiles[i].getName().contains(".java"))
            
	            try {
					Runtime.getRuntime().exec("javac "+ listOfFiles[i].getAbsolutePath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            
        }
    }
    
}
