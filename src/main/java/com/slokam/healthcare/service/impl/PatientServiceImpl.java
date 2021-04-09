package com.slokam.healthcare.service.impl;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slokam.healthcare.entity.Patient;
import com.slokam.healthcare.pojo.PatientSearchPojo;
import com.slokam.healthcare.repo.IPatientRepo;
import com.slokam.healthcare.repo.Patientcriteria;
import com.slokam.healthcare.service.IPatientService;

@Service
public class PatientServiceImpl implements IPatientService{
	@Autowired
	private IPatientRepo iPatientRepo;
	@Autowired
	private Patientcriteria patientCriteria;
	@Override
	public void patientRegistration(Patient patient) {
		patient.setRegdate(new java.util.Date());
		iPatientRepo.save(patient);
	}
	@Override
	public List<Patient> patientSearch(PatientSearchPojo searchPojo) {
		return patientCriteria.patientFullSearch(searchPojo);
	}
	@Override
	public List<Patient> criteriaTest(String name, String email) {
		
		return patientCriteria.patientSearch(name, email);
	}
	@Override
	public List<Patient> getAllPatients() {
		return iPatientRepo.findAll();
	}
	@Override
	public Patient getPatientById(Integer id) {
		Optional<Patient> patientOpt = iPatientRepo.findById(id);
		if(patientOpt.isPresent()) return patientOpt.get();
		else return null;
	}
	public List<Patient> getAllPatienteven(){
		return iPatientRepo.findAll();
	}
	
	
}
