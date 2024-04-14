package com.example.project.repositories;

import com.example.project.model.Airport;
import com.example.project.utils.AirportFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilterRepository extends JpaRepository<Airport, Long> {

    @Query(value = "SELECT c.name AS name, c.iso2_country_code AS iso2CountryCode, c.iso3_country_code AS iso3CountryCode, " +
            "ci.id AS cityId, ci.name AS cityName, " +
            "a.id AS airportId, a.name AS airportName, a.iata_code AS iataCode, a.icao_code AS icaoCode, " +
            "a.latitude AS latitude, a.longitude AS longitude " +
            "FROM com.example.myproject.model.Country c " +
            "JOIN com.example.myproject.model.City ci ON c.id = ci.country_id " +
            "LEFT JOIN com.example.myproject.model.Airport a ON ci.id = a.city_id " +
            "WHERE (:airportFilter.countryIso2Codes IS NULL OR c.iso2_country_code IN :airportFilter.countryIso2Codes) " +
            "AND (:airportFilter.cityIds IS NULL OR ci.id IN :airportFilter.cityIds) " +
            "AND (:airportFilter.airportIcaoCodes IS NULL OR a.icao_code IN :airportFilter.airportIcaoCodes) " +
            "AND (:airportFilter.airportNames IS NULL OR a.name IN :airportFilter.airportNames)",
            nativeQuery = true)
    List<Object> formatResponseWithCountryAsRoot(AirportFilter airportFilter);

    @Query(value = "SELECT a.id AS airportId, a.name AS airportName, a.iata_code AS iataCode, a.icao_code AS icaoCode, " +
            "a.latitude AS latitude, a.longitude AS longitude, " +
            "ci.id AS cityId, ci.name AS cityName, " +
            "c.name AS countryName, c.iso2_country_code AS iso2CountryCode, c.iso3_country_code AS iso3CountryCode " +
            "FROM com.example.myproject.model.Airport a " +
            "JOIN com.example.myproject.model.City ci ON a.city_id = ci.id " +
            "JOIN com.example.myproject.model.Country c ON ci.country_id = c.id " +
            "WHERE (:airportFilter.countryIso2Codes IS NULL OR c.iso2_country_code IN :airportFilter.countryIso2Codes) " +
            "AND (:airportFilter.cityIds IS NULL OR ci.id IN :airportFilter.cityIds) " +
            "AND (:airportFilter.airportIcaoCodes IS NULL OR a.icao_code IN :airportFilter.airportIcaoCodes) " +
            "AND (:airportFilter.airportNames IS NULL OR a.name IN :airportFilter.airportNames)",
            nativeQuery = true)
    List<Object> formatResponseWithAirportAsRoot(AirportFilter airportFilter);
}