package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Complaints;
import com.springboot.model.EngineerDuty;
import com.springboot.service.ComplaintService;
import com.springboot.service.EngineerDutyService;

@CrossOrigin("*")
@RestController
@RequestMapping("/engineerDuty")
public class EngineerDutyController {

	@Autowired
	EngineerDutyService engineerDutyService;

	@Autowired
	ComplaintService complaintService;

	@PostMapping("/addEngineerDuty")
	public boolean addEngineerDuty(@RequestBody EngineerDuty engineersDutyAssigned) throws NoSuchFieldException {

		int ticketId = engineersDutyAssigned.getTicketId();
		String customerEmail = engineersDutyAssigned.getCustomerEmail();
		String engineerEmail = engineersDutyAssigned.getEngineerEmail();
		System.out.println(ticketId + "---" + customerEmail + "--" + engineerEmail);

		EngineerDuty existingEngineersDuty = engineerDutyService.getEngineerAssignedByTicketId(ticketId);
		if (existingEngineersDuty == null) {

			EngineerDuty engineerDuty = new EngineerDuty();

			engineerDuty.setTicketId(ticketId);
			engineerDuty.setCustomerEmail(customerEmail);
			engineerDuty.setEngineerEmail(engineerEmail);

			engineerDutyService.saveEngineerDuty(engineerDuty);
			return true;
		}
		return false;
	}

	@PostMapping("/updateStatus")
	public boolean updateStatus(@RequestBody Complaints statusUpdate) throws NoSuchFieldException {

		System.out.println(statusUpdate);

		int ticketId = statusUpdate.getTicketId();
		String newStatus = statusUpdate.getStatus();

		System.out.println(newStatus);

		Complaints complaint = complaintService.findComplaintById(ticketId);
		if (complaint != null) {
			complaint.setStatus(newStatus);
			complaintService.saveComplaint(complaint);
			return true;
		}
		return false;
	}

}
