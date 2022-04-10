package com.example.ManUtd.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface NationalityRepository extends CrudRepository<Nationality, Long> {
	
	List<Nationality> findByName(String name);

}
