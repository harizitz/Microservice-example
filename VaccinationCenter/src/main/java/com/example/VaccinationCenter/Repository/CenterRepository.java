package com.example.VaccinationCenter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.VaccinationCenter.Entity.VaccinationCenter;

public interface CenterRepository extends JpaRepository<VaccinationCenter, Integer> {

}
