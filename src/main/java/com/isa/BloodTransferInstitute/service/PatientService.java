package com.isa.BloodTransferInstitute.service;

import com.isa.BloodTransferInstitute.dto.user.patient.CheckUniquePatientDTO;
import com.isa.BloodTransferInstitute.dto.user.patient.NewPatientDTO;
import com.isa.BloodTransferInstitute.dto.user.patient.UpdatePatientDTO;
import com.isa.BloodTransferInstitute.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

public interface PatientService {

	User add(NewPatientDTO dto);

	User update(UpdatePatientDTO dto);

	Optional<User> get(Long id);

	List<User> getAll();

	void delete(Long id);

	ArrayList<Boolean> checkUnique(CheckUniquePatientDTO dto);

}
