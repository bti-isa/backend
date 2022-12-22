package com.isa.BloodTransferInstitute.mappers;

import com.isa.BloodTransferInstitute.dto.NewPollDTO;
import com.isa.BloodTransferInstitute.model.Poll;
import com.isa.BloodTransferInstitute.model.User;

import org.springframework.stereotype.Component;

@Component
public class PollMapper {

	public static Poll NewDTOToEntity(NewPollDTO dto, User patient) {
		return Poll.builder()
			.weightOver50kg(dto.getWeightOver50kg())
			.commonCold(dto.getCommonCold())
			.skinDiseases(dto.getSkinDiseases())
			.problemWithPressure(dto.getProblemWithPressure())
			.antibiotics(dto.getAntibiotics())
			.menstruation(dto.getMenstruation())
			.dentalIntervention(dto.getDentalIntervention())
			.other(dto.getOther())
			.patient(patient)
			.build();
	}
}
