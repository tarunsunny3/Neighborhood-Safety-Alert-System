package com.neighborhood.incidentService.controller;

import com.neighborhood.incidentService.dto.IncidentDTO;
import com.neighborhood.incidentService.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/incidents")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    @PostMapping
    public ResponseEntity<?> reportIncident(@RequestBody IncidentDTO incidentDTO) {
        incidentService.reportIncident(incidentDTO);
        return ResponseEntity.ok("Incident reported");
    }

    @PostMapping("/emergency")
    public ResponseEntity<?> reportEmergency(@RequestBody IncidentDTO incidentDTO) {
        incidentService.reportEmergency(incidentDTO);
        return ResponseEntity.ok("Emergency reported");
    }
}
