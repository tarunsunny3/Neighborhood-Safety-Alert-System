package com.neighborhood.incidentService.controller;

import com.neighborhood.incidentService.dto.GetIncidentsResponse;
import com.neighborhood.incidentService.dto.IncidentDTO;
import com.neighborhood.incidentService.service.IncidentService;
import com.neighborhood.incidentService.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/incidents")
public class  IncidentController {

    @Autowired
    private IncidentService incidentService;

    @Autowired
    private KafkaProducerService kafkaProducerService;
    @GetMapping("/all")
    public ResponseEntity<GetIncidentsResponse> getIncidents(){
        List<IncidentDTO> incidents = new ArrayList<>();
        incidents.add(new IncidentDTO("Incident New", "Normal incident", "Murietta", false));
        GetIncidentsResponse response = GetIncidentsResponse
                .builder()
                .incidents(incidents)
                .build();
        return ResponseEntity.ok(response);
    }
    @PostMapping("/report/{count}")
    public ResponseEntity<?> reportIncident(@PathVariable String count) {
        int size = Integer.parseInt(count);
        for(int i = 0; i < size; i++ )
            incidentService.reportIncident(i);

        return ResponseEntity.ok("Incident reported");
    }
    @PostMapping("/report-incident")
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
