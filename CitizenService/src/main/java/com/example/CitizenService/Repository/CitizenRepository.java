package com.example.CitizenService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CitizenService.Entity.Citizen;

public interface CitizenRepository extends JpaRepository<Citizen, Integer> {

	public List<Citizen> findByVaccinationCenterId(int id);
}
