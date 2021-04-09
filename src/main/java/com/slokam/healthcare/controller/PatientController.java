package com.slokam.healthcare.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slokam.healthcare.entity.Patient;
import com.slokam.healthcare.pojo.PatientSearchPojo;
import com.slokam.healthcare.service.IPatientService;

@RestController
@RequestMapping("patient")
public class PatientController {
	@Autowired
	private IPatientService iPatientService;
	@PostMapping("/register")
	public ResponseEntity<Patient> registerPatient(@RequestBody Patient patient){
		
		iPatientService.patientRegistration(patient);
		return new ResponseEntity<Patient>(patient,HttpStatus.CREATED);
	}
	@GetMapping("/criteriaTest/{name}/{email}")
	public ResponseEntity<List<Patient>> criteriatest(@PathVariable String name,@PathVariable String email){
		List<Patient> list=iPatientService.criteriaTest(name, email);
		return new ResponseEntity<List<Patient>>(list,HttpStatus.OK);
	}
	@PostMapping("/search")
	public ResponseEntity<List<Patient>> patientSearch(@RequestBody PatientSearchPojo searchPojo) {
		List<Patient> list=  iPatientService.patientSearch(searchPojo);
		
		return ResponseEntity.ok(list);
	}
	@GetMapping("/all")
	public ResponseEntity<List<Patient>> getAllPatient() {
		List<Patient> list=  iPatientService.getAllPatients();
		return ResponseEntity.ok(list);
	}
	@GetMapping("/byId/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable Integer id){
		Patient patient = iPatientService.getPatientById(id);
		return ResponseEntity.ok(patient);}
	
	//get all patients even number ids
	@GetMapping("/allpatientseven")
	public ResponseEntity<List<Patient>> getAllPatienteven() {
		List<Patient> list=  iPatientService.getAllPatients();
	//List<Patient> patlis=	list.stream().filter(patient->patient.getId()%2==1).collect(Collectors.toList());
	//list.stream().sorted(Comparator.comparingInt(Patient::getAge)).collect(Collectors.toList()).forEach(f->System.out.println(f.getId()));
	list.stream().filter(i->i.getName().equals("malli")).collect(Collectors.toList()).forEach(o->System.out.println(o.getName()));		
	
		return ResponseEntity.ok(list);
	}

}
