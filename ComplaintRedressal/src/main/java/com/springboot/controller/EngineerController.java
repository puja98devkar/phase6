package com.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.EngineerDuty;
import com.springboot.model.Engineers;
import com.springboot.service.EngineerDutyService;
import com.springboot.service.EngineerService;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/engineers")
public class EngineerController 
{

	@Autowired
	EngineerService engineerService;

	@Autowired
	EngineerDutyService engineerDutyService;

	@GetMapping("/getAllEngineers")
	public List<Engineers> getAllEngineers() 
	{
		List<Engineers> engineers = (List<Engineers>) engineerService.fetchAllEngineers();
		return engineers;
	}

	@GetMapping("/getAllEngineerMails")
	public List<String> getAllEngineerMails()
	{
		List<Engineers> engineers = (List<Engineers>) engineerService.fetchAllEngineers();
		List<String> engineerMails = new ArrayList<String>();

		for (int i = 0; i < engineers.size(); i++)
		{
			engineerMails.add(engineers.get(i).getEngineerEmail());
		}

		return engineerMails;
	}

	@PostMapping("/login")
	public List<Integer> validateEngineer(@RequestBody Engineers loginDetails) throws NoSuchFieldException
	{

		String engineerEmail = loginDetails.getEngineerEmail();
		String engineerPassword = loginDetails.getEngineerPassword();

		System.out.println(engineerEmail + " " + engineerPassword);

		List<Integer> ticketIds = new ArrayList<Integer>();
		List<EngineerDuty> engineerDuties = (List<EngineerDuty>) engineerDutyService
				.findEngineerDutyByEmail(engineerEmail);

		Boolean engineerLoginStatus = engineerService.validateEngineer(engineerEmail, engineerPassword);
		System.out.println(engineerLoginStatus);

		if (engineerLoginStatus) 
		{
			for (int i = 0; i < engineerDuties.size(); i++) 
			{
				ticketIds.add(engineerDuties.get(i).getTicketId());
			}
			return ticketIds;
		}
		return null;
	}

	@PostMapping("/addEngineer")
	public void addEngineer(@RequestBody Optional<Engineers> engineer) 
	{
		engineerService.saveEngineer(engineer);
	}

	@DeleteMapping("/deleteEngineer/{engineerEmail}")
	public void deleteEngineer(@PathVariable("engineerEmail") String email) 
	{
		Optional<Engineers> engineer = engineerService.findEngineerById(email);
		engineerService.deleteEngineer(engineer);
	}
	
	@PutMapping("/updateEngineer/{engineerEmail}")
	public boolean updateEngineer(@PathVariable String engineerEmail, @RequestBody String newName) {
		
		System.out.println("New Name : " + newName);
		System.out.println("Engineer Email : " + engineerEmail);
		Engineers newEngineer = engineerService.findEngineerById(engineerEmail).get();
		newEngineer.setEngineerName(newName);
		engineerService.updateEngineer(newEngineer);
		
		return true;
		
	}

}
