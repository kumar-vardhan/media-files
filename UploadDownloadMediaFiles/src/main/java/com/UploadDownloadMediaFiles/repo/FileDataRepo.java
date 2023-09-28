package com.UploadDownloadMediaFiles.repo;

import com.UploadDownloadMediaFiles.entities.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDataRepo extends JpaRepository<FileData,Integer> {
    FileData findByName(String fileName);
}
