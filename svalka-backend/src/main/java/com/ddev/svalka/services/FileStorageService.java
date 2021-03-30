package com.ddev.svalka.services;


import java.io.IOException;
import java.util.stream.Stream;

import com.ddev.svalka.entities.FileDB;
import com.ddev.svalka.repositories.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
public class FileStorageService {
    @Autowired
    private FileDBRepository fileDBRepository;

    public FileDB store(MultipartFile file, Long id) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(id, fileName, file.getContentType(), file.getBytes());

        return fileDBRepository.save(FileDB);
    }


}