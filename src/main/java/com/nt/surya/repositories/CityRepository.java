package com.nt.surya.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.surya.entities.CityEntity;

public interface CityRepository extends JpaRepository<CityEntity, Serializable> {

}
