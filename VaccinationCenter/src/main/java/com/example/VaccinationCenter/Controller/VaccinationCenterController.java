package com.example.VaccinationCenter.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.VaccinationCenter.Entity.VaccinationCenter;
import com.example.VaccinationCenter.Model.Citizen;
import com.example.VaccinationCenter.Model.RequiredResponse;
import com.example.VaccinationCenter.Repository.CenterRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/vaccinationcenter")
public class VaccinationCenterController {

	@Autowired
	CenterRepository centerRepo;

	@Autowired
	RestTemplate restTemplate;

	@PostMapping("/add")
	public ResponseEntity<VaccinationCenter> add(@RequestBody VaccinationCenter newVaccinationCenter) {
		VaccinationCenter center = centerRepo.save(newVaccinationCenter);
		return new ResponseEntity<>(center, HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public String testfunc() {
		return "Test";
	}

	@RequestMapping("/id/{id}")
	@HystrixCommand(fallbackMethod = "fallbackRecovery")
	public ResponseEntity<RequiredResponse> getByCenterId(@PathVariable int id) {

		RequiredResponse req = new RequiredResponse();
		req.setCenter(centerRepo.findById(id).get());

		req.setListCitizens(restTemplate.getForObject("http://CITIZEN-SERVICE/citizen/id/" + id, List.class));
		return new ResponseEntity<>(req, HttpStatus.OK);
	}
	
	public ResponseEntity<RequiredResponse> fallbackRecovery(@PathVariable int id) {

		RequiredResponse req = new RequiredResponse();
		req.setCenter(centerRepo.findById(id).get());

		return new ResponseEntity<>(req, HttpStatus.OK);
	}
	
	

}