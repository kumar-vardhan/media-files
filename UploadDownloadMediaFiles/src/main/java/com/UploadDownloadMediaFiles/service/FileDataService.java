package com.UploadDownloadMediaFiles.service;

import com.UploadDownloadMediaFiles.entities.FileData;
import com.UploadDownloadMediaFiles.repo.FileDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class FileDataService {

    final String FILEPATH="C:\\Users\\Developer\\Desktop\\new\\";

    @Autowired
    private FileDataRepo fileDataRepo;

    public String uploadImage(MultipartFile file) throws IOException{
        String filepath=FILEPATH+file.getOriginalFilename();
        FileData fileData = fileDataRepo.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filepath).build());
        file.transferTo(new File(filepath));
        if(fileData != null){
            return "file uploaded successfully : "+filepath;
        }
        return null;
    }

    public byte[] downloadImage(String fileName) throws IOException {
        FileData fileData = fileDataRepo.findByName(fileName);
        String filePath = fileData.getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

}
