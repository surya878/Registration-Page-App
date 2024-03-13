package com.nt.surya.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.surya.entities.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Serializable> {

}
