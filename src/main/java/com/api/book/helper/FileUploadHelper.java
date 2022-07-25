package com.api.book.helper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {
    //upload files in system
    //public final String UPLOAD_DIR="C:\\Users\\NSUSHRUT\\Documents\\springBoot_tutorial\\bootRestBook\\src\\main\\resources\\static\\image";

    //uploads in server
    public final String UPLOAD_DIR=new ClassPathResource("static/image/").getFile().getAbsolutePath();

    public FileUploadHelper() throws IOException {
    }

    public boolean uploadFile(MultipartFile file){
        boolean uploaded=false;
        try {
//            InputStream is=file.getInputStream();
//            byte data []=new byte[is.available()];
//            //read the file
//            is.read(data);
//
//            //write the file
//            FileOutputStream fos=new FileOutputStream(UPLOAD_DIR+"\\"+file.getOriginalFilename());
//
//            fos.write(data);
//            fos.flush();
//            fos.close();

            // or

            Files.copy(file.getInputStream(),Paths.get(UPLOAD_DIR+"\\"+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            uploaded=true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return uploaded;
    }
}
