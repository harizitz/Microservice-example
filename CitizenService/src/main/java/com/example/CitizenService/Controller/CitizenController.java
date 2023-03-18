package com.example.CitizenService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CitizenService.Entity.Citizen;
import com.example.CitizenService.Repository.CitizenRepository;

@RestController
@RequestMapping("/citizen")
public class CitizenController {
	
	@Autowired
	CitizenRepository citizenRepository;
	
	@RequestMapping("/id/{id}")
	public ResponseEntity<List<Citizen>> getById(@PathVariable int id) {
		List<Citizen> citizens=citizenRepository.findByVaccinationCenterId(id); 
		return new ResponseEntity<>(citizens,HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Citizen> add(@RequestBody Citizen newCitizen) {
		Citizen citizen=citizenRepository.save(newCitizen); 
		return new ResponseEntity<>(citizen,HttpStatus.OK);
	}

}
