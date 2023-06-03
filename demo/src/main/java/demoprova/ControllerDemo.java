package demoprova;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import java.io.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import servicedemo.RandoopServiceDemo;


@RestController
public class ControllerDemo {
	
	
	
	private RandoopServiceDemo randoop = new RandoopServiceDemo();
	
	/*
    @RequestMapping("/hello")
    public void hello() {
    	randoop.RunRandoop();
        //return "ok";
        
    } */
   // @PostMapping("/document/upload")
    //public void uploadFile(@RequestPart("file") MultipartFile file,@RequestBody FileUpload jsondata)
    //@PostMapping("/uploadClass")
	//public UploadClassResponse uploadClassUT(@RequestParam("class_file") MultipartFile class_file, @RequestParam("complexity") int compl)
	
	@GetMapping("/getFile")
    public String postFile(@RequestParam("class_file") MultipartFile class_file){

    	File file = new File("classes/"+ class_file.getOriginalFilename());

    	try (OutputStream os = new FileOutputStream(file)) {
    	    os.write(class_file.getBytes());
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	randoop.RunRandoop(class_file);
		return class_file.getName();
		
    }

}
