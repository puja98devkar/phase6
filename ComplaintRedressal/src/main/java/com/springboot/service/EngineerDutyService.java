package com.springboot.service;

import java.util.List;

import com.springboot.model.EngineerDuty;

public interface EngineerDutyService {

	void saveEngineerDuty(EngineerDuty engineerDuty);

	List<EngineerDuty> findEngineerDutyByEmail(String engineerEmail);

	EngineerDuty getEngineerAssignedByTicketId(int ticketId);
}
