package com.springboot.service;

import java.util.List;
import java.util.Optional;

import com.springboot.model.Engineers;

public interface EngineerService {

	List<Engineers> fetchAllEngineers();

	void saveEngineer(Optional<Engineers> engineer);

	Optional<Engineers> findEngineerById(String email);

	void deleteEngineer(Optional<Engineers> engineer);

	Boolean validateEngineer(String engineerEmail, String engineerPassword);

	void updateEngineer(Engineers newEngineer);
}
