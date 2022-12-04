package com.dementiev.webshop.repository;

import com.dementiev.webshop.entity.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {

    Optional<Country> findByCodeAndType(String code, String type);
}
