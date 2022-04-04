package com.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.model.Engineers;
import com.springboot.repository.EngineerRepository;

@Service
public class EngineerServiceImpl implements EngineerService {

	@Autowired
	EngineerRepository engineerRepository;

	@Override
	public List<Engineers> fetchAllEngineers() {
		return engineerRepository.findAll();
	}

	@Override
	public void saveEngineer(Optional<Engineers> engineer) 
	{
		if(engineer.isPresent())
			engineerRepository.save(engineer.get());
		else
			System.out.println("Engineer Details are Empty.");
	}

	@Override
	public Optional<Engineers> findEngineerById(String email) {
		Optional<Engineers> engineer = engineerRepository.findById(email);
		return engineer;
	}

	@Override
	public void deleteEngineer(Optional<Engineers> engineer) {

		if (engineer.isPresent())
			engineerRepository.delete(engineer.get());
		else
			System.out.println("Engineer is empty");

	}

	@Override
	public Boolean validateEngineer(String engineerEmail, String engineerPassword) {
		System.out.println(engineerEmail + " --- " + engineerPassword);
		if (engineerRepository.findById(engineerEmail).isPresent()) {
			Engineers engineer = engineerRepository.findById(engineerEmail).get();
			String dbPassword = engineer.getEngineerPassword().toString();
			if (dbPassword.equals(engineerPassword)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void updateEngineer(Engineers newEngineer) {
		
		engineerRepository.save(newEngineer);
	}

}
