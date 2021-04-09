package com.slokam.healthcare.service;

import java.util.List;

import com.slokam.healthcare.entity.Patient;
import com.slokam.healthcare.pojo.PatientSearchPojo;

public interface IPatientService {
	public void patientRegistration(Patient patient);
	public List<Patient> criteriaTest(String name , String email);
	 public List<Patient> patientSearch(PatientSearchPojo searchPojo);
	public List<Patient> getAllPatients();
	// public Patient getPatientById(Integer id);
	public Patient getPatientById(Integer id);
   public List<Patient> getAllPatienteven();
}
