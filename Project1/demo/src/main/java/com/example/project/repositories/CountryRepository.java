package com.example.project.repositories;

import com.example.project.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, String> {
    Optional<Country> findByIso2CountryCode(String iso2CountryCode);

    Optional<Country> findByIso3CountryCode(String iso3CountryCode);
}