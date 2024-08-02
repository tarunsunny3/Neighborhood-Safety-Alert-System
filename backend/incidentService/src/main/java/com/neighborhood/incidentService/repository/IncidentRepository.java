package com.neighborhood.incidentService.repository;

import com.neighborhood.incidentService.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
}
