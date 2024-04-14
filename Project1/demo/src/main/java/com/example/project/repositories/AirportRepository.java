package com.example.project.repositories;

import com.example.project.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    @Query(value = "SELECT " +
            "a.id, a.name, " +
            "c.id, c.name, " +
            "co.name, co.iso2_country_code, co.iso3_country_code, " +
            "a.iata_code, a.icao_code, a.latitude, a.longitude " +
            "FROM airport a " +
            "JOIN city c ON a.city_id = c.id " +
            "JOIN country co ON c.country_iso2_country_code = co.iso2_country_code",
            nativeQuery = true)
    List<Object[]> getAllAirportsWithCountryAndCity();
}