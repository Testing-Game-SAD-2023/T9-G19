package demoprova;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ByteArrayResource;

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
	
	
/*	@GetMapping("/getFile")
    public MultipartFile GETFile(@RequestParam("class_file") MultipartFile class_file){

    	File file = new File("classes/"+ class_file.getOriginalFilename());

    	try (OutputStream os = new FileOutputStream(file)) {
    	    os.write(class_file.getBytes());
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	randoop.RunRandoop(class_file);
		return class_file;
		
    }
	
	*/
	@GetMapping("/getFile")
	public ResponseEntity<Resource> GETFile(@RequestParam("class_file") MultipartFile class_file) throws java.lang.ClassNotFoundException, MalformedURLException{
		
    	File file = new File("classes/"+ class_file.getOriginalFilename());

    	try (OutputStream os = new FileOutputStream(file)) {
    	    os.write(class_file.getBytes());
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String cartellaritorno = randoop.RunRandoop(class_file);
		
		Resource resource = new UrlResource(new File(cartellaritorno).toURI());
		if (!resource.exists()) {
			throw new ClassNotFoundException("File not found ");
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+resource.getFilename()+"\"")
				.body(resource);
	}
	
}
