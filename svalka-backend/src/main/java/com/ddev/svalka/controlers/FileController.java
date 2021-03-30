package com.ddev.svalka.controlers;

import com.ddev.svalka.entities.FileDB;
import com.ddev.svalka.message.ResponseFile;
import com.ddev.svalka.message.ResponseMessage;
import com.ddev.svalka.repositories.FileDBRepository;
import com.ddev.svalka.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/a")
public class FileController {
    @Autowired
    private FileStorageService storageService;

    @Autowired
    private FileDBRepository fileDBRepository;
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
                                                      @RequestParam("id") Long id) {
        String message = "";
        try {
            storageService.store(file, id);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    @Transactional
    public FileDB getListFiles(@RequestParam("id") Long id) {
        return fileDBRepository.findFileByUserId(id);
    }

}
