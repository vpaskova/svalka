package com.ddev.svalka.repositories;

import com.ddev.svalka.entities.FileDB;
import com.ddev.svalka.entities.ZodiacSign;
import com.ddev.svalka.message.ResponseFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.File;
import java.util.stream.Stream;

public interface FileDBRepository extends JpaRepository<FileDB, Long> {
    @Query("SELECT f FROM FileDB f WHERE f.userId = :userId")
    FileDB findFileByUserId(Long userId);
}