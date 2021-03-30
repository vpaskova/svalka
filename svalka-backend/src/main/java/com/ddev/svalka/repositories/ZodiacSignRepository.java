package com.ddev.svalka.repositories;

import com.ddev.svalka.entities.User;
import com.ddev.svalka.entities.ZodiacSign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ZodiacSignRepository extends JpaRepository<ZodiacSign,Long> {
    @Query("select zs FROM ZodiacSign zs WHERE lower(zs.zodiacType) = :zodiacName")
    ZodiacSign findZodiacSignByName(String zodiacName);
}
